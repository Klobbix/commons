package com.klobbix.reflection.javassist;

import javassist.*;

public enum PrimitiveType {
	INTEGER(CtClass.intType, "I"),
	SHORT(CtClass.shortType, "S"),
	DOUBLE(CtClass.doubleType, "D"),
	FLOAT(CtClass.floatType, "F"),
	LONG(CtClass.longType, "J"),
	BOOLEAN(CtClass.booleanType, "Z"),
	BYTE(CtClass.byteType, "B"),
	CHAR(CtClass.charType, "C"),
	VOID(CtClass.voidType, "V");

	private final CtClass type;
	private final String bytecode;

	PrimitiveType(CtClass type, String bytecode) {
		this.type = type;
		this.bytecode = bytecode;
	}

	public CtClass getType() {
		return type;
	}

	public String getBytecode() {
		return bytecode;
	}
}
