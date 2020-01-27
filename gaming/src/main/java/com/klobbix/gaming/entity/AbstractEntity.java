package com.klobbix.gaming.entity;

import java.util.HashMap;
import java.util.Map;

public class AbstractEntity implements Entity {

    Map<String, Object> attributes = new HashMap<>();

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }
}
