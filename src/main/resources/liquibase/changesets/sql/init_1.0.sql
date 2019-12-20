create table role
(
	id bigserial not null
		constraint role_pk
			primary key,
	name varchar(200)
);

alter table role owner to postgres;

create unique index role_name_uindex
	on role (name);

create table "users"
(
	id bigserial not null
		constraint users_pk
			primary key,
	login varchar(200) not null,
	password varchar(512),
	name varchar(200),
	surname varchar(200),
	last_name varchar(200),
	role_id bigint
		constraint users_role_id_fk
			references role
);

alter table "users" owner to postgres;

create unique index users_login_uindex
	on "users" (login);

create table borrower
(
	id bigserial not null
		constraint borrower_pk
			primary key,
	name varchar(200),
	surname varchar(200),
	last_name varchar(200),
	passport_number integer,
	passport_series integer,
	address varchar(200),
	phone_number varchar(50)
);

alter table borrower owner to postgres;

create table credit
(
	id bigserial not null
		constraint credit_pk
			primary key,
	amount integer,
	annual_rate decimal ,
	date_of_issue timestamp,
	closed boolean,
	creditor_id integer
		constraint credit_users_id_fk
			references "users",
	borrower_id integer
		constraint credit_borrower_id_fk
			references borrower
);

alter table credit owner to postgres;

create unique index borrower_passport_series_uindex
	on borrower (passport_series);

create table payment
(
	id bigserial not null
		constraint payment_pk
			primary key,
	amount integer,
	date timestamp,
	credit_id integer
		constraint payment_credit_id_fk
			references credit
);

alter table payment owner to postgres;

create table schedule
(
	id bigserial not null
		constraint schedule_pk
			primary key,
	amount integer,
	date timestamp,
	credit_id integer
		constraint schedule_credit_id_fk
			references credit
);

alter table schedule owner to postgres;

