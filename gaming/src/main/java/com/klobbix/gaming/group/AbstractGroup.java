package com.klobbix.gaming.group;

import java.util.LinkedList;
import java.util.List;

public class AbstractGroup<T> implements Group<List<T>, T> {

    private List<T> members = new LinkedList<>();
    private int maxSize;

    protected AbstractGroup() {
        this.maxSize = Integer.MAX_VALUE;
    }

    protected AbstractGroup(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public List<T> getMembers() {
        return members;
    }

    @Override
    public boolean addMember(T t) {
        if (members.size() >= maxSize) return false;
        return members.add(t);
    }

    @Override
    public boolean removeMember(T t) {
        return members.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return members.contains(t);
    }

    @Override
    public void removeAll() {
        members.clear();
    }
}
