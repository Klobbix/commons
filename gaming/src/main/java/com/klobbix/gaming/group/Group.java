package com.klobbix.gaming.group;

public interface Group<K, T> {

    K getMembers();

    boolean addMember(T t);

    boolean removeMember(T t);

    boolean contains(T t);

    void removeAll();
}
