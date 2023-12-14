package com.server.SpringTests.Response.Error;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    // Кастомные ошибки. Бонусом
    private Error error;
}
