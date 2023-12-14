package com.server.SpringTests.Response;

import com.server.SpringTests.Constants.Code;
import com.server.SpringTests.Response.Error.Error;
import com.server.SpringTests.Response.Error.ErrorResponse;
import com.server.SpringTests.Response.Exceptions.CommonException;
import com.server.SpringTests.Response.Exceptions.UserAlreadyExistException;
import com.server.SpringTests.Response.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// Аннотация указывающая, что это перехватчик ошибок. Можно дополнительно указать за каким контроллером наблюдает Handler
public class RestApiExceptionHandler {
    // Это та самая централизация ошибок. По сути это отдельный класс, который только и занимается тем,
    // что ждет, пока где-нибудь вылезет знакомое исключение.

    @ExceptionHandler(value = {Exception.class})
    // Указываем, что будем отлавливать обычные ошибки
    public ResponseEntity<?> defaultHandleException(Exception e){
        return ResponseEntity.badRequest().body(
                ErrorResponse
                        .builder()
                        .error(
                                Error.builder()
                                    .code(Code.CUSTOM_ERROR_CODE_1)
                                    .message("Неизвестная ошибка сервера")
                                    .build())
                        .build());
        // Здесь используются кастомные ошибки и классы, но это совсем не обязательно, вы можете возвращать и самые обычные
        // ResponseEntity, или дополнительно логировать ошибки, или что угодно, что придет вам в голову.
        // Суть в том, что когда где-нибудь в коде возникает ошибка, она прилетит сюда
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    // Обработка UserAlreadyExistException
    public ResponseEntity<?> alreadyExistHandleException(UserAlreadyExistException e){
        return ResponseEntity.badRequest().body(e.getMessage() + " P.S. от RestApiExceptionHandler");
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    // Обработка UserNotFoundException
    public ResponseEntity<?> notFoundHandleException(UserNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage() + " P.S. от RestApiExceptionHandler");
    }

    @ExceptionHandler(value = {CommonException.class})
    // Обработка CommonException
    // Опять же кастомная, но вы можете обработать ее как хотите
    public ResponseEntity<?> commonHandleException(CommonException e){
        return ResponseEntity.badRequest().body(ErrorResponse.builder().error(
                Error.builder()
                        .message(e.getError().getMessage() + " P.S. от RestApiExceptionHandler")
                        .code(e.getError().getCode()).build())
                .build());
    }
}
