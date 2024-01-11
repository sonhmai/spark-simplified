package com.spark.simplified.scheduler

import com.spark.simplified.work.task.ShuffleMapTask

class DAGScheduler {

  private[scheduler] def handleTaskCompletion(event: CompletionEvent): Unit = {
    val task = event.task

    task match {
      // ShuffleMapTask completed, now handle it within ShuffleMapStage
      // and write output for next stages consumption
      case shuffleMapTask: ShuffleMapTask => {
        // updating stage state for task completion
        // record the output location of completed task
        // If all tasks in the stage are complete, checks if shuffle merge finalization is needed
        // and either schedules it or processes the stage completion.
      }
    }
  }


}
