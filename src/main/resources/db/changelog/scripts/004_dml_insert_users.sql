insert into users (username, password, enabled)
values ('user', '123456', true);

insert into authorities (username, authority)
values ('user', 'ROLE_USER');