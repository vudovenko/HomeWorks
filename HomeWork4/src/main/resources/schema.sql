DROP TABLE IF EXISTS books;

CREATE TABLE books
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    author    VARCHAR(250) NOT NULL,
    title     VARCHAR(250) NOT NULL,
    price_old INT DEFAULT NULL,
    price     INT DEFAULT NULL
);

DROP TABLE IF EXISTS authors;

create table authors
(
    id         INT,
    first_name VARCHAR(50),
    last_name  VARCHAR(50)
);