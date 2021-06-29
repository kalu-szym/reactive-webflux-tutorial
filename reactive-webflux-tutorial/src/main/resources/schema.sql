CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    product_number INT
);

INSERT INTO products (name, product_number) VALUES
('product1','1'),
('product2','2'),
('product3','3');