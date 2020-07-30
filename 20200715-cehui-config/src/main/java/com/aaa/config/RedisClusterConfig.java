package com.aaa.config;

import com.aaa.properties.RedisClusterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
* @author zhh
* @date 2020/7/9 19:53
* 连接redis
*/
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisClusterProperties redisClusterProperties;

    /**
     * 获取redis的连接类
     * @return
     */
    @Bean
    public JedisCluster getJedisCluster(){
        String nodes = redisClusterProperties.getNodes();
        String[] split = nodes.split(",");
        Set<HostAndPort> hostAndPortSet = new HashSet<>(16);
        for(String hostPort : split){
            String[] split1 = hostPort.split(":");
            HostAndPort hostAndPort = new HostAndPort(split1[0],Integer.valueOf(split1[1]));
            hostAndPortSet.add(hostAndPort);
        }
        return new JedisCluster(hostAndPortSet,redisClusterProperties.getCommandTimeout(),redisClusterProperties.getMaxAttempts());
    }


}
