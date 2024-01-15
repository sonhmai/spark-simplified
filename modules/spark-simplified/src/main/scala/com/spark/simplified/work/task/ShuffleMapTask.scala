package com.spark.simplified.work.task

import com.spark.simplified.util.Logging

/** A ShuffleMapTask divides the elements of an RDD into multiple buckets (based on a partitioner
  * specified in the ShuffleDependency).
  */
class ShuffleMapTask(
    stageId: Int,
    stageAttemptId: Int,
    partitionId: Int,
    jobId: Option[Int] = None,
    appId: Option[String] = None
) extends Task(stageId, stageAttemptId, partitionId, jobId, appId) with Logging {
  override def runTask(context: TaskContext): Nothing = ???
}
