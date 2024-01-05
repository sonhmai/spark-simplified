package com.spark.simplified.deploy.worker

import com.spark.simplified.process.driver.DriverStateOnMaster
import com.spark.simplified.process.driver.DriverStateOnMaster.DriverStateOnMaster

class DriverRunner(
    driverId: String
) {

  // keeping track of the driver process which worker process has started
  @volatile private var process: Option[Process] = None
  @volatile private var killed = false // whether the driver process was forcibly killed
  @volatile private var finalState: Option[DriverStateOnMaster] = None

  def start(): Unit = {
    createRunnerThread().start()
  }

  private def createRunnerThread(): Thread = {
    new Thread("DriverRunner for" + driverId) {
      override def run(): Unit = {
        // add killing this driver runner to shutdown hook
        // prepare driver jars and run driver
        val exitCode = runDriver()
        // set driver runner state depending on if forcibly killed and process exit code
        finalState = if (exitCode == 0) {
          Some(DriverStateOnMaster.FINISHED)
        } else if (killed) {
          Some(DriverStateOnMaster.KILLED)
        } else {
          Some(DriverStateOnMaster.FAILED)
        }
        // notify worker of final driver state, possible exception
      }
    }
  }

  // returns exit code Int
  private def runDriver(): Int = {
    // config resource file for driver, which would be used to load resources when driver starts up
    // create ProcessBuild
    var exitCode = -1

    val commandSeq = List.empty[String]
    val processBuilder = new ProcessBuilder(commandSeq: _*)
    process = Some(processBuilder.start())

    // calling thread blocked until process is finished or killed
    exitCode = process.get.waitFor()

    exitCode
  }
}
