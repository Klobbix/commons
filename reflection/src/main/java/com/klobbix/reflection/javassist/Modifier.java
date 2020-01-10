package com.klobbix.reflection.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import java.io.IOException;

public class Modifier {

    private final CtClass cc;
    private final ClassPool pool;

    public Modifier(ClassPool pool, CtClass cc) {
        this.pool = pool;
        this.cc = cc;
    }

    /**
     * Defrosts a frozen class. A class will become frozen if toClass(), toBytecode(), or write() is called.
     * Any further modification to the class will only work if this method is called.
     */
    public void defrost() {
        cc.defrost();
    }

    public Class<?> toClass() {
        try {
            return cc.toClass();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] toBytecode() {
        try {
            return cc.toBytecode();
        } catch (IOException | CannotCompileException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write() {
        try {
            cc.writeFile();
        } catch (NotFoundException | IOException | CannotCompileException e) {
            e.printStackTrace();
        }
    }

    public void setSuper(String name) {
        try {
            cc.setSuperclass(pool.get(name));
        } catch (CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}
