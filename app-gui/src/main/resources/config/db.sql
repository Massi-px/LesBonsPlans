-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Hôte : cl1-sql7
-- Généré le :  lun. 26 nov. 2018 à 23:36
-- Version du serveur :  10.0.36-MariaDB-1~jessie
-- Version de PHP :  7.0.30-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `univcergy22`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonces`
--

DROP TABLE IF EXISTS `annonces`;

CREATE TABLE `annonces` (
                            `id` int(11) NOT NULL,
                            `title` varchar(250) NOT NULL,
                            `site` varchar(250) NOT NULL,
                            `link` varchar(250) NOT NULL,
                            `created_at` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Recherche`
--

DROP TABLE IF EXISTS `recherche`;

CREATE TABLE `recherche` (

    `id` int(11) NOT NULL,
    `keywords` varchar(250) null ,
    `sites` varchar(250)  NULL,
    `frequency` int(11)  NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Index pour les tables déchargées
--

--
-- Index pour la table `annonces`
--
ALTER TABLE `annonces`
    ADD PRIMARY KEY (`id`);


--
-- Index pour la table `recherche`
--
ALTER TABLE `recherche`
    ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonces`
--
ALTER TABLE `annonces`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;


--
-- AUTO_INCREMENT pour la table `recherche`
--
ALTER TABLE `recherche`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;