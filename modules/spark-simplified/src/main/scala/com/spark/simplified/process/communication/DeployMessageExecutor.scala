package com.spark.simplified.process.communication

sealed trait DeployMessage extends Serializable

case class LaunchExecutor() extends DeployMessage

case class KillExecutor() extends DeployMessage
case class KillExecutors() extends DeployMessage
case class ExecutorAdded() extends DeployMessage
case class ExecutorUpdated() extends DeployMessage
