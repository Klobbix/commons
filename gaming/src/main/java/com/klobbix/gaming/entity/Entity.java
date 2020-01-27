package com.klobbix.gaming.entity;

import java.util.Map;

public interface Entity {

    Map<String, Object> getAttributes();
    void addAttribute(String key, Object value);
}
