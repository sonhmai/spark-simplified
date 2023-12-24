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

    sql("CREATE TABLE IF NOT EXISTS table2 (key INT, value STRING) USING parquet")
//    sql(
//      """
//        |insert into table2 (key, value) values
//        | (1, "one"),
//        | (2, "two")
//        |""".stripMargin)

    val df = sql("select key from table2")

    df.explain(true)

    df.show()

  }
}
