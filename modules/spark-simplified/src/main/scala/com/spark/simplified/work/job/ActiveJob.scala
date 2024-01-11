package com.spark.simplified.work.job

import com.spark.simplified.work.stage.Stage

/**
  * A running job in the DAGScheduler.
  *
  * Jobs can be of two types:
  * 1. a result job
  * which computes a ResultStage to execute an action
  * 2. a map-stage job
  * which computes the map outputs for a ShuffleMapStage before any downstream stages are submitted.
  *
  * The latter is used for adaptive query planning, to look at map output statistics before submitting later stages.
  * We distinguish between these two types of jobs using the finalStage field of this class.
  *
  * @param jobId      A unique ID for this job
  * @param finalStage The stage that this job computes
  *                   (either a ResultStage for an action or a ShuffleMapStage for submitMapStage).
  */
class ActiveJob(
                 val jobId: Int,
                 val finalStage: Stage
               ) {

  val numPartitions: Int = 10

  /** Which partitions of the stage have finished */
  val finished: Array[Boolean] = Array.fill[Boolean](numPartitions)(false)

}
