CREATE DATABASE IF NOT EXISTS `gestion_lprs` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestion_lprs`;

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
                                          `id_commande` int NOT NULL AUTO_INCREMENT,
                                          `raison_commande` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
    `ref_fournisseur` int NOT NULL,
    `ref_gestionnaire` int NOT NULL,
    `nom_commande` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `date_commande` date NOT NULL,
    PRIMARY KEY (`id_commande`),
    KEY `FK_commande_utilisateur_gestionnaire` (`ref_gestionnaire`),
    KEY `FK_commande_fournisseur` (`ref_fournisseur`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `commande_fourniture`;
CREATE TABLE IF NOT EXISTS `commande_fourniture` (
                                                     `ref_commande` int NOT NULL,
                                                     `ref_fourniture` int NOT NULL,
                                                     KEY `FK_commande_fourniture_commande` (`ref_commande`),
    KEY `FK_commande_fourniture_fourniture` (`ref_fourniture`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
                                         `id_demande` int NOT NULL AUTO_INCREMENT,
                                         `est_valide` tinyint(1) DEFAULT '0',
    `ref_professeur` int NOT NULL,
    `ref_gestionnaire` int NOT NULL,
    `raison_demande` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
    `status` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `urgence` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `date_demande` date NOT NULL,
    PRIMARY KEY (`id_demande`),
    KEY `FK_demande_utilisateur_gestionnaire` (`ref_gestionnaire`),
    KEY `FK_demande_utilisateur_professeur` (`ref_professeur`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `demande_fourniture`;
CREATE TABLE IF NOT EXISTS `demande_fourniture` (
                                                    `ref_demande` int NOT NULL,
                                                    `ref_fourniture` int NOT NULL,
                                                    KEY `FK_demande_fourniture_demande` (`ref_demande`),
    KEY `FK_demande_fourniture_fourniture` (`ref_fourniture`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `dossier_inscription`;
CREATE TABLE IF NOT EXISTS `dossier_inscription` (
                                                     `id_dossier_inscription` int NOT NULL AUTO_INCREMENT,
                                                     `date_inscription` date NOT NULL,
                                                     `heure` time NOT NULL,
                                                     `motivation_etudiant` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
    `ref_filiere` int NOT NULL,
    `ref_fiche_etudiante` int NOT NULL,
    PRIMARY KEY (`id_dossier_inscription`),
    KEY `FK_dossier_inscription_fiche_etudiante` (`ref_fiche_etudiante`),
    KEY `FK_dossier_inscription_filiere` (`ref_filiere`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `fiche_etudiante`;
CREATE TABLE IF NOT EXISTS `fiche_etudiante` (
                                                 `id_fiche_etudiante` int NOT NULL AUTO_INCREMENT,
                                                 `ref_createur` int NOT NULL,
                                                 `nom_etudiant` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `prenom_etudiant` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `email_etudiant` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `dernier_diplome_etudiant` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `telephone` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `adresse` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id_fiche_etudiante`),
    KEY `FK_fiche_etudiante_utilisateur` (`ref_createur`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `filiere`;
CREATE TABLE IF NOT EXISTS `filiere` (
                                         `id_filiere` int NOT NULL AUTO_INCREMENT,
                                         `nom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id_filiere`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
                                             `id_fournisseur` int NOT NULL AUTO_INCREMENT,
                                             `nom_fournisseur` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `adresse_fournisseur` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `mail_fournisseur` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `telephone_fournisseur` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
    `delai_livraision_moyen` int DEFAULT NULL,
    `frais_livraison` decimal(10,2) DEFAULT '0.00',
    PRIMARY KEY (`id_fournisseur`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `fourniture`;
CREATE TABLE IF NOT EXISTS `fourniture` (
                                            `id_fourniture` int NOT NULL AUTO_INCREMENT,
                                            `libelle` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `description` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
    `stock_actuelle` int DEFAULT NULL,
    `stock_minimum` int DEFAULT NULL,
    PRIMARY KEY (`id_fourniture`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `fourniture_fournisseur`;
CREATE TABLE IF NOT EXISTS `fourniture_fournisseur` (
                                                        `ref_fourniture` int NOT NULL,
                                                        `ref_fournisseur` int NOT NULL,
                                                        `prix` double NOT NULL,
                                                        KEY `FK_fourniture_fournisseur_fourniture` (`ref_fourniture`),
    KEY `FK_fourniture_fournisseur_fournisseur` (`ref_fournisseur`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
                                             `id_rendez_vous` int NOT NULL AUTO_INCREMENT,
                                             `date_rendez_vous` date NOT NULL,
                                             `heure` time NOT NULL,
                                             `status` enum('Prévus','Annulé','Passé') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Prévus',
    `ref_professeur` int NOT NULL,
    `ref_dossier_inscription` int NOT NULL,
    `ref_salle` int NOT NULL,
    PRIMARY KEY (`id_rendez_vous`),
    KEY `FK_rendez_vous_utilisateur` (`ref_professeur`),
    KEY `FK_rendez_vous_dossier_inscription` (`ref_dossier_inscription`),
    KEY `FK_rendez_vous_salle` (`ref_salle`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
                                       `id_salle` int NOT NULL AUTO_INCREMENT,
                                       `capacite` int NOT NULL,
                                       `est_occupe` tinyint(1) DEFAULT '0',
    PRIMARY KEY (`id_salle`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
                                             `id_utilisateur` int NOT NULL AUTO_INCREMENT,
                                             `nom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `prenom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
    `mdp` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `role` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
    `ref_filiere` int DEFAULT NULL,
    `code` varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id_utilisateur`),
    KEY `FK_utilisateur_filiere` (`ref_filiere`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `commande`
    ADD CONSTRAINT `FK_commande_fournisseur` FOREIGN KEY (`ref_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_commande_utilisateur_gestionnaire` FOREIGN KEY (`ref_gestionnaire`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `commande_fourniture`
    ADD CONSTRAINT `FK_commande_fourniture_commande` FOREIGN KEY (`ref_commande`) REFERENCES `commande` (`id_commande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_commande_fourniture_fourniture` FOREIGN KEY (`ref_fourniture`) REFERENCES `fourniture` (`id_fourniture`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `demande`
    ADD CONSTRAINT `FK_demande_utilisateur_gestionnaire` FOREIGN KEY (`ref_gestionnaire`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_demande_utilisateur_professeur` FOREIGN KEY (`ref_professeur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `demande_fourniture`
    ADD CONSTRAINT `FK_demande_fourniture_demande` FOREIGN KEY (`ref_demande`) REFERENCES `demande` (`id_demande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_demande_fourniture_fourniture` FOREIGN KEY (`ref_fourniture`) REFERENCES `fourniture` (`id_fourniture`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `dossier_inscription`
    ADD CONSTRAINT `FK_dossier_inscription_fiche_etudiante` FOREIGN KEY (`ref_fiche_etudiante`) REFERENCES `fiche_etudiante` (`id_fiche_etudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_dossier_inscription_filiere` FOREIGN KEY (`ref_filiere`) REFERENCES `filiere` (`id_filiere`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `fiche_etudiante`
    ADD CONSTRAINT `FK_fiche_etudiante_utilisateur` FOREIGN KEY (`ref_createur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `fourniture_fournisseur`
    ADD CONSTRAINT `FK_fourniture_fournisseur_fournisseur` FOREIGN KEY (`ref_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_fourniture_fournisseur_fourniture` FOREIGN KEY (`ref_fourniture`) REFERENCES `fourniture` (`id_fourniture`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `rendez_vous`
    ADD CONSTRAINT `FK_rendez_vous_dossier_inscription` FOREIGN KEY (`ref_dossier_inscription`) REFERENCES `dossier_inscription` (`id_dossier_inscription`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_rendez_vous_salle` FOREIGN KEY (`ref_salle`) REFERENCES `salle` (`id_salle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_rendez_vous_utilisateur` FOREIGN KEY (`ref_professeur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `utilisateur`
    ADD CONSTRAINT `FK_utilisateur_filiere` FOREIGN KEY (`ref_filiere`) REFERENCES `filiere` (`id_filiere`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
