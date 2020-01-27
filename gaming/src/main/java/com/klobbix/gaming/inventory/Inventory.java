package com.klobbix.gaming.inventory;

public interface Inventory<K, T> {

    int getMaxSize();

    K getContents();

    boolean add(T t);

    boolean remove(T t);

    void removeAll();

    boolean contains(T t);

    boolean isEmpty();

}
