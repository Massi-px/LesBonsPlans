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
-- Structure de la table `annonce`
--

DROP TABLE IF EXISTS `annonces`;

CREATE TABLE `annonces` (
                         `id` int(11) NOT NULL,
                         `title` varchar(250) NOT NULL,
                         `path` varchar(250) NOT NULL,
                         `image` varchar(250) NOT NULL,
                         `description` varchar(250) NOT NULL,
                         `link` varchar(250) NOT NULL,
                         `date` datetime NOT NULL ,

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonces` (`id`, `title`, `path`, `image`, `description`) VALUES (1, 'Vente appartement 2 pièces 50 m² Paris 13E (75013)', 'https://cdn.pap.fr/photos/pap/0c/3c/0c3c01d2423fc9aaa8746c9e798559d7/0-p1.jpg', 'indien', '2 pièces - 50 m² Quartier Les Gobelins — Salpétrière - lisière 5ᵉ, à proximité du Jardin des Plantes et des commerces, cinémas, très bien desservi par 3 lignes de métro (5-6-7) et nombreux bus, dans une petite rue calme.'),
                                         (3, 'Chien perdu "OUMAK', 'https://data.chien-perdu.org/photos/3/7/1/2/8/371282_430x430_1.jpg?v=20241125102823', 'indien', 'Plus de détails sur ce chien perdu : nous avons perdu notre chien de 2 ans et demi « OUMAK » Old inuit dog, il est noir, grand, 40 kg, castré, vacciné, pucé, collier rouge, très docile et peureux des gens. Absolument pas agressif.'),
                                                                              (2, 'Vente appartement 3 pièces 81 m² Paris 17E (75017)', 'https://cdn.pap.fr/photos/pap/43/cc/43ccffe2c4a9480607cb232cafb9480b/4-p1.jpg', 'delalune', 'Idéalement situé à deux pas du Parc Monceau, Cet appartement de 81m² combine élégance et modernité. Niché en étage d’un bel immeuble de standing, ce bien rénové par un architecte offre un cadre de vie exceptionnel. Il est composé d''une entrée accueillante, d''une pièce de vie lumineuse avec cuisine équipée et deux chambres. Ce bien profite d’une belle luminosité.');



-- --------------------------------------------------------

--
-- Structure de la table `Statut`
--


--
-- Index pour les tables déchargées
--

--
-- Index pour la table `annonce`
--
ALTER TABLE `annonces`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonce`
--
ALTER TABLE `annonces`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;