package com.spark.simplified.rpc

/**
 * An RPC environment. It provides methods for setting up and tearing down RPC endpoints.
 */
trait RpcEnv {
  def setupEndpoint(name: String, endpoint: RpcEndpoint): RpcEndpointRef

  // stops the given RPC endpoint. After this method is called, the endpoint will not receive any more messages.
  def stop(endpoint: RpcEndpointRef): Unit

  // After this method is called, no more endpoints can be created and all existing endpoints will be stopped.
  def shutdown(): Unit
}
