package org.firefly.provider.feignclient.domain;

import java.time.LocalDateTime;

public class GreetDTO {
    private String name;
    private String message;
    private int age;
    private LocalDateTime dateTime;

    public GreetDTO(String name, String message, int age, LocalDateTime dateTime) {
        this.name = name;
        this.message = message;
        this.age = age;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
