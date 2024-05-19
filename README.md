# spark-simplified
A Simplified Version of Spark for Learning and Fun

## Main Modules

``` 
=== compute
deploy
process
thread

==== abstraction
sql
work 
  job
  stage
  task
rdd

=== io: networking, memory, storage
rpc
memory
storage
```

Deploy
  - Master
    - a JVM process runs on Master node.
    - responsible for resource allocation and scheduling across worker nodes.
  - Worker
    - a JVM process launched 1 per worker node responsible for
      - managing lifecycle of executors
      - handling task assignment to executors and coordination
      - reporting status back to cluster manager
  - History
    - a JVM process for history server with threads:
      - main thread
      - shutdown hook thread
      - application provider threads
      - jetty server threadpool
      - ...

Process
  - `Executor`
    - a JVM process for executing tasks. 
    - one or more executors can be launched on a worker node.
  - `Driver`
    - a JVM process running the `main()` of the developed Spark app.
  
Thread
  - DAGScheduler
    - `dag-scheduler-message`: single thread
    - `dag-scheduler-event-loop`
    - `shuffle-merge-finalizer`: thread pool as merge finalization can take some time.
  - Heartbeater
    - `driver-heartbeat`
    - `executor-heartbeat`
  - executor
    - `ExecutorThreadPool` (worker thread pool)
    - `task-reaper`
  - bus: ListenerBus
- rpc
  - remote procedure call layer that node communication is built upon.

## Structure

sql
    execution -> physical plan
        datasources
            FileFormat -> trait to read/write files from/to InternalRow format
            parquet

## RPC

https://books.japila.pl/apache-spark-internals/rpc/

