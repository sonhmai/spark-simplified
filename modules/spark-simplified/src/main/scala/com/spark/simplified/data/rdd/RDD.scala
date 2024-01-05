package com.spark.simplified.data.rdd

import com.spark.simplified.data.partition.Partition

abstract class RDD[T] {
  def compute(split: Partition): Iterator[T]
}
