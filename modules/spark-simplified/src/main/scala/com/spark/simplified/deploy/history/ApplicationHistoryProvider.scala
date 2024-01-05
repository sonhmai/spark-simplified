package com.spark.simplified.deploy.history

trait ApplicationHistoryProvider {

  def stop(): Unit

  /**
   * Called when the server is starting up. Implement this function to init the provider and start
   * background threads. With this function we can start provider later after it is created.
   */
  def start(): Unit
}
