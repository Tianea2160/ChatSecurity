-- auto-generated definition
create table member_credential
(
    member_credential_id bigint not null auto_increment,
    email                varchar(255),
    password             varchar(255),
    member_uuid          binary(255),
    primary key (member_credential_id)
) engine = InnoDB;

