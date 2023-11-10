package com.server.SpringTests.Entity;

import jakarta.persistence.*;
import lombok.Data;

// Аннотация Entity позволяет автоматически
// создать в БД табличку с нужными полями
@Entity
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean status;

    // указываем, что поле юзера в задачах связано с id их юзера
    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;
}
