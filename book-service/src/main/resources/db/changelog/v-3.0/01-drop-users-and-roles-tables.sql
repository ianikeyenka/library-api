alter table if exists user_roles
drop constraint if exists FKh8ciramu9cc9q3qcqiv4ue8a6;

alter table if exists user_roles
drop constraint if exists FKhfh9dx7w3ubf1co1vdev94g3f;

drop table if exists roles cascade;

drop table if exists user_roles cascade;

drop table if exists users cascade;