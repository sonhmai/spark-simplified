package com.spark.simplified.rpc
import scala.collection.mutable

class NettyRpcEnv extends RpcEnv {
  val endpoints = new mutable.HashMap[String, RpcEndpoint]

  override def setupEndpoint(name: String, endpoint: RpcEndpoint): RpcEndpointRef = {
    endpoints.put(name, endpoint)
    new NettyRpcEndpointRef(name, this)
  }

  override def stop(endpointRef: RpcEndpointRef): Unit = {
    require(endpointRef.isInstanceOf[NettyRpcEndpointRef])
    endpoints.remove(endpoint.name)
    endpoint.onStop()
  }

  override def shutdown(): Unit = {
    endpoints.values.foreach(endpoint => stop(endpoint))
  }

}
