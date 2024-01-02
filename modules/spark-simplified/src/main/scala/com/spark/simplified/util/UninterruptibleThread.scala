package com.spark.simplified.util

/**
 * A special Thread that provides "runUninterruptibly" to allow running codes without being
 * interrupted by `Thread.interrupt()`. If `Thread.interrupt()` is called during runUninterruptibly
 * is running, it won't set the interrupted status. Instead, setting the interrupted status will be
 * deferred until it's returning from "runUninterruptibly".
 *
 * See spark.util.UninterruptibleThread
 *
 * Note: "runUninterruptibly" should be called only in `this` thread.
 */
class UninterruptibleThread(
    target: Runnable,
    name: String
) extends Thread(target, name) {

}
