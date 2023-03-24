package ru.tinkoff.edu.java.bot.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.exception.InvalidRequestParametersException;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionBotApiHandler {

    @ExceptionHandler(InvalidRequestParametersException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequestParameters(InvalidRequestParametersException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse("Invalid request parameters", "400",
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
