package com.server.SpringTests.Response.Error;

import com.server.SpringTests.Constants.Code;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Error{
    private Code code;
    private String message;
}
