package com.spark.simplified.util

import org.slf4j.{Logger, LoggerFactory}

trait Logging {
  // Make the log field transient so that objects with Logging can
  // be serialized and used on another machine
  @transient private var log_ : Logger = _

  protected def logName = {
    // Ignore trailing $'s in the class names for Scala objects
    this.getClass.getName.stripSuffix("$")
  }

  protected def log: Logger = {
    if (log_ == null) {
      log_ = LoggerFactory.getLogger(logName)
    }
    log_
  }

  def logWarning(msg: => String): Unit = {
    log.warn(msg)
  }

  def logInfo(msg: => String): Unit = {
    log.info(msg)
  }
}
