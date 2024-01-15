package com.spark.examples

import org.apache.spark.sql.SparkSession

import java.io.File

object StreamDelta {
  def main(args: Array[String]): Unit = {

    // warehouseLocation points to the default location for managed databases and tables
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("StreamingDeltaFormat")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")  // needed for delta
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog") // needed for delta
      .config("spark.master", "local[*]")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate()

    val df_input = spark.readStream
      .format("delta")
      .option("maxFilesPerTrigger", 5)
      .option("ignoreChanges", "true")
      .load("delta_table_1")

    df_input.count()

//    df_input.explain(true)
//
//    df_input.show()

  }
}
