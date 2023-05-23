package com.farhad.example.redispubsub.subscriber;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.farhad.example.redispubsub.domain.Joke;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriberService {
    
    private final ReactiveRedisOperations<String,Joke> redisOps;

    @Value("${topic.name:joke-channel}")
    private String topic;

    @PostConstruct
    public void init() {

        this.redisOps
                .listenTo(ChannelTopic.of(topic))
                .map(m -> m.getMessage())
                .subscribe(joke -> log.info("Q: {} \nA: {}", joke.getSetup(), joke.getDelivery()) );
    }

}
