# Distinct or DropDuplicates

- distinct == dropDuplicates triggers a shuffle.


what happens if dropDuplicates on all columns, how will the shuffle happen?
1. Spark will generate a hash value for each row based on the values in all columns. 
Rows with the same hash value are considered to be duplicates.

2. During the shuffle, Spark will send rows with the same hash value to the same partition. 
This is done to ensure that duplicates of a row are located in the same partition, 
as deduplication is done on a per-partition basis.

3. Within each partition, Spark will keep only one instance of each group of duplicate rows.

4. The resulting DataFrame after the shuffle and deduplication will have no duplicates, 
where a duplicate is defined as a row that is identical to another row in all columns.


Logical Deduplicate
-> Logical Aggregate
-> Physical HashAggregateExec
-> Exchange (inserted) + HashAggregateExec


HashAggregateExec
- The shuffle operation comes into play because the HashAggregateExec operator needs to ensure that all rows 
with the same key (i.e., the same values in the deduplication columns) end up on the same partition.
- This is needed because HashAggregateExec operator works on 1 partition at a time.
- Simplified process
  1. The Exchange operator shuffles the data such that all rows with the same key end up on the same partition. 
  The key is computed using a hash function on the deduplication columns.
  2. HashAggregateExec operator processes one partition at a time. 
     - For each partition, it builds an in-memory hash map where the keys are the hashes
     and the values are the corresponding rows. 
     - If it encounters a key that is already in the hash map, it discards the row, effectively deduplicating the data.
  3. HashAggregateExec operator outputs one row for each entry in the hash map. 
  Since the hash map only contains unique keys, the output is deduplicated.


## Observations

1. If we `distinct()` or `dropDuplicates` on all columns, then repartition on 1 column,
there will be 2 separate shuffles because hash for all columns is different from hash on 1 column.
- [ ] do experiment to verify this.
