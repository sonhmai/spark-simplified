package com.spark.simplified.process.executor

import com.spark.simplified.scheduler.TaskDescription

class TaskRunner(
    executorBackend: ExecutorBackendForScheduler,
    taskDescription: TaskDescription
) extends Runnable {

  override def run(): Unit = {}

}
