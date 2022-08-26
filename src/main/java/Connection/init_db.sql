Create table developer
(
    id BIGINT AUTO_INCREMENT ,
    name        varchar(100),
    sex         varchar(100),
    PRIMARY KEY(id)

);
Create table company
(
    id BIGINT AUTO_INCREMENT ,
    name      varchar(100),
    area      varchar(100),
    primary key(id)
);

Create table customer
(
    id BIGINT AUTO_INCREMENT ,
    name       varchar(100),
    surname    varchar(100),
    primary key(id)
);
Create table skill
(
    id BIGINT AUTO_INCREMENT ,
    technology varchar(100),
    skill      varchar(100),
    primary key(id)
);


CREATE table developer_skill
(
    developer_id bigint not null,
    skill_id     bigint not null,

    FOREIGN KEY (developer_id) REFERENCES developer(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id)
);
Create table project
(
    id BIGINT AUTO_INCREMENT  ,
    name        varchar(100),
    time_of_creation DATE,
    customer_id BIGINT,
    company_id  BIGINT,
    foreign key (customer_id) REFERENCES customer(id),
    foreign key (company_id) REFERENCES company(id),
    primary key(id)
);



CREATE table developer_project
(
    developer_id BIGINT not null,
    project_id   BIGINT not null,
    PRIMARY KEY (developer_id, project_id),
    FOREIGN KEY (developer_id) REFERENCES developer (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
);

ALTER TABLE skill
    ADD CONSTRAINT technologies_names
        Check (TECHNOLOGY IN ('Java', 'C++', 'C#', 'JS'));

ALTER TABLE skill
    ADD CONSTRAINT skills_levels
        Check (SKILL IN ('Senior', 'Middle', 'Junior'));

ALTER TABLE DEVELOPER
    add salary int;


INSERT into COMPANY (NAME, AREA)
VALUES ('Samsung', 'Technology Development');