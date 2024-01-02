package com.spark.simplified.rpc

import scala.concurrent.Future

/**
 * A reference to RpcEndpoint in a RpcEnv.
 * RpcEndpointRef os a serializable entity that can be sent over a network
 * or save it for later use.
 */
trait RpcEndpointRef {
  // send message without waiting for response
  def send(message: Any): Unit

  // send message and receive a response.
  // Future will be completed with response from endpoint -> handled async.
  def ask(message: Any): Future[Any]
}
