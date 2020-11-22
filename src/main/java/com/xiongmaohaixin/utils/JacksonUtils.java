//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xiongmaohaixin.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final static Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private JacksonUtils() {
    }

    public static final ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String obj2json(Object obj) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            logger.error(obj==null?"json==null":obj.toString());
            throw var2;
        }
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws IOException {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException var3) {
            logger.error("[jsonStr:{}][{}]",jsonStr,var3);
            throw var3;
        }
    }

    public static <T> T json2pojo(String jsonStr, JavaType javaType) throws IOException {
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException var3) {
            logger.error("[jsonStr:{}][{}]",jsonStr,var3);
            throw var3;
        }
    }

    public static <T> Map<String, Object> json2map(String jsonStr) throws IOException {
        try {
            return (Map)objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException var2) {
            logger.error("[jsonStr:{}][{}]",jsonStr,var2);
            throw var2;
        }
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws IOException {
        Map map;
        try {
            map = (Map)objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
            });
        } catch (IOException var6) {
            logger.error("[jsonStr:{}][{}]",jsonStr,var6);
            throw var6;
        }

        Map<String, T> result = new HashMap();
        Iterator var4 = map.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Map<String, Object>> entry = (Entry)var4.next();
            result.put(entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
        }

        return result;
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws IOException {
        List list = null;

        try {
            list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
            });
        } catch (IOException var6) {
            logger.error("[jsonArrayStr:{}][{}]",jsonArrayStr,var6);
            throw var6;
        }

        List<T> result = new ArrayList();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Map<String, Object> map = (Map)var4.next();
            result.add(map2pojo(map, clazz));
        }

        return result;
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    static {
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    }
}
