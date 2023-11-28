CREATE TABLE users (
	id SERIAL PRIMARY KEY,
    email VARCHAR(50),
    username VARCHAR(50),
    password VARCHAR(100)
);

CREATE TABLE list (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    list_name VARCHAR(50),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    category VARCHAR(50)
);

CREATE TABLE product_list (
    id SERIAL PRIMARY KEY,
    list_id INT NOT NULL,
    product_name VARCHAR(50),
    category VARCHAR(50),
    amount VARCHAR(10),
    amount_size VARCHAR(10),
    bought BOOLEAN,
    CONSTRAINT fk_list_id FOREIGN KEY (list_id) REFERENCES list (id)
);

INSERT INTO users (username, email, password) VALUES ('Dud', 'Dud@google.com', '$2a$10$L4TheLJbX8HyaMmxnCjSh.Jy0qhudKAm/wKA1kJ5sTPxDzYid68cS');
INSERT INTO users (username, email, password) VALUES ('Fatty', 'Fatty@google.com', '$2a$10$6YEBx7rP7q3wQvIHOZXBFOj2Xs2jG/V889yKDb9jR3NHcY9tgFLye');
INSERT INTO users (username, email, password) VALUES ('Sveta', 'Sveta@gmail.com', '$2a$10$yVbe4AUv4EFJvPunroaktekSLhg9oe.LS.FntkKl3AEtTnjtbMnu.');
INSERT INTO users (username, email, password) VALUES ('Rubert', 'Rubert@google.com', 'g$2a$10$RjWauq5ZYJBgiN484Svhougfws2Wjnhk6/k17EgAButMJGZpop76y');

INSERT INTO list (user_id, list_name) VALUES (1, 'NYList');
INSERT INTO list (user_id, list_name) VALUES (1, 'WeeklyList');
INSERT INTO list (user_id, list_name) VALUES (1, 'PresentList');

INSERT INTO products (name, category) VALUES ('banana', 'fruits');
INSERT INTO products (name, category) VALUES ('tomato', 'vegetables');
INSERT INTO products (name, category) VALUES ('apple', 'fruits');
INSERT INTO products (name, category) VALUES ('water', 'drinks');

INSERT INTO product_list (list_id, product_name, category, amount, amount_size, bought) VALUES (1, 'apple', 'fruits', 5, 'doz', false);
INSERT INTO product_list (list_id, product_name, category, amount, amount_size, bought) VALUES (1, 'tomato', 'vegetables', 4,'kg', false);
INSERT INTO product_list (list_id, product_name, category, amount, amount_size, bought) VALUES (1, 'water', 'drinks', 15, 'bt', false);
INSERT INTO product_list (list_id, product_name, category, amount, amount_size, bought) VALUES (1, 'beer', 'drinks', 5, 'bt', false);
