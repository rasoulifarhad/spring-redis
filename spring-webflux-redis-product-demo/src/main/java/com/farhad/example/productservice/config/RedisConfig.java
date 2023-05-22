package com.farhad.example.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.farhad.example.productservice.dto.ProductDto;

@Configuration
// @ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class RedisConfig {
   
    @Bean
    public ReactiveHashOperations<String,Integer,ProductDto> hashOperations(ReactiveRedisConnectionFactory factory) {

        Jackson2JsonRedisSerializer<ProductDto> serializer = new Jackson2JsonRedisSerializer<>(ProductDto.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String,ProductDto> builder =
                        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
            
        RedisSerializationContext<String,ProductDto> context =  builder
                                                                    .hashKey(new GenericToStringSerializer<>(Integer.class)) 
                                                                    .hashValue(serializer)
                                                                    .build();

        ReactiveRedisTemplate<String,ProductDto> reactiveRedisTemplate = new ReactiveRedisTemplate<>(factory, context);

        return reactiveRedisTemplate.opsForHash();
    }

    @Bean
    public ReactiveRedisOperations<Integer,ProductDto> redisOperations(ReactiveRedisConnectionFactory factory) {

        Jackson2JsonRedisSerializer<ProductDto> serializer = new Jackson2JsonRedisSerializer<>(ProductDto.class);
        RedisSerializationContext.RedisSerializationContextBuilder<Integer,ProductDto> builder =
                        RedisSerializationContext.newSerializationContext(new GenericToStringSerializer<>(Integer.class));
            
        RedisSerializationContext<Integer,ProductDto> context =  builder
                                                                    // .key(new GenericToStringSerializer<>(Integer.class))
                                                                    .value(serializer)
                                                                    .build();

        ReactiveRedisTemplate<Integer,ProductDto> reactiveRedisTemplate = new ReactiveRedisTemplate<>(factory, context);

        return reactiveRedisTemplate;
    }
}
