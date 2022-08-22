package Entities;

import lombok.Data;

public @Data class Customer {
    private long ID;
    private String name;
    private String surname;

    public Customer(long ID, String name, String surname) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }
}