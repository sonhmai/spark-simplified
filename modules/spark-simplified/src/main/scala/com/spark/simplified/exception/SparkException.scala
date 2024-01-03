package com.spark.simplified.exception

class SparkException(
    message: String,
    cause: Throwable
) extends Exception(message, cause) {

  def this(message: String) = this(message, cause = null)
}
