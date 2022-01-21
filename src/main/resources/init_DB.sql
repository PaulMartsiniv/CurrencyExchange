create table CURRENCIES
(
    ID BIGINT auto_increment,
    NAME VARCHAR(5) not null,
    RATE DOUBLE not null,
    constraint CURRENCIES_PK
        primary key (ID)
);

create table CURRENCY_DETAILS
(
    ID BIGINT auto_increment,
    DATA DATE not null,
    BASE VARCHAR(5) not null,
    SYMBOLS VARCHAR(255),
    constraint CURRENCY_DETAILS_PK
        primary key (ID)
);

create table DETAILS_CURRENCIES
(
    DETAIL_ID BIGINT not null,
    CURRENCY_ID BIGINT not null,
    constraint CURRENCIES_FK
        foreign key (CURRENCY_ID) references CURRENCIES (ID),
    constraint DETAILS_FK
        foreign key (DETAIL_ID) references CURRENCY_DETAILS (ID)
);

