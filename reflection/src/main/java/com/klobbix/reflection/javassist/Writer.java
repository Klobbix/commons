package com.klobbix.reflection.javassist;

import javassist.ClassPool;
import javassist.CtClass;

import java.io.IOException;
import java.io.InputStream;

public class Writer {

	private ClassPool pool;

	public Writer(ClassPool pool) {
		this.pool = pool;
	}

	public CtClass createClass(InputStream inputStream) {
		try {
			return pool.makeClass(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CtClass createClass(String name) {
		return pool.makeClass(name);
	}

	public CtClass createInterface(String name) {
		return pool.makeInterface(name);
	}
}
