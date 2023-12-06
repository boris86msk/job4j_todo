CREATE TABLE todo_user
(
    id          SERIAL PRIMARY KEY,
    u_name      VARCHAR         NOT NULL ,
    u_login     VARCHAR UNIQUE  NOT NULL ,
    u_password  VARCHAR         NOT NULL
);