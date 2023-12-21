package com.server.SpringTests.DTO.Error;

import com.server.SpringTests.model.Constants.Code;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Error{
    private Code code;
    private String message;
}
