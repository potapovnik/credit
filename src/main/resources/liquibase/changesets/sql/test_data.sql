insert into role (id, name) values (1,'administrator');
insert into role (id, name) values (2,'loan manager');

insert into users ( login, password, name, surname, last_name, role_id) values ('admin','qwe','Андрей','Киров','Викторович',1);
insert into users ( login, password, name, surname, last_name, role_id) values ('manager','qwe','Виталий','Сидоров','Александрович',2);

insert into borrower (name, surname, last_name, passport_number, passport_series, address, phone_number)
values ('Степан','Иванов','Игоревич','2012','432542','где-то там живу','89009543212');
insert into borrower (name, surname, last_name, passport_number, passport_series, address, phone_number)
values ('Егор','Калимов','Сепанович','2012','123542','Живу где хочу','89009423212');