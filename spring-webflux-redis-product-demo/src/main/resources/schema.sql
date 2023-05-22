CREATE TABLE IF NOT EXISTS products(
   id serial PRIMARY KEY,
   description VARCHAR (500),
   price numeric (10) NOT NULL,
   qty_available integer NOT NULL
);

