package com.klobbix.reflection;

import java.lang.reflect.Field;

public class Fields {

    public static void setField(Object obj, String name, Object newObj) {
        try {
            Field t = obj.getClass().getDeclaredField(name);
            t.setAccessible(true);
            t.set(obj, newObj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
