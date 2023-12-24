package com.spark.simplified.rpc

object RpcExample {
  def main(args: Array[String]): Unit = {
    val rpcEnv: RpcEnv = new NettyRpcEnv
//    val endpoint: RpcEndpointRef = rpcEnv.setupEndpoint("hello", new HelloEndpoint(rpcEnv))

//    endpoint.send("Hello")
  }
}
