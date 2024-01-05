package com.spark.simplified.process.driver

/**
 * Driver state on Master process. Equi to DriverState in spark.deploy.master
 */
object DriverStateOnMaster extends Enumeration {

  type DriverStateOnMaster = Value

  // SUBMITTED: Submitted but not yet scheduled on a worker
  // RUNNING: Has been allocated to a worker to run
  // FINISHED: Previously ran and exited cleanly
  // RELAUNCHING: Exited non-zero or due to worker failure, but has not yet started running again
  // UNKNOWN: The state of the driver is temporarily not known due to master failure recovery
  // KILLED: A user manually killed this driver
  // FAILED: The driver exited non-zero and was not supervised
  // ERROR: Unable to run or restart due to an unrecoverable error (e.g. missing jar file)
  val SUBMITTED, RUNNING, FINISHED, RELAUNCHING, UNKNOWN, KILLED, FAILED, ERROR = Value
}

