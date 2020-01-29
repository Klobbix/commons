package com.klobbix.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for FastJSON's methods. This assumes that each object passed into the JSON String has fields tagged
 * with a @JSONField() annotation and contains a no-args constructor if reading from JSON.
 */
public class Json {

    public String toJsonFromObject(Object object) {
        return JSON.toJSONString(object);
    }

    public String toJsonFromObject(List<Object> objects) {
        return JSON.toJSONString(objects);
    }

    public String toJsonFromFields(Map<String, Object> values) {
        JSONObject jsonObject = new JSONObject();
        values.forEach(jsonObject::put);
        return jsonObject.toJSONString();
    }

    public String toJsonFromFields(List<Map<String, Object>> values) {
        JSONArray jsonArray = new JSONArray();
        values.forEach(val -> {
            JSONObject jsonObject = new JSONObject();
            val.forEach(jsonObject::put);
            jsonArray.add(jsonObject);
        });
        return jsonArray.toJSONString();
    }

    /**
     * Converts a JSON String into an applicable Object.
     * Be sure to cast to your object type when calling this.
     * Ensure that the Object type has a no-args constructor setup.
     *
     * @param json  The JSON String
     * @param clazz The object class
     * @return An Object represented from the JSON String
     */
    public Object fromJson(String json, Class clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * Converts a JSON String Array into an applicable list of Objects.
     * Be sure to cast to your object type when calling this.
     * Ensure that the Object type has a no-args constructor setup.
     *
     * @param json  The JSON String
     * @param clazz The object class
     * @return An Object represented from the JSON String
     */
    public List<Object> fromJsonArray(String json, Class clazz) {
        return JSON.parseArray(json, clazz);
    }
}
