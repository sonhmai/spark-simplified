package com.spark.examples

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

import java.io.File

object DedupWithRowNumberWindowing {
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

    val startDate = java.sql.Date.valueOf("2022-01-01")  // Start date
    val endDate = java.sql.Date.valueOf("2022-12-31")  // End date
    val addDateExp = date_add(lit(startDate), floor(randn() * datediff(lit(endDate), lit(startDate))).cast("int"))
    val numCustomers = 1_000_000

    val inputDf = spark
      .range(1, 20_000_000)
      .withColumnRenamed("id", "account_number")
      .withColumn("closed_date", addDateExp) // add random date as account closed date
      .withColumn("customer_code", floor(rand() * numCustomers).cast("int"))

    println(s"num partitions inputDf ${inputDf.rdd.getNumPartitions}")

    val repartitionDf = inputDf
//      .repartition(200, col("customer_code"))

    val window = Window
      .partitionBy("customer_code")
      .orderBy(col("account_number").desc)

    val dedupDF = repartitionDf
      .withColumn("row_number", row_number().over(window))
      .filter("row_number == 1")

    println(s"num partitions ${dedupDF.rdd.getNumPartitions}")

    dedupDF.explain(true)
    dedupDF
      .write
      .mode(SaveMode.Overwrite)
      .parquet("spark-warehouse/dedup_rownum")

    scala.io.StdIn.readLine()

    println("done")
  }
}
