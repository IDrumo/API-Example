package com.server.SpringTests.Exceptions;

import com.server.SpringTests.DTO.Error.Error;
import lombok.Data;

@Data
public class CommonException extends RuntimeException{
    private Error error;

    public CommonException(Error error) {
        super(error.getMessage());
        this.error = error;
    }
}
