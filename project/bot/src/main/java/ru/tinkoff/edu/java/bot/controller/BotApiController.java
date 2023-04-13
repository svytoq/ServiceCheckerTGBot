package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.dto.scrapper.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkUpdateResponse;


@RestController
public class BotApiController {

    @PostMapping("/update")
    public ResponseEntity<LinkUpdateResponse>  updateLink(@RequestBody LinkUpdateRequest linkUpdateRequest){

        //доделать в следующей дз

        return ResponseEntity.ok(new LinkUpdateResponse("Update completed successfully"));
    }



}
