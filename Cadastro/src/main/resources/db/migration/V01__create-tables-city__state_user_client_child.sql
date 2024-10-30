-- table users
CREATE TABLE users(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

-- table states
CREATE TABLE states(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(2) NOT NULL
);

-- table cities
CREATE TABLE cities(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    state_id UUID REFERENCES states(id)
);


-- table children
CREATE TABLE children(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birthday DATE
);

-- table clients
CREATE TABLE clients(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city_id UUID REFERENCES cities(id) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    birthday DATE NOT NULL,
    child_id UUID REFERENCES children(id) NOT NULL
);

