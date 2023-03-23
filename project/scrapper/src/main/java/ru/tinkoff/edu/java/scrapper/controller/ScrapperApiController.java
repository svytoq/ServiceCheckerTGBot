package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.*;
import ru.tinkoff.edu.java.scrapper.exception.InvalidRequestParametersException;
import ru.tinkoff.edu.java.scrapper.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
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



    @ExceptionHandler(InvalidRequestParametersException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequestParameters(InvalidRequestParametersException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Invalid request parameters", "400",
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Invalid request parameters", "404",
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Server error", "500",
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }
}
