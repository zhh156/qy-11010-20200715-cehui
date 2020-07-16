package com.aaa.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author zhh
* @date 2020/7/9 16:30
* map转为实体类 map to bean
*/
public class Map2BeanUtils {
    /**
     * 高性能java实例化工具
     */
    private static final Objenesis OBJENESIS = new ObjenesisStd(true);
    /**
     * 使用String效率太低，使用StringBuffer虽然效率提高了，但是相对于StringBuilder效率还是太低了
     */
    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    /**
     * 高性能反射工具类，高性能反射字节集
     * ConcurrentHashMap：在线程中运转，这个Map只会在当前线程中出现
     * 而且线程与线程之间是具有隔离性的，这里的Map就不会被其他的线程所干扰
     */
    private static final ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(16);


    private Map2BeanUtils(){

    }

    public static <T> T map2Bean(Map<String,Object> map,Class<T> clazz){
        //1.获取实例对象信息
        T instance = OBJENESIS.newInstance(clazz);
        //2.从Map中通过key（instance），获取MethodAccess对象
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        //3.判断
        if(methodAccess == null ){
            //4.通过类获取MethodAccess对象
            methodAccess = MethodAccess.get(clazz);
            //5.存入CONCURRENT_HASH_MAP中
            //putIfAbsent方法：如果是新的记录就会向map中添加该键值对并且返回null；如果已经存在那么不会覆盖已有的值，直接返回已经存在的值
            CONCURRENT_HASH_MAP.putIfAbsent(clazz,methodAccess);
        }
        //6.循环Map对象
        for (Map.Entry entry : map.entrySet()){
            String setMethodName = getSetMethodName((String) entry.getKey());
            Integer index = methodAccess.getIndex(setMethodName,entry.getValue().getClass());
            methodAccess.invoke(instance,index,entry.getValue());
        }
        return instance;
    }

    /**
     * 通过字段拼接方法名
     * @param filedName
     * @return
     */
    private static String getSetMethodName(String filedName){
        STRING_BUILDER.setLength(0);
        return STRING_BUILDER.append("set").append(first2UpperCase(filedName)).toString();
    }

    /**
     * 把属性的首字母转换为大写
     * @param str
     * @return
     */
    private static String first2UpperCase(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
}
