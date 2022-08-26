package Entities;

import lombok.Data;

public @Data class Developer {
    private long id;
    private String name;
    private String sex;
    private int salary;


    public Developer(long id, String name, String sex, int salary) {
        this.id = id;
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
