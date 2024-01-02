package com.spark.simplified.util.thread
import java.util.concurrent.TimeUnit

/** Creates a heartbeat thread which will call the specified reportHeartbeat function at
  * intervals of intervalMs.
  *
  * @param reportHeartbeat the heartbeat reporting function to call.
  * @param name the thread name for the heartbeater.
  * @param intervalMs the interval between heartbeats.
  */
class Heartbeater(
    reportHeartbeat: () => Unit,
    name: String,
    intervalMs: Long
) {
  // Executor for the heartbeat task
  private val heartbeater = ThreadUtils.newDaemonSingleThreadScheduledExecutor(name)

  /** Schedules a task to report a heartbeat. */
  def start(): Unit = {
    // Wait a random interval so the heartbeats don't end up in sync
    val initialDelay = intervalMs + (math.random * intervalMs).asInstanceOf[Int]

    val heartbeatTask = new Runnable() {
      // keeping things simple by ignoring any exception that can caused by reportHeartbeat
      override def run(): Unit = reportHeartbeat()
    }
    heartbeater.scheduleAtFixedRate(heartbeatTask, initialDelay, intervalMs, TimeUnit.MILLISECONDS)
  }

  /** Stops the heartbeat thread. */
  def stop(): Unit = {
    heartbeater.shutdown()
    heartbeater.awaitTermination(10, TimeUnit.SECONDS)
  }

}
