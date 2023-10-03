-- liquibase formatted sql
-- changeset Derkachev:1000000-1
-- comment: Initial creation of tables

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS student
(
    student_id UUID PRIMARY KEY UNIQUE DEFAULT uuid_generate_v4(),
    fullName VARCHAR(255) NOT NULL,
    birth DATE NOT NULL,
    performance_id BIGINT
);

CREATE TABLE IF NOT EXISTS performance
(
    performance_id BIGINT PRIMARY KEY NOT NULL ,
    text  VARCHAR(255) NOT NULL
    );
