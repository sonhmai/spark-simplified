package com.spark.simplified.deploy.executor
import com.spark.simplified.scheduler.TaskDescription

class TaskRunner(
    executorBackend: ExecutorBackendForScheduler,
    taskDescription: TaskDescription
) extends Runnable {

  override def run(): Unit = {}

}
