package com.klobbix.taskrunner;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskThread extends Thread {

	private TimeUnit timeUnit = TimeUnit.SECONDS;
	private int timeValue = 1;
	private TimeUnit endTimeUnit;
	private int endTimeValue = 0;
	private int endIterations = 0;
	private Task task;
	private long startTime;
	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicInteger iteration = new AtomicInteger(1);

	protected TaskThread() {
		super();
	}

	/**
	 * End the task after a certain amount of time has passed.
	 *
	 * @param unit  The time unit (ms, seconds, days, etc)
	 * @param value The length of time according to the unit
	 */
	public void endAfter(TimeUnit unit, int value) {
		endTimeUnit = unit;
		endTimeValue = value;
	}

	/**
	 * End the task after a certain amount of iterations.
	 * Value must be greater than 0.
	 *
	 * @param iterations The amount of iterations to run before ending the task
	 */
	public void endAfter(int iterations) {
		if (iterations == 0)
			return;
		endIterations = iterations;
	}

	/**
	 * Sets the timer interval on how often to run the task. If no interval is set, the task will run every 1 second by default.
	 *
	 * @param unit  The time unit (ms, seconds, days, etc)
	 * @param value The length of time according to the unit
	 */
	public void interval(TimeUnit unit, int value) {
		this.timeUnit = unit;
		this.timeValue = value;
	}

	/**
	 * Returns if the task is still executing.
	 *
	 * @return True if executing, otherwise false
	 */
	public boolean isRunning() {
		return running.get();
	}

	/**
	 * Kills the thread.
	 */
	public void kill() {
		running.set(false);
		interrupt();
	}

	/**
	 * Assigns the task to run.
	 *
	 * @param task The task to run
	 */
	public void task(Task task) {
		this.task = task;
	}

	/**
	 * Pauses the thread for the specified interval set through interval(TimeUnit, value).
	 */
	private void sleep() {
		try {
			sleep(timeUnit.toMillis(timeValue));
		} catch (InterruptedException e) {
			kill();
		}
	}

	private void endCheck() {
		if (endIterations != 0) {
			int currentIteration = iteration.getAndAdd(1);
			if (currentIteration >= endIterations) {
				kill();
				return;
			}
		}
		if (endTimeUnit != null) {
			if (startTime + endTimeUnit.toMillis(endTimeValue) < System.currentTimeMillis()) {
				kill();
			}
		}
	}

	/**
	 * Runs the task.
	 */
	@Override
	public void run() {
		if (task == null) {
			System.err.println("No task was assigned");
			return;
		}
		running.set(true);
		startTime = System.currentTimeMillis();
		while (running.get()) {
			if (!task.activate()) {
				kill();
				return;
			}
			sleep();
			task.execute();
			endCheck();
		}
	}
}
