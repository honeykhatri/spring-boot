
Executor Framework in Spring boot

Understanding what is difference between Synchronous and Asynchronous tasks

🔁 Synchronous Tasks
- One at a time: Tasks are performed one after another.
- Blocking: Each task must finish before the next one starts.
- Easy to understand: Code runs in a predictable, linear flow.
- Downside: If one task takes too long (like reading a large file or waiting for a database response), everything else has to wait.

- Example : 
- String data = fetchFromDatabase(); // blocks until data is fetched
  processData(data);                 // executes only after fetching is done

⚡ Asynchronous Tasks
- Run concurrently: Tasks can be initiated and continue independently.
- Non-blocking: The program doesn't wait; it moves on to other tasks.
- Efficient: Improves performance, especially in I/O-heavy applications.
- More complex: Requires handling callbacks, futures, or reactive streams.
- Example 
- When calling an external API, you don't want your thread blocked waiting for a response.
  CompletableFuture<String> apiResponse = CompletableFuture.supplyAsync(() -> callExternalApi());

🧵 What Is the Executor Framework?
At its core, the Executor framework provides a way to decouple task submission from task execution.
Instead of manually creating threads, you submit tasks to an executor, which manages a pool of threads behind the scenes.

⚙️ Common Executor Types
Spring Boot typically uses these from java.util.concurrent.Executors:
- FixedThreadPool: A pool with a fixed number of threads.
  executor.setCorePoolSize(5);
  executor.setMaxPoolSize(10);
  executor.setQueueCapacity(100);

- CachedThreadPool: Dynamically grows and reuses threads.
  executor.setCorePoolSize(0);
  executor.setMaxPoolSize(Integer.MAX_VALUE);
  executor.setQueueCapacity(0);

- ScheduledThreadPool: For delayed or periodic tasks.

** Key ThreadPoolExecutor Parameters

1. corePoolSize
The number of threads to keep in the pool, even if they are idle.
The Threads are always alive and ready to process tasks
If corePoolSize=5, the pool will always try to keep 5 threads running.

2. maxPoolSize 
The maximum no. of threads allowed in the pool.


