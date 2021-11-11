DROP TABLE IF EXISTS tbl_temperature;
DROP TABLE IF EXISTS tbl_deal;
DROP TABLE IF EXISTS tbl_user;
-- USER
create table tbl_user
(
    id         bigint auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null,
    pseudo     varchar(255) null
);

-- DEAL
create table tbl_deal
(
    id          bigint auto_increment
        primary key,
    date        datetime     null,
    description varchar(255) null,
    img_url     varchar(255) null,
    price_new   float        null,
    price_old   float        null,
    promo_code  varchar(255) null,
    shop_link   varchar(255) null,
    shop_name   varchar(255) null,
    title       varchar(255) null,
    fk_creator  bigint       null
);

create index FKej6lai7m9m9ysybxxrtm2k34f
    on tbl_deal (fk_creator);

-- TEMPERATURE
create table tbl_temperature
(
    id      bigint auto_increment
        primary key,
    value   int    null,
    fk_deal bigint null,
    fk_user bigint null
);

create index FK2lmxi8e6ti448dbhjk9pxw3ah
    on tbl_temperature (fk_user);

create index FK9y4217jsul17p37q61dvnwfkv
    on tbl_temperature (fk_deal);


