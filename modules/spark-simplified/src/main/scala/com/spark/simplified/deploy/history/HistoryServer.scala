package com.spark.simplified.deploy.history

import com.spark.simplified.conf.SparkConf

/**
 * A web server that renders SparkUIs of completed applications.
 *
 * For the standalone mode, MasterWebUI already achieves this functionality. Thus, the
 * main use case of the HistoryServer is in other deploy modes (e.g. Yarn or Mesos).
 *
 * The logging directory structure is as follows: Within the given base directory, each
 * application's event logs are maintained in the application's own sub-directory. This
 * is the same structure as maintained in the event log write code path in
 * EventLoggingListener.
 */
class HistoryServer(
    conf: SparkConf,
    appHistoryProvider: ApplicationHistoryProvider,
    port: Int
) {

  /** Bind to HTTP server behind this web interface */
  def bind(): Unit = {
    startJettyServer()
  }

  /** Stop server and close file system */
  def stop(): Unit = {
    appHistoryProvider.stop()
  }

  /**
   * Attempt to start a Jetty server bound to the supplied hostName:port using the given
   * context handlers.
   *
   * If the desired port number is contended, continues incrementing ports until a free port is
   * found. Return the jetty Server object, the chosen port, and a mutable collection of handlers.
   */
  private def startJettyServer(): Unit = {
    // TODO - start jetty server
  }

}

object HistoryServer {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    val port = 18080
    val appHistoryProvider = new FsHistoryProvider(conf)
    val server = new HistoryServer(conf, appHistoryProvider, port)

    server.bind()
    appHistoryProvider.start()

    // sleep the main thread until History Server is manually stopped.
    // The provider and server threads are still running in the background
    while(true) {
      Thread.sleep(Int.MaxValue)
    }
  }
}