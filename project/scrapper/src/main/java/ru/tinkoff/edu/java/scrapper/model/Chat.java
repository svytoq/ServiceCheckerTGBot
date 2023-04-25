package ru.tinkoff.edu.java.scrapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private long id;
    private String username;

    public Chat(long id){
        this.id = id;
    }
}