CREATE TABLE IF NOT EXISTS "user_table"
(
    id            int primary key generated always as identity,
    username      varchar not null unique,
    name          varchar not null,
    user_password varchar not null,
    user_role     varchar default ('USER'),
    status        varchar default ('ACTIVE')
);

INSERT INTO "user_table" (username, name, user_password, user_role, status)
VALUES ('admin', 'admin_name', '$2a$12$dV4YANYZYoamq/NfDVZGv.S9qJ1VfMqjhzAW0ckcFwYATO.l2r/4O', 'ADMIN', 'ACTIVE'),
       ('user', 'user_name', '$2a$12$jq4mkw9EgoD0sQvn/kle/eSbnGewmhxvHZ0jyfpQUngd5isGZkwji', 'USER', 'ACTIVE')
ON CONFlICT DO NOTHING