package com.spark.simplified.sql.catalyst

/**
  * Represents one row of output from a relational operator.
  * Allows both generic access by ordinal, which will incur boxing overhead for primitives,
  * and native primitive access.
  *
  * A Row object can be constructed by providing field values. Example
  * {{{
  *   // create a Row from values
  *   Row(val1, val2, val3, ...)
  * }}}
  *
  *
  */
trait Row extends Serializable {



}
