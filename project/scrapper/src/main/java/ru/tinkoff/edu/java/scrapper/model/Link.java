package ru.tinkoff.edu.java.scrapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private long id;
    private String url;
    private Instant updateTime;

    public Link(String url){
        this.url = url;
    }
}
