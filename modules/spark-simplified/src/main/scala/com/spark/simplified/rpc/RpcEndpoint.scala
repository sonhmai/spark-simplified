package com.spark.simplified.rpc

// an object that can receive RPC messages
trait RpcEndpoint {
  val rpcEnv: RpcEnv
  def receive: PartialFunction[Any, Unit]
  def onStop(): Unit
}
