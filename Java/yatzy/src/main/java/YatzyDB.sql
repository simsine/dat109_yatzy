DROP SCHEMA IF EXISTS yatzy CASCADE;
CREATE SCHEMA yatzy;
SET search_path TO yatzy;

CREATE TABLE spiller (
    brukernavn varchar(255) PRIMARY KEY,
    email VARCHAR(255),
    fornavn VARCHAR(255),
    etternavn VARCHAR(255),
    hashetpassord VARCHAR(255),
    salt VARCHAR(255)
);

CREATE TABLE spill (
    spillnr SERIAL PRIMARY KEY,
    tidopprettet TIMESTAMP,
    tidavsluttet TIMESTAMP,
    oppretterbrukernavn VARCHAR(255),
    FOREIGN KEY (oppretterbrukernavn) REFERENCES spiller(brukernavn)
);

CREATE TABLE poengtabell (
    brukernavn VARCHAR(255),
    spillnr INTEGER,
    poeng JSONB,
    PRIMARY KEY (brukernavn, spillnr),
    FOREIGN KEY (brukernavn) REFERENCES spiller(brukernavn),
    FOREIGN KEY (spillnr) REFERENCES spill(spillnr)
);

CREATE TABLE admin(
	brukernavn varchar(255) primary key,
	FOREIGN KEY (brukernavn) REFERENCES spiller(brukernavn)	
);