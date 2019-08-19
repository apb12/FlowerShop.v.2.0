 create sequence hibernate_sequence start 1 increment 1;
 create table bucket (
  id int8 not null,
  amount int8,
  sum float8,
  evidence_id int8,
  flower_id int8,
  primary key (id)
     );

 create table evidence (
  id int8 not null,
  date timestamp,
  status varchar(255),
  total float8,
  user_id int8,
  primary key (id)
      );
 create table flower_entity
 (id int8 not null,
  amount int8,
  name varchar(255),
   price float8,
    primary key (id)
    );
 create table user_role(
 user_id int8 not null,
 roles varchar(255)
 );
 create table usr (
 id int8 not null,
  activation_code varchar(255),
  cash float8,
  discount float8,
  email varchar(255),
   password varchar(255),
    username varchar(255),
     primary key (id)
     );
 alter table if exists flower_entity add constraint UK_nqcil0r8la66vk3c9dciwx1jb unique (name);
alter table if exists bucket add constraint FK99284tbfkjwj3bcyxr8kbd34c foreign key (evidence_id) references evidence;
alter table if exists bucket add constraint FKexenebkv598hy8vimy43yelii foreign key (flower_id) references flower_entity;
 alter table if exists evidence add constraint FKdx18vpxjq1ppngt3qrh6gj35y foreign key (user_id) references usr;
alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;
