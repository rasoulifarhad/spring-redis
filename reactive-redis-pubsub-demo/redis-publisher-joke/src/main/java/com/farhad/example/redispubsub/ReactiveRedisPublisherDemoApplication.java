package com.farhad.example.redispubsub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReactiveRedisPublisherDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveRedisPublisherDemoApplication.class, args);
	}

}
