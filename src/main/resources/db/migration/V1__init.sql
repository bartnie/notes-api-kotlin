CREATE TABLE Note(
    id       varchar(36) PRIMARY KEY,
    title    nvarchar(100),
    message  nvarchar(600),
    location varchar(30)
);

CREATE TABLE Todo(
    id       varchar(36) PRIMARY KEY,
    title    nvarchar(100),
    message  nvarchar(600),
    schedule bigint,
    location varchar(30)
);
