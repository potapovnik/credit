insert into role (id, name) values (1,'administrator');
insert into role (id, name) values (2,'loan manager');

insert into users ( login, password, name, surname, last_name, role_id) values ('admin','ec7dc4386b419165c3ca4b51785d92dfc5004a9008d05648ca85516d5c953f827293165ad50910190ea2180081e6baa83c32fff2ce7e02f0ebf92aa931b08dc4987f67c20a6dccf3','Андрей','Киров','Викторович',1);
insert into users ( login, password, name, surname, last_name, role_id) values ('manager','ec7dc4386b419165c3ca4b51785d92dfc5004a9008d05648ca85516d5c953f827293165ad50910190ea2180081e6baa83c32fff2ce7e02f0ebf92aa931b08dc4987f67c20a6dccf3','Виталий','Сидоров','Александрович',2);

insert into borrower (name, surname, last_name, passport_number, passport_series, address, phone_number)
values ('Степан','Иванов','Игоревич','2012','432542','где-то там живу','89009543212');
insert into borrower (name, surname, last_name, passport_number, passport_series, address, phone_number)
values ('Егор','Калимов','Сепанович','2012','123542','Живу где хочу','89009423212');