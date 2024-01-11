package com.spark.simplified.work.stage

import com.spark.simplified.data.rdd.RDD
import com.spark.simplified.work.task.TaskContext

/**
  * ResultStages apply a function on some partitions of an RDD to compute the result of an action.
  * The ResultStage object captures the function to execute, `func`, which will be applied to each
  * partition, and the set of partition IDs, `partitions`. Some stages may not run on all partitions
  * of the RDD, for actions like first() and lookup().
  */
class ResultStage(
                   id: Int,
                   rdd: RDD[_],
                   val func: (TaskContext, Iterator[_]) => _,
                   parents: List[Stage]
                 ) extends Stage {

}
