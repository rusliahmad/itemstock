CREATE TABLE order (
  order_no TEXT PRIMARY KEY,
  item_id INTEGER NOT NULL,
  qty INTEGER NOT NULL,
  FOREIGN KEY (item_id) REFERENCES item (id)
);