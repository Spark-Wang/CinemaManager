create table movie
(
    M_id   int          not null
        primary key,
    M_name varchar(20)  not null,
    M_price double(11, 0) null,
    M_durTime int       null,
    M_startTime timestamp     null,
    M_endTime   timestamp     null
)

create table hall
(
    H_id  int         not null
        primary key,
    H_name   varchar(30) not null,
    H_capacity  int      null,
    H_line      int      null,
    H_row       int      null
)

create table customer
(
    C_id       int          not null,
    C_name     varchar(20)  not null,
    C_password varchar(255) not null,
    C_Type     varchar(30)  null,
    primary key (C_id, C_name)
)

create table scence
(
    M_id      int      not null,
    H_id      int      not null,
    S_time    timestamp not null,
    primary key (M_id, H_id, S_time),
    constraint M_id
        foreign key (M_id) references movie (M_id),
    constraint H_id
        foreign key (H_id) references hall (H_id)
            on update cascade on delete cascade
)

create table ticket
(
    C_id      int          not null,
    M_id      int          not null,
    H_id      int          not null,
    H_line    varchar(255) not null,
    H_row     varchar(255) not null,
    T_time    timestamp    not null,
    primary key (C_id, M_id, H_id, H_line, H_row, T_time),
    constraint ticket_ibfk_1
        foreign key (C_id) references customer (C_id),
    constraint ticket_ibfk_2
        foreign key (M_id) references movie (M_id)
            on update cascade on delete cascade,
    constraint ticket_ibfk_3
        foreign key (H_id) references hall (H_id)
            on update cascade on delete cascade,
    constraint ticket_ibfk_4
        foreign key (T_time) references scence (S_time)
            on update cascade on delete cascade
)