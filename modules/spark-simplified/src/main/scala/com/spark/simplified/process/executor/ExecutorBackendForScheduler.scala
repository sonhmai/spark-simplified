package com.spark.simplified.process.executor

import com.spark.simplified.work.task.TaskState.TaskState

import java.nio.ByteBuffer

/**
 * A pluggable interface used by the Executor to send updates to the cluster scheduler.
 */
trait ExecutorBackendForScheduler {
  def statusUpdate(taskId: Long, state: TaskState, data: ByteBuffer): Unit

}
