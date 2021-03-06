package com.aaa.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
* @author zhh
* @date 2020/7/9 17:23
* 获取spring容器
*/
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT = null;
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    private SpringContextUtils(){

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /**
         * 虽然已经把自己写的spring上下文代替了spring自带的
         * 当spring开始运行加载的时候，仍然会把spring配置文件覆盖自定义编写的
         */
        SpringContextUtils.APPLICATION_CONTEXT = applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        Lock lock = READ_WRITE_LOCK.readLock();
        lock.lock();
        try {
            if(APPLICATION_CONTEXT != null){
                return APPLICATION_CONTEXT;
            }else {
                return null;
            }
        }finally {
            lock.unlock();
        }
    }
}
