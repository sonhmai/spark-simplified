package com.spark.simplified.rpc.message
import com.spark.simplified.rpc.{RpcEndpoint, RpcEnv}

/** A heartbeat from executors to the driver. This is a shared message used by several internal
  * components to convey liveness or execution information for in-progress tasks. It will also
  * expire the hosts that have not heartbeated for more than spark.network.timeout.
  * spark.executor.heartbeatInterval should be significantly less than spark.network.timeout.
  */
case class Heartbeat(
    message: String,
    executorId: String
)

class HeartbeatReceiver(inputRpcEnv: RpcEnv) extends RpcEndpoint {
  override def receive: PartialFunction[Any, Unit] = ???

  override def receiveAndReply(): PartialFunction[Any, Unit] = ???
}
