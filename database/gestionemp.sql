-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 05 juin 2026 à 20:45
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionemp`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin123'),
('admin', 'admin123');

-- --------------------------------------------------------

--
-- Structure de la table `conges`
--

CREATE TABLE `conges` (
  `conge_id` int(11) NOT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `reason` text DEFAULT NULL,
  `status` varchar(20) DEFAULT 'active',
  `file_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `conges`
--

INSERT INTO `conges` (`conge_id`, `emp_id`, `start_date`, `end_date`, `reason`, `status`, `file_path`) VALUES
(1, 14, '2025-06-01', '2025-06-10', 'Maladie', 'Approuvé', NULL),
(2, 2, '2025-09-12', '2025-09-22', 'malade', 'Approuvé', NULL),
(3, 2, '2026-01-01', '2026-01-05', 'vacance', 'En attente', NULL),
(4, 2, '2025-10-01', '2025-10-03', 'personnel', 'Refusé', NULL),
(9, 345764, '2025-07-25', '2025-08-10', 'maladie', 'Approuvé', NULL),
(10, 345764, '2025-07-18', '2025-08-18', 'mariage', 'Approuvé', 'demandes_conges/345764_1748546207614.pdf'),
(11, 641159, '2025-08-24', '2025-08-26', 'fete', 'En attente', 'demandes_conges/641159_1748565652226.pdf');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `poste` varchar(100) DEFAULT NULL,
  `salaire` decimal(10,2) DEFAULT NULL,
  `date_embauche` date DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `adresse` text DEFAULT NULL,
  `education` varchar(100) NOT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  `departement` varchar(50) DEFAULT NULL,
  `etat_civil` varchar(20) DEFAULT NULL,
  `cin` varchar(20) DEFAULT NULL,
  `nationalite` varchar(50) DEFAULT NULL,
  `photo_profil` varchar(255) DEFAULT NULL,
  `type_employe` varchar(50) DEFAULT NULL,
  `experience` varchar(10) DEFAULT NULL,
  `statut` varchar(10) DEFAULT 'actif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `nom`, `prenom`, `date_naissance`, `poste`, `salaire`, `date_embauche`, `email`, `telephone`, `adresse`, `education`, `sexe`, `departement`, `etat_civil`, `cin`, `nationalite`, `photo_profil`, `type_employe`, `experience`, `statut`) VALUES
(2, 'mouabbad', 'aya', '2003-08-25', 'stagaire', 10000.00, '2025-07-17', 'mouabbadaya@gmail.com', '0702842490', 'sidi bennour', 'ucd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'actif'),
(14, 'hnaineee', 'hiba', '2004-01-25', 'devlopeuse', 10000.00, '2025-07-25', 'hibahnaine@gmail.co;', '0702842490', 'sidi bennour', 'ucds', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'actif'),
(10461, 'mouabbada', 'aya', '2003-08-25', 'devlopper', 7000.00, '2026-09-06', 'ayamouabbadaya@gmail.com', '0702842490', 'eljadidasidibennour', 'master BIBDA', 'Femme', 'informatique', 'Célibataire', 'MC 315526', 'Marocain(e)', 'aya', NULL, NULL, 'actif'),
(345764, 'Moutaouakil', 'hanane', '2004-01-28', 'designer', 10000.00, '2025-08-28', 'hananemoutaouakil0@gmail.com', '0770668331', 'settat/essalam', 'bac+3', 'Femme', 'informatique', 'Célibataire', 'w89997', 'Marocain(e)', 'hanane.jpg', 'Permanent', '2', 'actif'),
(412601, 'test', 'tata', '2004-01-28', 'stage', 123.00, '2004-01-28', 'hananana', '1234567890', 'bhbchevckh', 'ucd', 'Homme', 'info', 'Célibataire', 'hyeb46tg', 'Marocain(e)', '', 'Stagiaire', '1', 'supprimé'),
(480563, 'el idrissi', 'bochra', '1985-08-22', 'teste', 5000.00, '2020-07-02', 'ayamouabbadaya@gmail.com', '0702842490', 'sidibennour', 'bac+5', 'Femme', 'informatique', 'Marié(e)', 'MC23445', 'Marocain(e)', 'ayamouabbad.jpg', 'Stagiaire', '4', 'actif'),
(641159, 'mouabbad', 'aya', '2003-08-25', 'devlopper', 3000.00, '2024-08-20', 'a44651889@gmail.com', '0702842490', 'sidibennour', 'bac+3', 'Femme', 'informatique', 'Célibataire', 'MC315526', 'Marocain(e)', 'ayamouabbad.jpg', 'Stagiaire', '1', 'actif'),
(1691246, 'moutaouakil', 'hanane', '2004-01-28', 'designer', 5000.00, '2025-05-20', 'hananemoutaouakil0@gmail.com', '0770668331', 'settat/elborouj', 'ucd', 'Femme', 'info', 'Célibataire', 'wtg2312', 'Marocain(e)', 'Capture d’écran_8-4-2025_204159_github.com.jpeg', 'Stagiaire', '2', 'actif');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `conges`
--
ALTER TABLE `conges`
  ADD PRIMARY KEY (`conge_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `conges`
--
ALTER TABLE `conges`
  MODIFY `conge_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8409074;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `conges`
--
ALTER TABLE `conges`
  ADD CONSTRAINT `conges_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
