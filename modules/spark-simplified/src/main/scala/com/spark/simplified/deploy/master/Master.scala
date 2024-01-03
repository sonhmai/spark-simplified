package com.spark.simplified.deploy.master

import com.spark.simplified.deploy.master.model.WorkerInfo
import com.spark.simplified.process.communication.WorkerSchedulerStateResponse
import com.spark.simplified.rpc.{RpcEndpoint, RpcEnv}
import com.spark.simplified.util.Logging

import scala.collection.mutable

class Master(
    rpcEnv: RpcEnv,
    webUIPort: Int
) extends RpcEndpoint with Logging {

  private val idToWorker = new mutable.HashMap[String, WorkerInfo]

  // ====== implementing RpcEndpoint starts =======
  override def receive: PartialFunction[Any, Unit] = { case WorkerSchedulerStateResponse(workerId) =>
    logInfo("haha")
  }

  override def receiveAndReply(): PartialFunction[Any, Unit] = ???

  // ====== implementing RpcEndpoint ends =======
}
