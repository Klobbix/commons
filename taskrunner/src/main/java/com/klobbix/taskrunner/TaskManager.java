package com.klobbix.taskrunner;

import java.util.LinkedList;
import java.util.List;

public class TaskManager {

	private List<TaskRunner> tasks;

	public TaskManager() {
		tasks = new LinkedList<>();
	}

	/**
	 * Adds a task to the TaskManager.
	 *
	 * @param task The TaskRunner task
	 * @return The TaskManager
	 */
	public void add(TaskRunner task) {
		tasks.add(task);
		task.getTaskThread().start();
	}

	/**
	 * Adds multiple tasks to the TaskManager.
	 *
	 * @param tasks The TaskRunner tasks
	 * @return The TaskManager
	 */
	public void add(TaskRunner... tasks) {
		for (TaskRunner task : tasks) {
			this.tasks.add(task);
			task.getTaskThread().start();
		}
	}

	/**
	 * Returns the tasks added to the TaskManager.
	 *
	 * @return The list of tasks
	 */
	public List<TaskRunner> getTasks() {
		return tasks;
	}

	/**
	 * Kills a given task.
	 *
	 * @param task The task to kill
	 */
	public void kill(TaskRunner task) {
		tasks.forEach(t -> {
			if (task.getTaskThread().getId() == t.getTaskThread().getId()) {
				t.kill();
			}
		});
		tasks.remove(task);
	}

	/**
	 * Kills all tasks in the TaskManager.
	 */
	public void killAll() {
		tasks.forEach(TaskRunner::kill);
		tasks.clear();
	}
}
