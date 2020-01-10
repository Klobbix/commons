package com.klobbix.reflection.javassist;

import javassist.*;

public class FieldWrapper {

	private final CtField field;

	public FieldWrapper(CtField field) {
		this.field = field;
	}

	public CtField get() {
		return field;
	}

	public void addModifier(int modifier) {
		field.setModifiers(field.getModifiers() & modifier);
	}

	public void removeModifier(int modifier) {
		field.setModifiers(field.getModifiers() & ~modifier);
	}

	public void hide() {
		field.setName(field.getName() + "_ignored");
		field.setModifiers(ModifierType.PRIVATE);
	}
}
