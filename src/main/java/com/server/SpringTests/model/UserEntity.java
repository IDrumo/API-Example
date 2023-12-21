package com.server.SpringTests.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    // Задаем отношение один ко многим, т.к. один пользователь может иметь много задач
    // и указываем каскад, чтобы при удалении пользователя удалялись все задачи
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TaskEntity> tasks;
}
