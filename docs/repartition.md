# DataFrame Repartition

What happens when Spark runs a repartition on a DataFrame?

## Data Structures

On Master


On Executor


## Difference repartition vs write.partitionBy

`df.repartition(self.target.partition_by)` 
- is used to repartition the DataFrame based on the partition_by column(s) before writing it out. 
- This can help ensure that all rows with the same partition_by value are written out by the same task, 
- which can reduce the number of output files and improve write performance.


`df.write.partitionBy("col")` 
- Spark will create a separate folder for each unique value in the "col" column
- write the corresponding rows into the appropriate folder. 
- This can be useful for optimizing read performance when you often filter by the partitioning column.