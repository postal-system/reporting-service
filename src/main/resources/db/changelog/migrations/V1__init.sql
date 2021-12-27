create table if not exists portion (
  portion_id uuid primary key,
  shipment_ids uuid[],
  sending_timestamp timestamp not null
);


