-- Supprime les tables si elles existent déjà
DROP TABLE IF EXISTS Inscription;
DROP TABLE IF EXISTS Adherent;
DROP TABLE IF EXISTS Tournoi;

-- Crée la table Adherent
CREATE TABLE Adherent (
                          numeroAdherent VARCHAR(50) PRIMARY KEY,
                          nom VARCHAR(50) NOT NULL,
                          prenom VARCHAR(50) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          password VARCHAR(100) NOT NULL
);

-- Crée la table Tournoi
CREATE TABLE Tournoi (
                         codeTournoi VARCHAR(50) PRIMARY KEY,
                         nom VARCHAR(100) NOT NULL,
                         date DATE NOT NULL,
                         lieu VARCHAR(100) NOT NULL
);

-- Crée la table Inscription
CREATE TABLE Inscription (
                             numeroAdherent VARCHAR(50),
                             codeTournoi VARCHAR(50),
                             dateInscription DATE,
                             PRIMARY KEY (numeroAdherent, codeTournoi),
                             FOREIGN KEY (numeroAdherent) REFERENCES Adherent(numeroAdherent),
                             FOREIGN KEY (codeTournoi) REFERENCES Tournoi(codeTournoi)
);

INSERT INTO Adherent (numeroAdherent, nom, prenom, email, password) VALUES
('A001', 'AMODE MALL', 'Samir', 'samir@gmail.com', '$2a$10$kU/Q7tNbY9Y76pBJ8UisHuTdnT.XRMZ4zsWZac/dlu18eWT8ZJF0u'),
('A002', 'BOCARA', 'Benjamin', 'benjamin@gmail.com', '$2a$10$ExampleHash456'),
('A003', 'RICHARD', 'Guillaume', 'guillaume@gmail.com', '$2a$10$ExampleHash789'),
('A004', 'STEMPFER', 'Julien', 'julien@gmail.com', '$2a$10$ExampleHash321');

-- Ajoute 4 tournois
INSERT INTO Tournoi (codeTournoi, nom, date, lieu) VALUES
('T001', 'Open Australie', '2025-02-08', 'Melbourne'),
('T002', 'Roland Garros', '2025-05-23', 'Paris'),
('T003', 'Wimbledon', '2025-06-23', 'Londres'),
('T004', 'US Open', '2025-08-30', 'New York');

-- Insertion des données
insert into TODO (ID_TODO, DESCRIPTION) values (1, 'Faire le TP avec sa tête et ses doigts.');
insert into TODO (ID_TODO, DESCRIPTION) values (2, 'Comprendre (ou presque) ce qui est fait.');
insert into TODO (ID_TODO, DESCRIPTION) values (3, 'Apprécier le résultat.');
