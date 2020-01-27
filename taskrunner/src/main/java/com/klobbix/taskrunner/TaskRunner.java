package com.klobbix.taskrunner;

import java.util.concurrent.TimeUnit;

/**
 * Wrapper class for the TaskThread class. This class is to be used to create Tasks for the TaskManager.
 */
public class TaskRunner {

	private final TaskThread taskThread;

	public TaskRunner() {
		taskThread = new TaskThread();
	}

	/**
	 * End the task after a certain amount of iterations.
	 *
	 * @param iterations The amount of iterations to run before ending the task
	 */
	public TaskRunner endAfter(int iterations) {
		taskThread.endAfter(iterations);
		return this;
	}

	/**
	 * End the task after a certain amount of time has passed.
	 *
	 * @param unit  The time unit (ms, seconds, days, etc)
	 * @param value The length of time according to the unit
	 */
	public TaskRunner endAfter(TimeUnit unit, int value) {
		taskThread.endAfter(unit, value);
		return this;
	}

	/**
	 * Sets the timer interval on how often to run the task.
	 *
	 * @param value The length of time in seconds
	 * @return The TaskRunner object
	 */
	public TaskRunner interval(int value) {
		taskThread.interval(TimeUnit.SECONDS, value);
		return this;
	}

	/**
	 * Sets the timer interval on how often to run the task.
	 *
	 * @param unit  The time unit (ms, seconds, days, etc)
	 * @param value The length of time according to the unit
	 * @return The TaskRunner object
	 */
	public TaskRunner interval(TimeUnit unit, int value) {
		taskThread.interval(unit, value);
		return this;
	}

	/**
	 * Returns if the task is still executing.
	 *
	 * @return True if executing, otherwise false
	 */
	public boolean isRunning() {
		return taskThread.isRunning();
	}

	/**
	 * Assigns the task for the TaskRunner to execute.
	 *
	 * @param task The task to run
	 * @return The TaskRunner object
	 */
	public TaskRunner task(Task task) {
		taskThread.task(task);
		return this;
	}

	/**
	 * Kills the task.
	 */
	public void kill() {
		taskThread.kill();
	}

	/**
	 * Returns the TaskThread associated with the the task to be run.
	 *
	 * @return The TaskThread object
	 */
	public TaskThread getTaskThread() {
		return taskThread;
	}

}
