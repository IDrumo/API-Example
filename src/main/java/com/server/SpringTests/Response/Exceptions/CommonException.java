package com.server.SpringTests.Response.Exceptions;

import com.server.SpringTests.Response.Error.Error;
import lombok.Data;

@Data
public class CommonException extends RuntimeException{
    private Error error;

    public CommonException(Error error) {
        super(error.getMessage());
        this.error = error;
    }
}
