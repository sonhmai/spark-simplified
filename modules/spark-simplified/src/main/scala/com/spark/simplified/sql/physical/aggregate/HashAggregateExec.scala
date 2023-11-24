package com.spark.simplified.sql.physical.aggregate
import com.spark.simplified.rdd.RDD
import com.spark.simplified.sql.catalyst.InternalRow
import com.spark.simplified.sql.physical.SparkPlan

case class HashAggregateExec(
    child: SparkPlan
) extends SparkPlan {
  override protected def doExecute(): RDD[InternalRow] = ???
}
