CREATE DATABASE gestion_lprs DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE TABLE filiere(
id_filiere int(11) NOT NULL AUTO_INCREMENT,
nom varchar(50) NOT NULL,
PRIMARY KEY(id_filiere)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE salle(
id_sale int(11) NOT NULL AUTO_INCREMENT,
capacite int(3) NOT NULL,
est_occupe tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE commande(
id_commande int(11) NOT NULL AUTO_INCREMENT?
raison varchar(1000)NOT NULL, 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demande(
id_demande int(11) NOT NULL AUTO_INCREMENT,
est_valide tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE fournisseur(
id_fournisseur int(11) NOT NULL AUTO_INCREMENT,
nom varchar(50) NOT NULL,
/* Ajouter d'autre champ jsp lesquel*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE fourniture(
id_fourniture int(11) NOT NULL AUTO_INCREMENT,
libelle varchar(50) NOT NULL,
description varchar(1000) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE utilisateur(
id_utilisateur int(11) NOT NULL AUTO_INCREMENT,
nom varchar(50) NOT NULL,
prenom varchar(50) NOT NULL,
email varchar(50) NOT NULL,
mdp varchar(255) NOT NULL,
role varchar(30) NOT NULL,
ref_filiere int default NULL,
PRIMARY KEY(id_utilisateur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE fiche_etudiante(
id_fiche_etudiante int(11) NOT NULL AUTO_INCREMENT,
ref_createur int NOT NULL,
nom_etudiant varchar(50) NOT NULL,
prenom_etudiant varchar(50) NOT NULL,
email_etudiant varchar(50) NOT NULL,
telephone varchar(50) NOT NULL,
adresse varchar(50) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE dossier_inscription(
id_dossier_inscription int(11) NOT NULL AUTO_INCREMENT,
date date NOT NULL,
heure TIME NOT NULL,
ref_filiere int NOT NULL,
motivation_etudiant varchar(250) NOT NULL,/*Peut etr epouvoir le mettre en nul nan */
ref_fiche_etudiante int NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE rendez_vous(
id_rendez_vous int(11) NOT NULL AUTO_INCREMENT,
date_rendez_vous date NOT NULL,
heure TIME NOT NULL,
ref_professeur int NOT NULL,
ref_dossier_inscription int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE rdv_salle(
ref_rdv int NOT NULL,
ref_salle int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE utilisateur_commande (
ref_utilisateur int NOT NULL,
ref_commande int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE utilisateur_demande (
ref_utilisateur int NOT NULL,
ref_demande int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE commande_fournisseur (
ref_commande int NOT NULL,
ref_fournisseur int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE commande_fourniture (
ref_commande int NOT NULL,
ref_fourniture int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demande_fourniture (
ref_demande int NOT NULL,
ref_fourniture int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE fourniture_fournisseur (
ref_fourniture int NOT NULL,
ref_fournisseur int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


