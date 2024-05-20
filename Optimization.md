# Optimization

## DISTINCT vs ROW_NUMBER for dedup
distinct
- requires a full shuffle because distinct requires a global view of all the data.
- more efficient when number of duplicate is low

row_number
- sorting and ranking rows in each partition
- data is large and the duplicates are evenly distributed across partitions, using window functions with ROW_NUMBER() can be more efficient,
- as it avoids the expensive shuffle operation

small datasets with low number of duplicates
- DISTINCT more efficient than ROW_NUMBER for dedup
- because it avoids overhead of sorting rows within partitions

large datasets with significant number of duplicates
- DISTINCT is less efficient
  - requires a full shuffle regardless of their partitioning -> IO and memory pressure
  - skewed partitions can cause memory or slow tasks issues.
- ROW_NUMBER
  - dedup within each partition by partitioning columns (`name, email, address` in example below)

``` 
# schema
|-- id: long
|-- name: string
|-- email: string
|-- address: string

val deduped = spark.sql("""
  SELECT DISTINCT name, email, address
  FROM table
""")

# without PARTITION BY a full shuffle is required just like DISTINCT 
# with an extra sort step by (col3, col4) before assigning row number.
SELECT col3, col4, ROW_NUMBER() OVER (ORDER BY col3, col4) AS row_num
FROM table

# using row_num
val windowSpec = Window.partitionBy("name", "email", "address").orderBy("id")
val deduped = spark.sql("""
  SELECT name, email, address
  FROM (
    SELECT name, email, address, ROW_NUMBER() OVER (${windowSpec.toString}) AS row_num
    FROM table
  )
  WHERE row_num = 1
""")
```