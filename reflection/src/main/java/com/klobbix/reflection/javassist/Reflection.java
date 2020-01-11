package com.klobbix.reflection.javassist;

import javassist.*;

public class Reflection {

	private ClassPool pool;

	public Reflection() {
		pool = ClassPool.getDefault();
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

	public CtClass getClass(String name) {
		try {
			return pool.get(name);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Writer writer() {
		return new Writer(pool);
	}

	public QuickWriter quickWriter(String name) {
		return new QuickWriter(name);
	}

	public Loader loader() {
		return new Loader(pool);
	}

	public Modifier modify(CtClass cc) {
		return new Modifier(pool, cc);
	}

	public Modifier modify(byte[] bytes, String name) {
		ClassPath cp = pool.insertClassPath(new ByteArrayClassPath(name, bytes));
		CtClass cc = null;
		try {
			cc = pool.get(name);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
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
