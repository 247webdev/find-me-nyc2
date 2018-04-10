create table RESULTS (
    ID serial,
    TITLE varchar(100) NOT NULL,
    LOCATION varchar(100) NOT NULL,
    LAT decimal(9,6) NOT NULL,
    LNG decimal(9,6) NOT NULL,
    SECTION varchar(100),
    DESCRIPTION text,
    DATE_AND_TIME varchar(100)
);