create database if not exists food; 

use food; 

drop table if exists food;

create table food (
id int(5) not null auto_increment,
name varchar(50) not null,
primary key(id)


);


insert into food (name) VALUES ('Watermelon');
insert into food (name) VALUES ('Chicken');
insert into food (name) VALUES ('Corn');
insert into food (name) VALUES ('Bread');
insert into food (name) VALUES ('Milk');

