create table users(
  id bigserial primary key,
  username varchar(36) not null,
  password varchar(80) not null,
  email    varchar(50) unique,
  created_at timestamp default CURRENT_TIMESTAMP,
  updated_at timestamp default CURRENT_TIMESTAMP
);

create table roles
(
    id bigserial primary key,
    name varchar(50) not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP
    primary key(user_id, role_id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('bob', '$2a$12$Ka6cXW/Fu4brhnIrAG8Pxe0SyTAq49dH4Fmf2R2pukMotPORNauVe', 'bob@gmail.com'),
       ('john', '$2a$12$eVc66JhZgxixCiikXe7JSOi1kO.oFUEpFq03hrNLL8iwh/aZOqQ2m', 'john@gmail.com');


INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);