package com.spark.simplified.rpc
import java.util.concurrent.ConcurrentHashMap

/** Dispatcher is responsible for routing RPC messages to appropriate endpoint(s).
  *
  * When an RPC request comes in, the NettyRpcEnv receives it and hands it off
  * to the Dispatcher. The Dispatcher then looks at the message, determines
  * which RpcEndpoint it's intended for, and forwards the message to that endpoint.
  *
  * This design allows the NettyRpcEnv to handle incoming connections and data reception,
  * while the Dispatcher handles the higher-level logic of deciding where to route messages.
  *
  * This separation of concerns makes the code easier to understand and maintain.
  */
class Dispatcher(nettyRpcEnv: NettyRpcEnv) {

  // used when a message needs to be dispatched to an RpcEndpoint.
  // The dispatcher looks up the RpcEndpoint by its name in this map
  // and then forwards the message to it.
  private val endpoints = new ConcurrentHashMap[String, RpcEndpoint]()

  // used when the dispatcher needs to get the RpcEndpointRef for a given RpcEndpoint,
  // for example, to respond to a message.
  private val endpointRefs = new ConcurrentHashMap[RpcEndpoint, RpcEndpointRef]()

  def registerRpcEndpoint(
      name: String,
      rpcEndpoint: RpcEndpoint
  ): RpcEndpointRef = {
    val endpointRef = new NettyRpcEndpointRef(
      RpcEndpointAddress(nettyEnv.address, name),
      nettyEnv
    )
    endpoints.put(name, endpoint)
    endpointRefs.put(endpoint, endpointRef)
    endpointRef
  }

  def unregisterRpcEndpoint(name: String): Unit = {
    endpoints.remove(name)
    endpointRefs.values().removeIf(ref => ref.name == name)
  }

  def dispatchMessage(message: RequestMessage): Unit = {
    val endpoint = endpoints.get(message.receiver.name)
    if (endpoint == null) {
      throw new SparkException(s"Could not find ${message.receiver.name}")
    }
    endpoint.receiveAndReply(new NettyRpcCallContext(message.senderAddress))
  }
}
