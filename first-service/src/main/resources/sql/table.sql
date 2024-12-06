-- Enum для MpaaRating
CREATE TYPE mpaa_rating AS ENUM ('PG_13', 'R', 'NC_17');

-- Таблица Person
CREATE TABLE person (
    name VARCHAR NOT NULL, -- Используем как первичный ключ
    birthday TIMESTAMP WITH TIME ZONE NOT NULL,
    weight BIGINT NOT NULL CHECK (weight > 0),
    x BIGINT NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    z REAL NOT NULL,
    PRIMARY KEY (name)
);

-- Таблица Movie
CREATE TABLE movie (
    id SERIAL PRIMARY KEY, -- Автоматическая генерация ID
    name VARCHAR NOT NULL UNIQUE, -- Уникальное и обязательное поле
    x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(), -- Автоматическая генерация даты
    oscar_count INT CHECK (oscar_count > 0),
    length INT NOT NULL CHECK (length > 0),
    budget INT NOT NULL CHECK (budget > 0),
    total_box_office INT NOT NULL CHECK (total_box_office > 0),
    mpaa_rating VARCHAR NOT NULL,
    director_name VARCHAR NOT NULL,
    CONSTRAINT fk_director FOREIGN KEY (director_name)
        REFERENCES person (name) ON DELETE SET NULL
);

INSERT INTO person (name, birthday, weight, x, y, z) VALUES
('John Doe', '1980-05-15T00:00:00Z', 75, 1, 50.5, 100.0),
('Jane Smith', '1990-07-20T00:00:00Z', 68, 2, -30.1, 200.5),
('Alice Johnson', '1975-11-30T00:00:00Z', 80, 3, 15.0, -50.0);

INSERT INTO movie (name, x, y, creation_date, oscar_count, budget, length, total_box_office, mpaa_rating, director_name) VALUES
('Inception', 10.5, 20.5, DEFAULT, 5, 160000000, 120, 830000000, 'PG_13', 'John Doe'),
('Titanic', 30.0, 40.1, DEFAULT, 11, 200000000, 200, 2187000, 'PG_13', 'Jane Smith'),
('The Matrix', 50.5, -60.3, DEFAULT, 4, 63000000, 40, 466000000, 'R', 'Alice Johnson');