package com.farhad.example.redispubsub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joke {
    
    // private static final String JOKE_FORMAT = "Q: %s \nA: %s";

    private String setup;
    private String delivery;
}
