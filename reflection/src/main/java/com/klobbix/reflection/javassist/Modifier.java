package com.klobbix.reflection.javassist;

import javassist.*;

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

	public void setInterfaces(CtClass[] interfaces) {
		cc.setInterfaces(interfaces);
	}

	public void setName(String name) {
		cc.setName(name);
	}

	public CtClass clazz() {
		return cc;
	}

	public void addModifier(int modifier) {
		cc.setModifiers(cc.getModifiers() & modifier);
	}

	public void removeModifier(int modifier) {
		cc.setModifiers(cc.getModifiers() & ~modifier);
	}

	public void addField(PrimitiveType type, String name, int modifier) {
		try {
			CtField f = new CtField(type.getType(), name, cc);
			f.setModifiers(modifier);
			cc.addField(f);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public void addField(PrimitiveType type, String name, int modifier, String initialValue) {
		try {
			CtField f = new CtField(type.getType(), name, cc);
			f.setModifiers(modifier);
			cc.addField(f, initialValue);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public void addMethod(String content) {
		try {
			CtNewMethod.make(content, cc);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public void addMethod(CtClass returnType, String name, CtClass[] parameters) {
		try {
			CtMethod method = new CtMethod(returnType, name, parameters, cc);
			cc.addMethod(method);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public FieldWrapper field(CtField field) {
		return new FieldWrapper(field);
	}

	public FieldWrapper field(String name) {
		return new FieldWrapper(getField(name));
	}

	public MethodWrapper method(CtMethod method) {
		return new MethodWrapper(method);
	}

	public MethodWrapper method(String name) {
		return new MethodWrapper(getMethod(name));
	}

	public void removeField(String name) {
		try {
			cc.removeField(getField(name));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public void removeField(CtField field) {
		try {
			cc.removeField(field);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public void removeMethod(String name) {
		try {
			cc.removeMethod(getMethod(name));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public void removeMethod(CtMethod method) {
		try {
			cc.removeMethod(method);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public CtField getField(String name) {
		try {
			return cc.getDeclaredField(name);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CtMethod getMethod(String name) {
		try {
			return cc.getDeclaredMethod(name);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
