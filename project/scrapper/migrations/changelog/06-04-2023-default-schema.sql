--liquibase formatted sql

--changeset svytoq:create_table_chat
CREATE TABLE IF NOT EXISTS chat
(
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

--changeset svytoq:create_table_chat
CREATE TABLE IF NOT EXISTS link
(
    url VARCHAR(255) NOT NULL,
    id INT,
    update_time TIME
)
;
--changeset svytoq:create_table_chat_link
CREATE TABLE IF NOT EXISTS chat_link
(
    chat_id INT,
    link_id INT,
    PRIMARY KEY (chat_id, link_id),
    FOREIGN KEY (chat_id) REFERENCES chat(id),
    FOREIGN KEY (link_id) REFERENCES link(id)
);
