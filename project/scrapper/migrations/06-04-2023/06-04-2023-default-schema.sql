--liquibase formatted sql

--changeset svytoq:create_table_chat
CREATE TABLE IF NOT EXISTS Chat
(
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

--changeset svytoq:create_table_chat
CREATE TABLE IF NOT EXISTS Link
(
    url VARCHAR(255) NOT NULL,
    id INT,
    update_time TIME,
    FOREIGN KEY (id) References Chat (id)
);
