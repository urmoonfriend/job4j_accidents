create table users
(
    id   serial primary key,
    name varchar not null,
    login varchar not null,
    password varchar not null
);
