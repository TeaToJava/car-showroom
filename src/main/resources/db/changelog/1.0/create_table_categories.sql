--liquibase formatted sql

--changeset d:1
CREATE TABLE IF NOT EXISTS categories(
    id bigint PRIMARY KEY,
    name varchar(10) NOT NULL);