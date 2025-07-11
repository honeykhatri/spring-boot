
**Executor Framework in Spring boot**

The Executor framework in Spring Boot is a powerful way to manage asynchronous task execution using thread pools.
It builds on Java’s ExecutorService and integrates seamlessly with Spring’s @Async support.
Here's how it works and how you can use it effectively

Before that it is required to understand 
**What is difference between Synchronous and Asynchronous tasks?**

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

**🧵 What Is the Executor Framework?**
At its core, the Executor framework provides a way to decouple task submission from task execution.
Instead of manually creating threads, you submit tasks to an executor, which manages a pool of threads behind the scenes.

**⚙️ Common Executor Types**

Spring Boot typically uses these from java.util.concurrent.Executors:
- **FixedThreadPool**: A fixed number of threads are created and reused for executing tasks.
  
  **_Configuration_**

  executor.setCorePoolSize(5);
  executor.setMaxPoolSize(10);
  executor.setQueueCapacity(100);

  **_Use Cases_**
* Suitable for CPU bound tasks
* When you want to limit concurrency to a fixed number.

- **CachedThreadPool**: Creates new threads as needed and reuses previously constructed threads when available.
  
  **_Configuration_**

  executor.setCorePoolSize(0);
  executor.setMaxPoolSize(Integer.MAX_VALUE);
  executor.setQueueCapacity(0);

  **_Use Cases_**
 * For short-lived asynchronous tasks.
 * Suitable for I/O-bound operations with unpredictable load.
   **Note** :  It can lead to high memory usage if too many threads are created.

- **ScheduledThreadPool**: For delayed or periodic tasks.
- Spring provides @Scheduled for this, but you can also use ScheduledThreadPoolExecutor.

  **_Use Cases_**
 * Periodic tasks like cleanup jobs, polling etc.

⚙️ **Key ThreadPoolExecutor Parameters**

1. **corePoolSize**

**Definition** : The number of threads to keep in the pool, even if they are idle.

**Behavior** : The Threads are always alive and ready to process tasks

**Example** : If corePoolSize = 5, the pool will always try to keep 5 threads running.

3. **maxPoolSize** 

**Definition** : The maximum no. of threads allowed in the pool.

**Behavior** : If the task queue is full and all the core threads are busy, new threads
   (up to maxPoolSize) will be created.

**Example** : If maxPoolSize = 10, the pool can grow to 10 threads under heavy load.

4. **queueCapacity**

**Definition** : The size of the queue that holds tasks before they are executed.

**Behavior** : 
If the number of active threads <  corePoolSize, a new thread is created.
If the number of active threads >= corePoolSize, the task is added to the queue.
If the queue is full & number of threads < maxPoolSize, a new thread is created.
If the queue is full & number of threads < maxPoolSiz, the task is rejected.

5. **keepAliveTime**

**Definition** : Time that excess idle threads(beyond corePoolSize) will wait for new tasks
 before terminating

**Default** : 60 Seconds

**Use case** : Helps reduce resource usage when the load decreases.

6. **threadNamePrefix**

**Definition** : Prefix for thread names in the pool.

**Use case** : Useful for debugging and monitoring.

?? **How it works together**

Let's say if we configure

executor.setCorePoolSize(5);
executor.setMaxPoolSize(10);
executor.setQueueCapacity(50);

Here is what happens when tasks arrive:
1. **Tasks 1-5** : New threads are created (up to corePoolSize)
2. **Tasks 6-55** : Tasks are queued (up to queue capacity)
3. **Tasks 56-60** : New threads are created (up to maxPoolSize)
4. **Tasks 61+** : Rejected.


