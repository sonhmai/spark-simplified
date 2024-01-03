package com.spark.simplified.deploy.master.model

object WorkerState extends Enumeration {
  type WorkerState = Value

  val ALIVE, DEAD, DECOMMISSIONED, UNKNOWN = Value
}
