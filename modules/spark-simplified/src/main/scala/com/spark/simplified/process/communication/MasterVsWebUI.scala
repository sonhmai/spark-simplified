package com.spark.simplified.process.communication
import com.spark.simplified.deploy.master.model.WorkerInfo

trait MasterVsWebUI

// WebUI to Master
case object WebUIRequestMasterState

// Master to WebUI
case class MasterStateToWebUIResponse(
    host: String,
    port: Int,
    workers: Array[WorkerInfo]
)
