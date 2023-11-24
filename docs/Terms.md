# Terms

- Executor: a process launched for an application on a worker node,
that runs tasks and keeps data in memory.
- Task: a unit of work that will be sent to one executor.
- Job: a parallel computation consisting of multiple tasks that gets
spawned in response to a Spark action.
- Stage: each jobs divided into smaller sets of tasks called stages
that depends on each other. There is a new stage if there is shuffle.