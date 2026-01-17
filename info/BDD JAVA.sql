CREATE DATABASE gestion_lprs DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE gestion_lprs;

CREATE TABLE filiere(
id_filiere int(11) NOT NULL AUTO_INCREMENT,
nom varchar(50) NOT NULL,
PRIMARY KEY(id_filiere)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE salle(
id_salle int(11) NOT NULL AUTO_INCREMENT,
capacite int(3) NOT NULL,
est_occupe tinyint(1) DEFAULT 0,
PRIMARY KEY(id_salle)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE commande(
id_commande int(11) NOT NULL AUTO_INCREMENT,
raison varchar(1000) NOT NULL,
PRIMARY KEY(id_commande)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE demande(
id_demande int(11) NOT NULL AUTO_INCREMENT,
est_valide tinyint(1) DEFAULT 0,
PRIMARY KEY(id_demande)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE fournisseur(
id_fournisseur int(11) NOT NULL AUTO_INCREMENT,
nom varchar(50) NOT NULL,
    /* Ajouter d'autre champ jsp lesquel*/
PRIMARY KEY(id_fournisseur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE fourniture(
id_fourniture int(11) NOT NULL AUTO_INCREMENT,
libelle varchar(50) NOT NULL,
description varchar(1000) NOT NULL,
PRIMARY KEY(id_fourniture)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE utilisateur(
id_utilisateur int(11) NOT NULL AUTO_INCREMENT,
nom varchar(50) NOT NULL,
prenom varchar(50) NOT NULL,
email varchar(50) NOT NULL,
mdp varchar(255) NOT NULL,
role varchar(30) NOT NULL,
ref_filiere int default NULL,
PRIMARY KEY(id_utilisateur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE fiche_etudiante(
id_fiche_etudiante int(11) NOT NULL AUTO_INCREMENT,
ref_createur int NOT NULL,
nom_etudiant varchar(50) NOT NULL,
prenom_etudiant varchar(50) NOT NULL,
email_etudiant varchar(50) NOT NULL,
telephone varchar(50) NOT NULL,
adresse varchar(50) NOT NULL,
PRIMARY KEY(id_fiche_etudiante)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE dossier_inscription(
id_dossier_inscription int(11) NOT NULL AUTO_INCREMENT,
date date NOT NULL,
heure TIME NOT NULL,
motivation_etudiant varchar(250) NOT NULL,/*Peut etr epouvoir le mettre en nul nan */
ref_filiere int NOT NULL,
ref_fiche_etudiante int NOT NULL,
PRIMARY KEY(id_dossier_inscription)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE rendez_vous(
id_rendez_vous int(11) NOT NULL AUTO_INCREMENT,
date_rendez_vous date NOT NULL,
heure TIME NOT NULL,
ref_professeur int NOT NULL,
ref_dossier_inscription int NOT NULL,
PRIMARY KEY(id_rendez_vous)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE rdv_salle(
ref_rdv int NOT NULL,
ref_salle int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE utilisateur_commande (
ref_utilisateur int NOT NULL,
ref_commande int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE utilisateur_demande (
ref_utilisateur int NOT NULL,
ref_demande int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE commande_fournisseur (
ref_commande int NOT NULL,
ref_fournisseur int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE commande_fourniture (
ref_commande int NOT NULL,
ref_fourniture int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE demande_fourniture (
ref_demande int NOT NULL,
ref_fourniture int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE fourniture_fournisseur (
ref_fourniture int NOT NULL,
ref_fournisseur int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE utilisateur
ADD CONSTRAINT FK_utilisateur_filiere FOREIGN KEY (ref_filiere) REFERENCES filiere (id_filiere)ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE fiche_etudiante
ADD CONSTRAINT FK_fiche_etudiante_utilisateur FOREIGN KEY (ref_createur) REFERENCES utilisateur (id_utilisateur)ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE dossier_inscription
ADD CONSTRAINT FK_dossier_inscription_fiche_etudiante FOREIGN KEY (ref_fiche_etudiante) REFERENCES fiche_etudiante (id_fiche_etudiante) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_dossier_inscription_filiere FOREIGN KEY (ref_filiere) REFERENCES filiere (id_filiere) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rendez_vous
ADD CONSTRAINT FK_rendez_vous_utilisateur FOREIGN KEY (ref_professeur) REFERENCES utilisateur (id_utilisateur) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_rendez_vous_dossier_inscription FOREIGN KEY (ref_dossier_inscription) REFERENCES dossier_inscription (id_dossier_inscription) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rdv_salle
ADD CONSTRAINT FK_rdv_salle_rdv FOREIGN KEY (ref_rdv) REFERENCES rendez_vous (id_rendez_vous) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_rdv_salle_salle FOREIGN KEY (ref_salle) REFERENCES salle (id_salle) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE utilisateur_commande
ADD CONSTRAINT FK_utilisateur_commande_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES utilisateur (id_utilisateur) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_utilisateur_commande_commande FOREIGN KEY (ref_commande) REFERENCES commande (id_commande) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE utilisateur_demande
ADD CONSTRAINT FK_utilisateur_demande_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES utilisateur (id_utilisateur) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_utilisateur_demande_demande FOREIGN KEY (ref_demande) REFERENCES demande (id_demande) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commande_fourniture
ADD CONSTRAINT FK_commande_fourniture_commande FOREIGN KEY (ref_commande) REFERENCES commande (id_commande) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_commande_fourniture_fourniture FOREIGN KEY (ref_fourniture) REFERENCES fourniture (id_fourniture) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE demande_fourniture
ADD CONSTRAINT FK_demande_fourniture_demande FOREIGN KEY (ref_demande) REFERENCES demande (id_demande) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_demande_fourniture_fourniture FOREIGN KEY (ref_fourniture) REFERENCES fourniture (id_fourniture) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commande_fournisseur
ADD CONSTRAINT FK_commande_fournisseur_commande FOREIGN KEY (ref_commande) REFERENCES commande (id_commande) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_commande_fournisseur_fournisseur FOREIGN KEY (ref_fournisseur) REFERENCES fournisseur (id_fournisseur) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE fourniture_fournisseur
ADD CONSTRAINT FK_fourniture_fournisseur_fourniture FOREIGN KEY (ref_fourniture) REFERENCES fourniture (id_fourniture) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT FK_fourniture_fournisseur_fournisseur FOREIGN KEY (ref_fournisseur) REFERENCES fournisseur (id_fournisseur) ON DELETE CASCADE ON UPDATE CASCADE;

