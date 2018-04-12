create table RESULTS (
    ID serial,
    TITLE varchar(100) NOT NULL,
    LOCATION varchar(100) NOT NULL,
    LAT decimal(9,6),
    LNG decimal(9,6),
    SECTION varchar(100),
    DESCRIPTION text,
    DATE_AND_TIME varchar(100)
);