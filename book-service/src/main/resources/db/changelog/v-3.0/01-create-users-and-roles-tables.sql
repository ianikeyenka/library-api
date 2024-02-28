create table roles
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);

create table users
(
    id       bigserial not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table user_roles
(
    role_id bigint not null,
    user_id bigint not null
);

alter table if exists user_roles
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6
    foreign key (role_id)
    references roles;

alter table if exists user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
    foreign key (user_id)
    references users;