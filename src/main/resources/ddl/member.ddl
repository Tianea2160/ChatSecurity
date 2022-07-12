create table member
(
    member_id              int auto_increment
        primary key,
    member_name     varchar(255) not null,
    member_nickname varchar(255) not null,
    member_uuid     binary(255) not null,
    created_at      datetime     not null,
    leave_at        datetime     null,
    constraint members_member_uuid_uindex
        unique (member_uuid)
);

