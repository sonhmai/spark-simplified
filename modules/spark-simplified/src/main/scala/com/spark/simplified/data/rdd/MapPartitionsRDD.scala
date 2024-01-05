package com.spark.simplified.data.rdd

import com.spark.simplified.data.partition.Partition

class MapPartitionsRDD[U, T] extends RDD[U]{
  override def compute(split: Partition) = ???
}
