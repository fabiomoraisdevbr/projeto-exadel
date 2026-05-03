-- db/init.sql

CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        customer_id INTEGER NOT NULL,
                        amount NUMERIC(10, 2) NOT NULL,
                        CONSTRAINT fk_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customer (id)
                                ON DELETE CASCADE
);


INSERT INTO customer (nome, email) VALUES
                                       ('João Silva', 'joao@email.com'),
                                       ('Maria Souza', 'maria@email.com');

INSERT INTO orders (customer_id, amount) VALUES
                                             (1, 150.50),
                                             (1, 300.25),
                                             (2, 300.00);