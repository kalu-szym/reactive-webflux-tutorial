CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    product_number INT
);

INSERT INTO product (id, name, product_number) VALUES
(1, 'product1','1'),
(2, 'product2','2'),
(3, 'product3','3')
ON CONFLICT DO NOTHING;