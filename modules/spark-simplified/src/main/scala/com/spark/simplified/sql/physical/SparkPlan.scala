package com.spark.simplified.sql.physical
import com.spark.simplified.rdd.RDD
import com.spark.simplified.sql.catalyst.InternalRow

trait SparkPlan {
  protected def doExecute(): RDD[InternalRow]
}
