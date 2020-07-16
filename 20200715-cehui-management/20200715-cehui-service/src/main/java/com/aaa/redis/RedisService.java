package com.aaa.redis;

import com.aaa.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.aaa.staticproperties.RedisProperties.*;

/**
* @author zhh
* @date 2020/7/10 15:03
* redis业务处理类
*/
//@Service
public class RedisService<T> {
    @Autowired
    private JedisCluster jedisCluster;

    private RedisSerializer keySerializer = null;

    /**
     * 初始化redis的key序列化器
     * @PostConstruct ：这个注解表示在项目启动后，加载完spring中的配置后会直接运行该注解标注的方法
     */
    @PostConstruct
    public void initRedisSerializer(){
        if(this.keySerializer == null){
            this.keySerializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
        }
    }

    /**
     * 向redis中存入数据
     * @param key 存入redis中的key
     * @param value 存入redis中的value，因为不知道存入的数值是什么类型，所以需要泛型
     * @param nxxx 是固定值，有两个值
     *      nx：如果redis中的key不存在，则可以存入value值；如果redis中的key已经存在，则不存入value值。可以理解为不能发生覆盖
     *      xx：如果redis中的key不存在，则不可以存入value值；如果redis中的key已经存在，则可以存入value值。可以理解为覆盖
     * @param expx 是固定值，有两个值
     *      ex：失效时间的单位是秒
     *      px：失效时间的单位是毫秒
     * @param seconds 设置的失效时间
     * @return
     */
    public String set(String key,T value,String nxxx,String expx,Integer seconds){
        if(null != seconds && seconds >0 &&
                (nxxx == NX || nxxx == XX ) &&
                (expx == EX || expx == PX)){
            //说明在存入数据的时候需要上失效时间
            return jedisCluster.set(key, JSONUtils.toJsonString(value),nxxx,expx,seconds);
        }else{
            //说明不需要设置失效时间，但是仍然需要进一步去判断用户所传递的到底是nx还是xx
            if(NX.equals(nxxx)){
                return String.valueOf(jedisCluster.setnx(key, JSONUtils.toJsonString(value)));
            }else if(nxxx.equals(XX)){
                return jedisCluster.set(key, JSONUtils.toJsonString(value));
            }
        }
        return NO;
    }

    /**
     * 从redis中查询数据（单个数据）
     * @param key
     * @return
     */
    public T getOne(String key) {
        return (T) jedisCluster.get(key);
    }

    /**
     * 从redis中查询数据（value是字符串）
     * @param key
     * @return
     */
    public String getString(String key){
        return jedisCluster.get(key);
    }

    /**
     * 从redis中查询数据（集合数据）
     * @param key
     * @return
     */
    public List<T> getList(String key){
        return (List<T>) JSONUtils.toList(jedisCluster.get(key),Object.class);
    }

    /**
     * 通过key删除redis中的单个数据
     * @param key
     * @return
     */
    public Long delOne(Object key){
        /**
         * 思路：
         *      目前来说架构遇到的问题：
         *          封装redis的时候发现无法实现通用，因为JedisCluster只能接收String类型或者byte数组类型的key值
         *          并不符合架构的标准，最终可以把Object对象转换为字节数组来进行处理这个问题
         */
        return jedisCluster.del(getByteArrayKey(key));
    }

    /**
     * 删除redis中的多个数据
     * @param keys
     * @return
     */
    public Long delMany(Collection<T> keys){
        if(CollectionUtils.isEmpty(keys)){
            return 0L;
        }else {
            byte[][] bytes = this.getByteArrayKeys(keys);
            return jedisCluster.del(bytes);
        }
    }
    /**
     * 把Object对象转换为字节数组
     * @param key
     * @return
     */
    private byte[] getByteArrayKey(Object key){
        /**
         * 断言就是用来判断用的：
         *  如果key有值就会去执行下面的代码
         *  如果key没有，则直接return了
         */
        Assert.notNull(key,"key required");
        return this.keySerializer == null && key instanceof byte[]?
                (byte[]) key : this.keySerializer.serialize(key);
        //以上代码可以用下面的代码来进行理解
        /*if(key == null){
            System.out.println("key required");
            return null;
        }else{
            if(keySerializer == null && key instanceof byte[]){
                return (byte[]) key;
            }else{
                return this.keySerializer.serialize(key);
            }
        }*/
    }

    /**
     * 将key（List、set）转为二维的字节数组
     * @param keys
     * @return
     */
    private byte[][] getByteArrayKeys(Collection<T> keys){
        //1.创建二维数组，范围为keys的数量
        byte[][] bytes = new byte[keys.size()][];
        //2.确定二维数组开始的位置
        Integer i = 0;
        //3.定义从keys取出的key
        Object key;
        //4、开始对keys中的key进行循环转为字节数组
        /**
         * 执行顺序是：
         *      Iterator<T> iterator = keys.iterator()->iterator.hasNext()：只会判断下一个值是否存在，指针不会进行移动->
         *      key = iterator.next():指针进行移动获取到keys中的第一个key->bytes[i++] = getByteArrayKey(key)：调用getByteArrayKey方法将key转为字节数组放到byte[i++][0]中
         *      最后的结果是：在二维数组的第一个位置存放着keys中key进行转换为字节数组后的值
         */
        for(Iterator<T> iterator = keys.iterator();iterator.hasNext();bytes[i++] = getByteArrayKey(key)){
            key = iterator.next();
        }
        return bytes;
    }
}
