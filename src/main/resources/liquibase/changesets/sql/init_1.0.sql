create table public.role
(
	id integer not null
		constraint role_pkey
			primary key,
	name varchar(100)
)
;