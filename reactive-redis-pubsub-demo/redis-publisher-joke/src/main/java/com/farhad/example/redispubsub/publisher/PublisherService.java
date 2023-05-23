package com.farhad.example.redispubsub.publisher;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farhad.example.redispubsub.domain.Joke;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService {
    
    private static final String JOKE_URL = "https://v2.jokeapi.dev/joke/Any?type=twopart";

    private final ReactiveRedisOperations<String,Joke> redisOps;

    private WebClient webClient;

    @Value("${topic.name:joke-channel}")
    private String topic;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                                    .baseUrl(JOKE_URL)
                                    .build();
    }

    @Scheduled(fixedRate = 3000)
    public void publishJoke() {
        this.webClient
                .get()
                .retrieve()
                .bodyToMono(Joke.class)
                .flatMap(joke -> this.redisOps.convertAndSend(topic, joke) )
                .subscribe();
    }
}
