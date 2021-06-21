create table IF NOT EXISTS STATISTICS (
                                   ID BIGSERIAL PRIMARY KEY,
                                   short_url varchar(200) not null,
                                   long_url varchar(200) not null,
                                   amount  BIGINT DEFAULT 0 not null
);

alter table url drop column amount;
