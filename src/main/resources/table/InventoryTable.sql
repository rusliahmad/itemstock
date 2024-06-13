CREATE TABLE inventory (
  item_id INTEGER PRIMARY KEY,
  qty INTEGER NOT NULL,
  type TEXT NOT NULL,
  FOREIGN KEY (item_id) REFERENCES item (id)
);