create table if not exists user (
id bigint primary key auto_increment,
username varchar(255) unique not null,
email varchar(255) unique not null,
first_name varchar(255) not null,
last_name varchar(255) not null,
is_staff boolean not null,
is_active boolean not null,
membership_type varchar(50) not null
);

create table if not exists author (
id bigint primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255),
date_of_birth date not null,
biography text
);

create table if not exists library_branch (
id bigint primary key auto_increment,
name varchar(255) not null,
location varchar(255),
contact_info varchar(255)
);

create table if not exists book (
id bigint primary key auto_increment,
title varchar(255) not null,
isbn varchar(50) unique not null,
author_id bigint not null,
published_date date not null,
available_copies int not null,
library_branch_id bigint not null,
foreign key (author_id) references author(id),
foreign key (library_branch_id) references library_branch(id)
);



create table if not exists borrowing_record (
id bigint primary key auto_increment,
user_id bigint,
book_id bigint,
borrow_date date not null,
due_date date not null,
return_date date,
status varchar(50),
foreign key (user_id) references user(id),
foreign key (book_id) references book(id)
);

create table if not exists fine (
id bigint primary key auto_increment,
borrowing_record_id bigint,
amount decimal(10, 2) not null,
issued_date date not null,
status varchar(50) not null,
foreign key (borrowing_record_id) references borrowing_record(id)
);
