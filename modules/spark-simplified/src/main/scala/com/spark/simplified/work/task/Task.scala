package com.spark.simplified.work.task

/**
  *
  * @param stageId
  * @param stageAttemptId
  * @param partitionId
  * @param jobId
  * @param appId
  * @tparam T Type of the result of running the task
  */
abstract class Task[T] (
                   val stageId: Int,
                   val stageAttemptId: Int,
                   val partitionId: Int,
                   val jobId: Option[Int] = None,
                   val appId: Option[String] = None
                   ) {

  @transient var context: TaskContext = _  // to be initialized in run()

  /**
    * Called by Executor to run this task.
    *
    * @return the result of task along with updates of Accumulators
    */
  final def run(): T = {
    runTask(context)
  }

  def runTask(context: TaskContext): T

}
