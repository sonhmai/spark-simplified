package com.spark.simplified.sql.catalyst

/**
 * An abstract class for row used internally in Spark SQL,
 * which only contains the columns as internal types.
 *
 * Spark's internal binary row format.
 */
abstract class InternalRow {
  def numFields: Int

}
