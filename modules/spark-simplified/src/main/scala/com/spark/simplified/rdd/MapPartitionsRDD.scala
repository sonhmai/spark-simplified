package com.spark.simplified.rdd
import com.spark.simplified.partition.Partition

class MapPartitionsRDD[U, T] extends RDD[U]{
  override def compute(split: Partition) = ???
}
