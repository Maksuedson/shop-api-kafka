create table shop (
  id bigserial primary key,
  identifier varchar not null,
  status varchar not null,
  date_shop date
);

create table shop_item (
  id bigserial primary key,
  product_identifier varchar(100) not null,
  amount int not null,
  price float not null,
  shop_id bigint REFERENCES shop(id)
);