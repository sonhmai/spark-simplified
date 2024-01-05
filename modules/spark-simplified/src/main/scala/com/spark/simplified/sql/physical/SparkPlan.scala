package com.spark.simplified.sql.physical
import com.spark.simplified.data.rdd.RDD
import com.spark.simplified.sql.catalyst.InternalRow

trait SparkPlan {
  protected def doExecute(): RDD[InternalRow]
}
