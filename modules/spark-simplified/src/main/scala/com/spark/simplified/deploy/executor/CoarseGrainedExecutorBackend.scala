package com.spark.simplified.deploy.executor

import com.spark.simplified.SparkEnv
import com.spark.simplified.deploy.communication.{LaunchTask, StatusUpdate}
import com.spark.simplified.rpc.{RpcEndpointRef, RpcEnv}
import com.spark.simplified.scheduler.TaskDescription
import com.spark.simplified.util.Logging
import com.spark.simplified.work.task.TaskState.TaskState

import java.nio.ByteBuffer

/** "coarse-grained" refers to the level of granularity at which Spark tasks are executed within the executor processes.
  * Tasks executed within the executor processes are relatively larger in size and operate
  * on coarse-grained data partitions. In other words, each task typically processes
  * a significant portion of data within a partition.
  *
  * Fine-grained tasks often involve processing individual records or small subsets of data within a partition.
  *
  * Advantages
  * - reduces the overhead of task scheduling and communication between the driver and the executor processes.
  * - by processing larger chunks of data in a single task, it can minimize the amount of data shuffling
  * and network communication required between tasks.
  *
  * @param rpcEnv
  * @param driverUrl
  * @param env
  */
class CoarseGrainedExecutorBackend(
    rpcEnv: RpcEnv,
    driverUrl: String,
    env: SparkEnv
) extends ExecutorBackendForScheduler with Logging {

  var executor: Executor = _
  @volatile var driver: Option[RpcEndpointRef] = None

  override def statusUpdate(taskId: Long, state: TaskState, data: ByteBuffer): Unit = {
    val statusUpdateMessage = StatusUpdate(taskId, state)
    driver match {
      case Some(driverRef) => driverRef.send(statusUpdateMessage)
      case None            => logWarning(s"Drop $statusUpdateMessage because has not yet connected to driver")
    }
  }

  def receive: PartialFunction[Any, Unit] = {
    case LaunchTask(data) => {
      val taskDesc = TaskDescription.decode(data.value)
      executor.launchTask(this, taskDesc)
    }
  }
}
