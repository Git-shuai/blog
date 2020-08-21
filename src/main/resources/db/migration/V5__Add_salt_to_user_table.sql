alter table USER
	add salt varchar(200);

comment on column USER.salt is '密码盐值';