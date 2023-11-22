package com.server.SpringTests.DTO;

import lombok.Data;

@Data
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

}
