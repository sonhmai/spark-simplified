package com.spark.simplified.rpc

import org.apache.spark.rpc.ThreadSafeRpcEndpoint

/** An end point for the RPC that defines what functions to trigger given a message.
  *
  * It is guaranteed that `onStart`, `receive` and `onStop` will be called in sequence.
  *
  * The life-cycle of an endpoint is:
  *
  * {@code constructor -> onStart -> receive* -> onStop}
  *
  * Note: `receive` can be called concurrently. If you want `receive` to be thread-safe, please use
  * [[ThreadSafeRpcEndpoint]]
  *
  * If any error is thrown from one of [[RpcEndpoint]] methods except `onError`, `onError` will be
  * invoked with the cause. If `onError` throws an error, [[RpcEnv]] will ignore it.
  */
trait RpcEndpoint {

  // PartialFunction[Any, Unit] is a func takes Any as input and output Unit

  /** Process messages from `RpcEndpointRef.send` or `RpcCallContext.reply`. If receiving a
    * unmatched message, `SparkException` will be thrown and sent to `onError`.
    */
  def receive: PartialFunction[Any, Unit]

  /** Process messages from `RpcEndpointRef.ask`. If receiving a unmatched message,
    * `SparkException` will be thrown and sent to `onError`.
    */
  def receiveAndReply(): PartialFunction[Any, Unit]
}
