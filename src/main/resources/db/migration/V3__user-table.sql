CREATE TABLE User(
    email                 nvarchar(100) PRIMARY KEY,
    pwd                   nvarchar(60),
    firstName             nvarchar(60),
    lastName              nvarchar(60),
    roles                 varchar(600),
    enabled               bit,
    accountNonExpired     bit,
    accountNonLocked      bit,
    credentialsNonExpired bit,
    created               timestamp,
    modified              timestamp,
    userType              varchar(20)
);
