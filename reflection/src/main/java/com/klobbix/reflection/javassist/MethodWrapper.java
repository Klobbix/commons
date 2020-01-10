package com.klobbix.reflection.javassist;

import javassist.*;

public class MethodWrapper {

	private final CtMethod method;

	public MethodWrapper(CtMethod method) {
		this.method = method;
	}

	/**
	 * $0, $1, $2, ...     	this and actual parameters
	 * $args 	An array of parameters. The type of $args is Object[].
	 * $$ 	All actual parameters.
	 * For example, m($$) is equivalent to m($1,$2,...)
	 * <p>
	 * $cflow(...) 	cflow variable
	 * $r 	The result type. It is used in a cast expression.
	 * $w 	The wrapper type. It is used in a cast expression.
	 * $_ 	The resulting value
	 * $sig 	An array of java.lang.Class objects representing the formal parameter types.
	 * $type 	A java.lang.Class object representing the formal result type.
	 * $class 	A java.lang.Class object representing the class currently edited.
	 *
	 * @param content
	 */
	public void insertAtStart(String content) {
		if (!content.trim().startsWith("{")) {
			content += "{" + content;
		}
		if (!content.trim().endsWith("}")) {
			content += content + "}";
		}
		try {
			method.insertBefore(content);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public void insertAtEnd(String content) {
		if (!content.trim().startsWith("{")) {
			content += "{" + content;
		}
		if (!content.trim().endsWith("}")) {
			content += content + "}";
		}
		try {
			method.insertAfter(content);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public CtMethod get() {
		return method;
	}

	public void addModifier(int modifier) {
		method.setModifiers(method.getModifiers() & modifier);
	}

	public void removeModifier(int modifier) {
		method.setModifiers(method.getModifiers() & ~modifier);
	}

	public void surroundWithTryCatch(String catchContent) {
		try {
			surroundWithTryCatch(catchContent, ClassPool.getDefault().get("java.io.Exception"));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public void surroundWithTryCatch(String catchContent, CtClass exception) {
		try {
			method.addCatch(catchContent, exception);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public void surroundWithTryCatch(String catchContent, String exception) {
		try {
			method.addCatch(catchContent, ClassPool.getDefault().get(exception));
		} catch (NotFoundException | CannotCompileException e) {
			e.printStackTrace();
		}
	}

	public void hide() {
		method.setName(method.getName() + "_ignored");
		method.setModifiers(ModifierType.PRIVATE);
	}
}
