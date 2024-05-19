package com.spark.simplified.sql

import com.spark.simplified.sql.catalyst.Row
import com.spark.simplified.sql.execution.QueryExecution

class Dataset[T] private[sq](
                            val queryExecution: QueryExecution,
                            ) extends Serializable {

  /**
    * Converts this strongly typed collection of data to generic Dataframe.
    * In contrast to the strongly typed objects that Dataset operations work on,
    * a Dataframe returns generic [[Row]] objects that allow fields to be accessed
    * by ordinal or name.
    */
  def toDF(): DataFrame = new Dataset[Row](queryExecution, RowEncoder(schema))
}
