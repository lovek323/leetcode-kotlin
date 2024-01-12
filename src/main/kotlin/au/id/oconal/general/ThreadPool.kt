package au.id.oconal.general

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

class ThreadPool(threadCount: Int, maxTaskCount: Int) {
  /**
   * When the thread pool has been shutdown, set this boolean which will stop any new tasks being
   * added to the queue or run.
   */
  private val isShutdown = AtomicBoolean(false)
  /**
   * Create the threads up-front and have the threads take items from the queue and run them as long
   * as this thread pool has not been shutdown.
   */
  private val workers: Array<Thread> =
    Array(threadCount) {
      Thread { while (!isShutdown.get()) taskQueue.take().run() }.apply { this.start() }
    }

  /**
   * A blocking queue will ensure that we only have at most `maxTaskCount` tasks in the queue and
   * will block the calling thread until we can accept a new task.
   */
  private val taskQueue: ArrayBlockingQueue<Runnable> = ArrayBlockingQueue(maxTaskCount)

  /** Shutdown the thread pool: don't accept any new tasks and don't run any new tasks. */
  fun shutdown() = isShutdown.set(true)

  fun submit(task: Runnable) {
    if (isShutdown.get()) throw IllegalArgumentException("Thread pool is shutdown")
    taskQueue.put(task)
  }
}
