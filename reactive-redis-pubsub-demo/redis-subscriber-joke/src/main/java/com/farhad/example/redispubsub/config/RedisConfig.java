package com.farhad.example.redispubsub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.farhad.example.redispubsub.domain.Joke;

@Configuration
public class RedisConfig {
    
    @Bean
    public ReactiveRedisOperations<String,Joke> jokeOperation(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        
        RedisSerializer<Joke> valueSerializer = new Jackson2JsonRedisSerializer<>(Joke.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String,Joke> builder = 
                    RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String,Joke> context = builder
                                                            .value(valueSerializer)
                                                            .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory,context);            
    }
}
