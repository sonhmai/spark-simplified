package com.spark.simplified.deploy.history

import com.spark.simplified.conf.SparkConf


/** A class that provides application history from event logs stored in the file system.
  * This provider checks for new finished applications in the background periodically and
  * renders the history application UI by parsing the associated event logs.
  */
class FsHistoryProvider(
    conf: SparkConf
) extends ApplicationHistoryProvider {

  override def start(): Unit = {}

  override def stop(): Unit = {}
}
