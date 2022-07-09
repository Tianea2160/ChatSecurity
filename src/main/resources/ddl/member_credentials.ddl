create table member_credentials
(
    member_uuid int not null  AUTO_INCREMENT primary key,
    email       varchar(255) null,
    password    varchar(255) null
);