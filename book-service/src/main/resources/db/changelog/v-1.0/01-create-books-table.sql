create table books
(
    id          bigserial not null,
    author      varchar(255),
    description varchar(255),
    genre       varchar(255),
    isbn        varchar(255),
    name        varchar(255),
    primary key (id)
)

GO
