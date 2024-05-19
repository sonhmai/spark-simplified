package com.spark.simplified.sql.execution.datasources

import com.spark.simplified.sql.SparkSession
import org.apache.hadoop.fs.Path

/**
  * Used to read and write data in files to/from [[com.spark.simplified.sql.catalyst.InternalRow]] format
  */
trait FileFormat {

  def isSplitable(
                   sparkSession: SparkSession,
                   options: Map[String, String],
                   path: Path
                 ): Boolean
}
