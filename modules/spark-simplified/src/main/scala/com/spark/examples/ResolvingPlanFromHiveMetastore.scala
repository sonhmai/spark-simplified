package com.spark.examples

import org.apache.spark.sql.SparkSession

import java.io.File

object ResolvingPlanFromHiveMetastore {
  def main(args: Array[String]) = {

    // warehouseLocation points to the default location for managed databases and tables
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("hive_metastore")
      .config("spark.master", "local[*]")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    import spark.sql

    val df = sql("select * from table1")

    df.explain(true)

    df.show()

  }
}
