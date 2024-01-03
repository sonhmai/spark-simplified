# spark-simplified
A Simplified Version of Spark for Learning and Fun

## Main Modules
- Deploy
  - Master
    - a JVM process runs on Master node.
    - responsible for resource allocation and scheduling across worker nodes.
  - Worker
    - a JVM process launched 1 per worker node responsible for
      - managing lifecycle of executors
      - handling task assignment to executors and coordination
      - reporting status back to cluster manager
  - History
    - a JVM process for history server.
- Process
  - Executor
    - a JVM process for executing tasks. 
    - one or more executors can be launched on a worker node.
  - Driver
    - ...
- Thread
  - DAGSchedulerEventLoop
  - Scheduler
  - Heartbeater
  - executor
    - ExecutorThreadPool (worker thread pool)
    - TaskReaper
  - bus: ListenerBus
- rpc
  - remote procedure call layer that node communication is built upon.


## RPC

https://books.japila.pl/apache-spark-internals/rpc/

