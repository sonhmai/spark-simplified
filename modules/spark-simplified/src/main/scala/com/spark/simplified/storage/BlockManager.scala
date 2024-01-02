package com.spark.simplified.storage
import com.spark.simplified.MemoryManager

/** Manager running on every node (driver and executors) which provides interfaces for putting and
  * retrieving blocks both locally and remotely into various stores (memory, disk, and off-heap).
  *
  * Note that [[initialize()]] must be called before the BlockManager is usable.
  */
class BlockManager(
    memoryManager: MemoryManager
) {



  def stop(): Unit = {

  }
}
