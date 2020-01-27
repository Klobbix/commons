package com.klobbix.taskrunner;

public interface Task {
    void execute();

    default boolean activate() {
        return true;
    }
}
