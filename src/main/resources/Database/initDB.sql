CREATE TABLE "users"
(
    id            int primary key generated always as identity,
    username      varchar,
    name          varchar,
    user_password varchar,
    user_role     varchar default ('USER'),
    status        varchar default ('ACTIVE')
);
INSERT INTO users (username, name, user_password, user_role, status)
VALUES ('admin', '$2a$12$yrgFShcYhDgvMKb2Bb8qzOZ1GLenqSVsE0rxoxI2nto6jDQDKZZka', 'aleksandr', 'ADMIN', 'ACTIVE')