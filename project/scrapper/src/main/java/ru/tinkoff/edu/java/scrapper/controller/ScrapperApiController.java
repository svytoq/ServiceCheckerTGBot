package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.controller.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.controller.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.controller.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.controller.RemoveLinkRequest;

import java.util.Arrays;

@RestController
public class ScrapperApiController {

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<LinkResponse> registerСhat(@PathVariable("id") String id){

        //доделать в следующей дз, обработать ошибку
        return ResponseEntity.ok(new LinkResponse(Integer.parseInt(id), "chat successfully registered"));
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<LinkResponse> deleteChat(@PathVariable("id") String id){

        //доделать в следующей дз, обработать ошибку
        return ResponseEntity.ok(new LinkResponse(Integer.parseInt(id), "chat successfully deleted"));
    }

    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getAllChats(){

        //доделать в следующей дз, обработать ошибку
        return ResponseEntity.ok(new ListLinksResponse(Arrays.asList(new LinkResponse(1, "/svytoq"), new LinkResponse(2, " Gram3r"))));
    }

    @PostMapping("/links")
    public ResponseEntity<LinkResponse> addLink(@RequestBody AddLinkRequest addLinkRequest){

        //доделать в следующей дз, обработать ошибку
        return ResponseEntity.ok(new LinkResponse(3, "Ja zidkiy"));
    }


    @DeleteMapping("/links")
    public ResponseEntity<LinkResponse> deleteLink(@RequestBody RemoveLinkRequest removeLinkRequest){

        //доделать в следующей дз, обработать ошибку
        return ResponseEntity.ok(new LinkResponse(0, "Siema"));
    }



}
