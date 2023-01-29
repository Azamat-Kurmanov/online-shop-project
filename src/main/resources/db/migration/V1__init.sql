create table products(
    id bigserial primary key,
    title varchar(255),
    price int
);
insert into products (title, price)
values ('Milk', 80), ('Bread', 25), ('Cheese', 300);

create table users(
    id bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null
);

create table roles
(
    id bigserial primary key,
    name varchar(50) not null
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

INSERT INTO users (username, password)
VALUES ('bob', '$2a$12$Ka6cXW/Fu4brhnIrAG8Pxe0SyTAq49dH4Fmf2R2pukMotPORNauVe'),
       ('john', '$2a$12$eVc66JhZgxixCiikXe7JSOi1kO.oFUEpFq03hrNLL8iwh/aZOqQ2m');

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);