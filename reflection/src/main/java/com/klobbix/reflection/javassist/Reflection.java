package com.klobbix.reflection.javassist;

import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Reflection {

    private ClassPool pool;
    private Writer writer;

    public Reflection() {
        pool = ClassPool.getDefault();
        writer = new Writer(pool);
    }

    public void insertClassPath(ClassPath cp) {
        pool.insertClassPath(cp);
    }

    public void insertClassPath(String path) {
        try {
            pool.insertClassPath(path);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public Writer writer() {
        return writer;
    }

    public Modifier modify(CtClass cc) {
        return new Modifier(pool, cc);
    }

    public Modifier modify(String name) {
        CtClass clazz = null;
        try {
            clazz = pool.get(name);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return new Modifier(pool, clazz);
    }

}
