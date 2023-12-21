package com.server.SpringTests.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserPasswordlessDTO {
    private Long id;
    private String login;
    private List<TaskNoUserDTO> tasks;
}
