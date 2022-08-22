package Entities;

import lombok.Data;

public @Data class Developer {
    private long ID;
    private String name;
    private String sex;
    private int salary;


    public Developer(long ID, String name, String sex, int salary) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.salary = salary;
    }

    public Developer(String name, String sex, int salary) {
        this.name = name;
        this.sex = sex;
        this.salary = salary;
    }

    public Developer() {

    }
}
