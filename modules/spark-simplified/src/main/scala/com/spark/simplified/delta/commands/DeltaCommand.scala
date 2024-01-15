package com.spark.simplified.delta.commands
import com.spark.simplified.sql.SparkSession
import com.spark.simplified.sql.catalyst.Expression

/** Helper trait for all delta commands.
  */
trait DeltaCommand {
  protected def parsePredicates(
      spark: SparkSession,
      predicate: String
  ): Seq[Expression] = ???
}
