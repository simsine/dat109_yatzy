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
    FOREIGN KEY (brukernavn) REFERENCES spiller(brukernavn)
);

INSERT INTO spiller(brukernavn, email, fornavn, etternavn, hashetpassord, salt) VALUES 
('XFaze', 'jada@gugel.no', 'Simeon', 'Strømsfjes', 'hei', 'NaCL'),
('Jogustauhan', 'nehei@jahu.net', 'Auguhan', 'Staavik', 'ikke', 'SaLT'),
('MisterMan', 'heman@muskel.com', 'Herdahl', 'Manberg', 'oiasjd', 'HCl'),
('Lajus', 'l@sse.no', 'Labbe', 'Uggus', 'neitakk', 'OIAJSDOIJASDIOJ1234!');