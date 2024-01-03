package com.spark.simplified.deploy.master.model
import com.spark.simplified.rpc.RpcEndpointRef

import scala.collection.mutable

/** Worker info maintained on Master */
class WorkerInfo(
    val id: String,
    val host: String,
    val port: Int,
    val cores: Int,
    val memory: Int,
    val endpointRef: RpcEndpointRef
) extends Serializable {

  @transient var executors: mutable.HashMap[String, ExecutorDesc] = _ // executorId -> info
  @transient var drivers: mutable.HashMap[String, DriverInfo] = _ // driverId -> info
  @transient var state: WorkerState.Value = _
  @transient var lastHeartbeat: Long = _
}
