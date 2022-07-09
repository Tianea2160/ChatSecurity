create table members
(
    id              int not null NOT NULL AUTO_INCREMENT primary key,
    member_name     varchar(255) null,
    member_nickname varchar(255) null,
    member_uuid     varchar(255) null,
    leaved_at       datetime null,
    created_at      datetime null
);