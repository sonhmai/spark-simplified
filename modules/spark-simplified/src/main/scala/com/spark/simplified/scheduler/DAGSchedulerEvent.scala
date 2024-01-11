package com.spark.simplified.scheduler

import com.spark.simplified.work.task.Task


sealed trait DAGSchedulerEvent {

}

private[scheduler] case class CompletionEvent(
                            task: Task[_],
                            result: Any
                                             ) extends DAGSchedulerEvent
