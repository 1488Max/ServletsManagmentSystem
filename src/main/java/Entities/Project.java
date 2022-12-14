package Entities;

import lombok.Data;

public @Data class Project {
    private long id;
    private String name;
    private String timeOfCreation;
    private long customerId;
    private long companyId;

    public Project(String name, String timeOfCreation) {
        this.name = name;
        this.timeOfCreation = timeOfCreation;
    }

    public Project(String name, String timeOfCreation, long customerId, long companyId) {
        this.name = name;
        this.timeOfCreation = timeOfCreation;
        this.customerId = customerId;
        this.companyId = companyId;
    }
}
