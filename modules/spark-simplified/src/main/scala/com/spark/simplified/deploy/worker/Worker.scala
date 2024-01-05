package com.spark.simplified.deploy.worker

import com.spark.simplified.process.communication.deploy.LaunchDriver
import com.spark.simplified.rpc.RpcEndpoint
import com.spark.simplified.util.Logging

import scala.collection.mutable

class Worker extends RpcEndpoint with Logging {

  // keeping track of drivers, executors on the node worker process is running.
  val runningDrivers    = new mutable.HashMap[String, DriverRunner]
  val finishedDrivers   = new mutable.LinkedHashMap[String, DriverRunner]
  val runningExecutors  = new mutable.HashMap[String, ExecutorRunner]
  val finishedExecutors = new mutable.LinkedHashMap[String, ExecutorRunner]

  override def receive: PartialFunction[Any, Unit] = {
    case LaunchDriver(driverId, resources) => {
      logInfo(s"Asked to launch driver $driverId")

      val driverRunner = new DriverRunner(driverId)
      runningDrivers(driverId) = driverRunner
      driverRunner.start()
    }
  }

  override def receiveAndReply(): PartialFunction[Any, Unit] = ???

}
