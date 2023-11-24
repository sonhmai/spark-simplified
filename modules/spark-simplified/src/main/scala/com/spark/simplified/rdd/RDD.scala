package com.spark.simplified.rdd
import com.spark.simplified.partition.Partition

abstract class RDD[T] {
  def compute(split: Partition): Iterator[T]
}
