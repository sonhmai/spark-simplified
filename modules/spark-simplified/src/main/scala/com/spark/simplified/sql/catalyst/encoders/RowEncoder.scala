package com.spark.simplified.sql.catalyst.encoders

/**
  * Factory for constructing encoders that convert external row to/from
  * Spark SQL internal binary representation.
  * Following is a mapping between Spark SQL types and its allowed external types:
  * {{{
  *   BooleanType -> java.lang.Boolean
  *   StringType -> String
  * }}}
  */
object RowEncoder {

}
