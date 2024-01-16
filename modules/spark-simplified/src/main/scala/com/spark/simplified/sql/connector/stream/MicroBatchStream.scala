package com.spark.simplified.sql.connector.stream

import com.spark.simplified.sql.connector.read.{InputPartition, Offset}

trait MicroBatchStream extends SparkDataStream {

  def latestOffset(): Offset

  def planInputPartitions(start: Offset, end: Offset): Array[InputPartition]

}
