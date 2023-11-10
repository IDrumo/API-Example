package com.server.SpringTests.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TaskNoUserDTO {
    private Long id;
    private String title;
    private Boolean status;
}
