package com.klobbix.reflection.javassist;

import javassist.*;

public enum PrimitiveType {
	INTEGER(CtClass.intType),
	SHORT(CtClass.shortType),
	DOUBLE(CtClass.doubleType),
	FLOAT(CtClass.floatType),
	LONG(CtClass.longType),
	BOOLEAN(CtClass.booleanType),
	BYTE(CtClass.byteType),
	CHAR(CtClass.charType),
	VOID(CtClass.voidType);

	private final CtClass type;

	PrimitiveType(CtClass type) {
		this.type = type;
	}

	public CtClass getType() {
		return type;
	}
}
