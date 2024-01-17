insert into types(name)
values ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

insert into rules(name)
values ('Статья. 1'),
       ('Статья. 2'),
       ('Статья. 3');

insert into accidents(name, text, address, type_id)
values ('Авария 1', 'Авария на улице Абая / Момышулы в 19:05', 'Абая / Момышулы', 1),
       ('Авария 2', 'Авария на улице Толе би / Саина в 18:36', 'Толе би / Саина', 2),
       ('Авария 3', 'Авария на улице Сатпаева / Достык в 09:23', 'Сатпаева / Достык', 3);

insert into accidents_rules(accident_id, rule_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1);