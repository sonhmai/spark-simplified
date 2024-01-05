package com.spark.simplified.process.communication.deploy

trait MasterVsWorkerDeployMessage
trait Master2WorkerMessage extends MasterVsWorkerDeployMessage
trait Worker2MasterMessage extends MasterVsWorkerDeployMessage

// Master to Worker
case class LaunchDriver(
    driverId: String,
    resources: Map[String, String] = Map.empty
) extends Master2WorkerMessage

// Worker to Master
