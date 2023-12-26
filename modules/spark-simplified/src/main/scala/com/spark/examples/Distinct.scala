package com.spark.examples
import org.apache.spark.sql.SparkSession

import java.io.File

object Distinct {
  def main(args: Array[String]) = {

    // warehouseLocation points to the default location for managed databases and tables
    val warehouseLocation = new
        File("spark-warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("hive_metastore")
      .config("spark.master", "local[*]")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    import spark.sql

    val df_input = sql("select * from table2").cache()
    println(s"num partitions df_input ${df_input.rdd.getNumPartitions}")

    df_input.show()

    val df = df_input.distinct()

    println(s"num partitions ${df.rdd.getNumPartitions}")

    df.explain(true)

    df.show()

  }
}
