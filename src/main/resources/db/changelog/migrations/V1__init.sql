create table if not exists portion (
  portion_id uuid primary key,
  shipment_ids uuid[],
  sending_date date not null
);


