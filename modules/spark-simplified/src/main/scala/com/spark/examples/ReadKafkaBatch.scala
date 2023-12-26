package com.spark.examples

import org.apache.spark.sql.SparkSession

import java.io.File

object ReadKafkaBatch {
  def main(args: Array[String]): Unit = {

    // warehouseLocation points to the default location for managed databases and tables
    val warehouseLocation = new
        File("spark-warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("kafka_batch")
      .config("spark.master", "local[*]")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate()

    val df_input = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
      .option("subscribe", "topic1")
      // 2 options below if not specified default to the earliest and latest offsets
      .option("startingOffsets", "earliest")
      .option("endingOffsets", "latest")
      .load()

    df_input.count()

    df_input.explain(true)

    df_input.show()

  }
}
