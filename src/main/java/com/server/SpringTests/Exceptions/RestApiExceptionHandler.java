package com.server.SpringTests.Exceptions;

import com.server.SpringTests.model.Constants.Code;
import com.server.SpringTests.DTO.Error.Error;
import com.server.SpringTests.DTO.Error.ErrorResponse;
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

    @ExceptionHandler(value = {ApiException.class})
    // Обработка всех ошибок, которые наследуются от ApiException
    // Можно было использовать
    // @ExceptionHandler(value = {UserNotFoundException.class, UserAlreadyExistException.class})
    // То есть перечислить все, обрабатываемые одинаково, ошибки через запятую эквивалентно указанию родительского класса этих ошибок
    public ResponseEntity<?> apiException(UserAlreadyExistException e){
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
