package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.exception.InvalidRequestParametersException;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionBotApiHandler {

    public static final String CODE_400 = "400";
    public static final String CODE_400_DESCRIPTION = "Invalid request parameters";
    public static final String CODE_500 = "500";
    public static final String CODE_500_DESCRIPTION = "Server error";

    @ExceptionHandler(InvalidRequestParametersException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequestParameters(InvalidRequestParametersException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(CODE_400_DESCRIPTION, CODE_400,
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(CODE_500_DESCRIPTION, CODE_500,
                e.getClass().getSimpleName(), e.getMessage(), List.of(Arrays.toString(e.getStackTrace())));
        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }
}
