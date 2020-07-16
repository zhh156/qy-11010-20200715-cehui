package com.aaa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
* @author zhh
* @date 2020/7/10 15:19
* json转换工具类
*/
public class JSONUtils {
    //1.定义私有静态常量ObjectMapper（命名规则:所有字母全部大写，单词之间使用_连接）
        //ObjectMapper:就是fastjson包中进行类型转换的工具类
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JSONUtils(){

    }
    /**
     * 将对象转换为json字符串
     * @param object
     * @return
     */
    public static String toJsonString(Object object){
        try {
            String jsonString = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把json对象转为指定的对象
     * T：返回值的类型
     * @param jsonData 传入的json对象
     * @param clazz 需要转换为的目标类型
     * @param <T> 定义了一个类型
     * @return
     */
    public static <T> T toObject(String jsonData, Class<T> clazz){
        try {
            T t = OBJECT_MAPPER.readValue(jsonData, clazz);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把json对象转换为List集合
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String jsonData, Class<T> clazz){
        //1.为集合添加一个指定的泛型
            //List User.class ---> 通过constructParametricType方法把List和User合并，也就是说为List指定一个User对象的泛型List<User>
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class,clazz);
        try {
            List<T> list = OBJECT_MAPPER.readValue(jsonData,javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
