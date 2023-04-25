package ru.tinkoff.edu.java.scrapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatLink {
    private long chatId;
    private long linkId;
}