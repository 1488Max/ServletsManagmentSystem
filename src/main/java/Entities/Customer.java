package Entities;

import lombok.Data;

public @Data class Customer {
    private long id;
    private String name;
    private String surname;

    public Customer(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }
}