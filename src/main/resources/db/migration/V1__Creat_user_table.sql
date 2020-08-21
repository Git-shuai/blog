create table user
(
	id long auto_increment,
	username varchar(50) not null,
	password varchar(50) not null,
	gmt_creat bigint not null,
	gmt_modified bigint not null,
	phone int(11),
	email varchar(50),
	qq int(10),
	wechat varchar(50),
	delete int default 0,
	version int default 1,
	constraint USER_PK
		primary key (id)
);