package com.spark.simplified.scheduler

import java.nio.ByteBuffer

class TaskDescription(
    val taskId: Long,
    val attemptNumber: Int,
    val executorId: String,
    val name: String,
    val partitionId: Int,
    val serializedTask: ByteBuffer
) {

  override def toString: String = s"TaskDescription($name)"
}

object TaskDescription {
  def encode(taskDescription: TaskDescription): ByteBuffer = ???

  def decode(byteBuffer: ByteBuffer): TaskDescription = ???
}