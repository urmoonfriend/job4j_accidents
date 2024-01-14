CREATE TABLE types
(
    id serial primary key,
    name text
);

CREATE TABLE rules
(
    id serial primary key,
    name text
);

CREATE TABLE accidents
(
    id   serial primary key,
    name text,
    text text,
    address text,
    type_id integer references types(id)
);

CREATE TABLE accidents_rules
(
    id serial primary key,
    accident_id integer references accidents(id),
    rule_id integer references rules(id)
)

