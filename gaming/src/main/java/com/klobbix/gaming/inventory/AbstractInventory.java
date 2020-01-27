package com.klobbix.gaming.inventory;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractInventory<T> implements Inventory<List<T>, T> {

    private int maxSize;
    private List<T> contents = new LinkedList<>();

    protected AbstractInventory() {
        this.maxSize = Integer.MAX_VALUE;
    }

    protected AbstractInventory(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public List<T> getContents() {
        return contents;
    }

    @Override
    public boolean add(T t) {
        if (contents.size() >= getMaxSize())
            return false;
        return contents.add(t);
    }

    @Override
    public boolean remove(T t) {
        return contents.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return contents.contains(t);
    }

    @Override
    public void removeAll() {
        contents.clear();
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }
}
