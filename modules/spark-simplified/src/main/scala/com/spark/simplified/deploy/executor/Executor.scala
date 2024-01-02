package com.spark.simplified.deploy.executor

import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.spark.simplified.SparkEnv
import com.spark.simplified.scheduler.TaskDescription
import com.spark.simplified.util.thread.Heartbeater

import java.util.concurrent.{ConcurrentHashMap, Executors, ThreadPoolExecutor}

/** Executor is backed by a thread pool to run task.
  */
class Executor(
    executorId: String,
    executorHostname: String,
    env: SparkEnv
) {

  private val HEARTBEAT_INTERVAL_MS = 10
  // current list of running tasks mapped by taskId
  private val runningTasks = new ConcurrentHashMap[Long, TaskRunner]

  private var workerThreadPool: ThreadPoolExecutor = _
  // for reporting heartbeat and metrics for active tasks to driver
  private var executorHeartbeater: Heartbeater = _

  /** Difference to Spark:
    *  - Executor in Spark init thread pool for worker, background threads when class instance is created.
    *  - Here the process of init is put to start() to make it clear where the caller wants to
    *  start the Executor process.
    */
  def start(): Unit = {
    workerThreadPool = createWorkerThreadPool()

    executorHeartbeater = createHeartbeater()
    executorHeartbeater.start()

  }

  def stop(): Unit = {}

  // executorBackend for giving status report of executor to Scheduler
  def launchTask(executorBackend: ExecutorBackendForScheduler, taskDescription: TaskDescription): Unit = {
    val taskId = taskDescription.taskId
    val taskRunner = new TaskRunner(executorBackend, taskDescription)
    runningTasks.put(taskId, taskRunner)

    workerThreadPool.execute(taskRunner)
  }

  private def createWorkerThreadPool(): ThreadPoolExecutor = {
    val threadFactory = new ThreadFactoryBuilder()
      .setDaemon(true)
      .setNameFormat("Executor task launch worker-%d")
      // should use UninterruptibleThread instead of Thread, but keeping things simple for now
      .setThreadFactory((r: Runnable) => new Thread(r, "unused"))
      .build()
    Executors.newCachedThreadPool(threadFactory).asInstanceOf[ThreadPoolExecutor]
  }

  private def createHeartbeater(): Heartbeater = new Heartbeater(
    () => reportHeartbeat(),
    "executor-heartbeater",
    HEARTBEAT_INTERVAL_MS
  )

  private def reportHeartbeat(): Unit = {}
}
