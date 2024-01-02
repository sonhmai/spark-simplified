package com.spark.simplified.deploy.communication

import com.spark.simplified.util.SerializableBuffer
import com.spark.simplified.work.task.TaskState.TaskState

sealed trait ClusterMessage extends Serializable

case class LaunchTask(data: SerializableBuffer) extends ClusterMessage

case class StatusUpdate(
    taskId: Long,
    state: TaskState
) extends ClusterMessage
