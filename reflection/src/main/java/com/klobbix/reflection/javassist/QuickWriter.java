package com.klobbix.reflection.javassist;

import javassist.bytecode.*;

import java.io.*;

public class QuickWriter {

	private String name;
	private ClassFile classFile;

	public QuickWriter(String name) {
		this.name = name;
		classFile = new ClassFile(false, name, null);
	}

	public void addInterface(String value) {
		classFile.addInterface(value);
	}

	public void addField(String name, PrimitiveType type, int modifiers) {
		try {
			FieldInfo f = new FieldInfo(classFile.getConstPool(), name, type.getBytecode());
			f.setAccessFlags(modifiers);
			classFile.addField(f);
		} catch (DuplicateMemberException e) {
			e.printStackTrace();
		}
	}

	public void addMethod(String name, PrimitiveType type, int modifiers) {
		try {
			MethodInfo m = new MethodInfo(classFile.getConstPool(), name, type.getBytecode());
			m.setAccessFlags(modifiers);
			classFile.addMethod(m);
		} catch (DuplicateMemberException e) {
			e.printStackTrace();
		}
	}

	public void write() {
		try {
			classFile.write(new DataOutputStream(new FileOutputStream(name.substring(name.lastIndexOf(".")) + ".class")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
