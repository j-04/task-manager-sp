package ru.dragosh.tm.enumarate;

import java.util.UUID;

public enum Status {
    SCHEDULED("Запланировано"),
    PROCESSING("В процессу"),
    FINISHED("Закончен");

    private String id;
    private String name;

    Status(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
    }
}
