package com.server.SpringTests.DTO.Error;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    // Кастомные ошибки. Бонусом
    private Error error;
}
