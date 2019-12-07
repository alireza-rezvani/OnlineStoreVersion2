create table addresses
(
    id       int auto_increment
        primary key,
    province varchar(50) not null,
    city     varchar(50) not null,
    street   varchar(50) not null,
    zipcode  varchar(10) not null
);

create table carts
(
    id             int auto_increment
        primary key,
    product1_id    int default 0 null,
    product1_count int default 0 null,
    product2_id    int default 0 null,
    product2_count int default 0 null,
    product3_id    int default 0 null,
    product3_count int default 0 null,
    product4_id    int default 0 null,
    product4_count int default 0 null,
    product5_id    int default 0 null,
    product5_count int default 0 null
);

create table products
(
    id    int auto_increment
        primary key,
    title varchar(50) not null,
    price int         not null,
    count int         not null
);

create table electronic_devices
(
    id         int auto_increment
        primary key,
    voltage    varchar(10) null,
    product_id int         not null,
    constraint electronic_devices_ibfk_1
        foreign key (product_id) references products (id)
);

create table radios
(
    id                   int auto_increment
        primary key,
    supported_bands      varchar(20) null,
    electronic_device_id int         not null,
    constraint radios_ibfk_1
        foreign key (electronic_device_id) references electronic_devices (id)
);

create index electronic_device_id
    on radios (electronic_device_id);

create table readables
(
    id         int auto_increment
        primary key,
    pages      int         null,
    publisher  varchar(50) null,
    product_id int         not null,
    constraint readables_ibfk_1
        foreign key (product_id) references products (id)
);

create table books
(
    id          int auto_increment
        primary key,
    writer      varchar(50) null,
    isbn        varchar(20) null,
    readable_id int         not null,
    constraint books_ibfk_1
        foreign key (readable_id) references readables (id)
);

create index readable_id
    on books (readable_id);

create table magazines
(
    id          int auto_increment
        primary key,
    editor      varchar(50) null,
    readable_id int         not null,
    constraint magazines_ibfk_1
        foreign key (readable_id) references readables (id)
);

create index readable_id
    on magazines (readable_id);

create index product_id
    on readables (product_id);

create table shoes
(
    id         int auto_increment
        primary key,
    gender     enum ('MEN', 'WOMEN', 'BOTH') null,
    size       int                           null,
    color      varchar(20)                   null,
    product_id int                           not null,
    constraint shoes_ibfk_1
        foreign key (product_id) references products (id)
);

create table formal_shoes
(
    id       int auto_increment
        primary key,
    material varchar(20) null,
    shoe_id  int         not null,
    constraint formal_shoes_ibfk_1
        foreign key (shoe_id) references shoes (id)
);

create index shoe_id
    on formal_shoes (shoe_id);

create index product_id
    on shoes (product_id);

create table sport_shoes
(
    id      int auto_increment
        primary key,
    `usage` varchar(30) null,
    shoe_id int         not null,
    constraint sport_shoes_ibfk_1
        foreign key (shoe_id) references shoes (id)
);

create index shoe_id
    on sport_shoes (shoe_id);

create table tvs
(
    id                   int auto_increment
        primary key,
    size                 varchar(10) null,
    electronic_device_id int         not null,
    constraint tvs_ibfk_1
        foreign key (electronic_device_id) references electronic_devices (id)
);

create index electronic_device_id
    on tvs (electronic_device_id);

create table users
(
    username     varchar(20) not null
        primary key,
    password     varchar(30) not null,
    firstname    varchar(30) not null,
    lastname     varchar(30) not null,
    mobilenumber varchar(15) not null,
    email        varchar(30) not null,
    address_id   int         not null,
    cart_id      int         not null,
    constraint users_ibfk_1
        foreign key (cart_id) references carts (id),
    constraint users_ibfk_2
        foreign key (address_id) references addresses (id)
);

create index address_id
    on users (address_id);

create index cart_id
    on users (cart_id);


