package com.zhiyou.taoge.common.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/6.
 */
@Component
public class HibernateRedisIdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private IObjectIdGenerator idGenerator;

    @Bean
    public RedisObjectIdGenerator redisIdGenerator(LettuceConnectionFactory connectionFactory) {
        connectionFactory.setDatabase(1);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        RedisObjectIdGenerator redisIdGenerator = new RedisObjectIdGenerator();
        redisIdGenerator.setTemplate(redisTemplate);
        return redisIdGenerator;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        idGenerator = applicationContext.getBean("redisIdGenerator", IObjectIdGenerator.class);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String simpleName = object.getClass().getSimpleName();
        long l = idGenerator.generateId(simpleName);
        System.out.println(simpleName + "主键:  " + l);
        return l;
    }
}
