package examples.read

import org.apache.spark.sql.SparkSession

import java.io.File

object ReadS3 {
  def main(args: Array[String]): Unit = {

    // warehouseLocation points to the default location for managed databases and tables
    val warehouseLocation = new
        File("spark-warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("read_s3")
      .config("spark.master", "local[*]")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate()

    val df_input = spark
      .read
      .parquet("s3://bucket/table_parquet/")

    df_input.count()

//    df_input.explain(true)
//
//    df_input.show()

  }
}
