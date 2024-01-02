package com.spark.simplified.deploy.executor
import com.spark.simplified.MemoryManager

/** A class that polls executor metrics, and tracks their peaks per task and per stage.
  * Each executor keeps an instance of this class.
  * The poll method polls the executor metrics, and is either run in its own thread or
  * called by the executor's heartbeater thread, depending on configuration.
  * The class keeps two ConcurrentHashMaps that are accessed (via its methods) by the
  * executor's task runner threads concurrently with the polling thread. One thread may
  * update one of these maps while another reads it, so the reading thread may not get
  * the latest metrics, but this is ok.
  * We track executor metric peaks per stage, as well as per task. The per-stage peaks
  * are sent in executor heartbeats. That way, we get incremental updates of the metrics
  * as the tasks are running, and if the executor dies we still have some metrics. The
  * per-task peaks are sent in the task result at task end. These are useful for short
  * tasks. If there are no heartbeats during the task, we still get the metrics polled
  * for the task.
  *
  * @param memoryManager the memory manager used by the executor.
  * @param pollingInterval the polling interval in milliseconds.
  */
class ExecutorMetricsPoller(
    memoryManager: MemoryManager,
    pollingInterval: Long
) {}
