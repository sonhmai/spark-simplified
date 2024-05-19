package com.spark.simplified.sql.execution.datasources.parquet

import com.spark.simplified.sql.SparkSession
import com.spark.simplified.sql.execution.datasources.FileFormat
import org.apache.hadoop.fs.Path

class ParquetFileFormat extends FileFormat {

  override def isSplitable(sparkSession: SparkSession, options: Map[String, String], path: Path) = true
}
