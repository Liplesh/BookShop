CREATE TABLE IF NOT EXISTS authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    biography TEXT
);

CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    price_old VARCHAR(250) DEFAULT NULL,
    price VARCHAR(250) DEFAULT NULL,
    author_id int,
    FOREIGN KEY (author_id) REFERENCES authors(id)
    );

