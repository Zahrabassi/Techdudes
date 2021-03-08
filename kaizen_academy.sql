-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 08 mars 2021 à 16:19
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `kaizen_academy`
--

-- --------------------------------------------------------

--
-- Structure de la table `cour`
--

CREATE TABLE `cour` (
  `idC` int(11) NOT NULL,
  `nomCour` varchar(50) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `devoir`
--

CREATE TABLE `devoir` (
  `idD` int(11) NOT NULL,
  `nomD` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `dateLim` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `emplois du temps`
--

CREATE TABLE `emplois du temps` (
  `idEmplois` varchar(20) NOT NULL,
  `login` int(20) NOT NULL,
  `heureDebut` int(15) NOT NULL,
  `duree` int(50) NOT NULL,
  `classe` int(15) NOT NULL,
  `date` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `id_ens` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`id_ens`, `nom`, `prenom`) VALUES
(1, 'Dridi', 'Ramzi'),
(2, 'Arnaud ', 'Mercier'),
(3, 'Houdoux', 'Mikael');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id-etud` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id-etud`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id_eval` int(11) NOT NULL,
  `moy` float NOT NULL,
  `id_etud` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id_eval`, `moy`, `id_etud`) VALUES
(1, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id_f` int(11) NOT NULL,
  `intitule` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `date_debut` date NOT NULL,
  `id_eval` int(11) NOT NULL,
  `id_formateur` int(11) NOT NULL,
  `date_fin` date NOT NULL,
  `type` varchar(20) NOT NULL,
  `prix` float NOT NULL,
  `img` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id_f`, `intitule`, `description`, `date_debut`, `id_eval`, `id_formateur`, `date_fin`, `type`, `prix`, `img`) VALUES
(2, 'Programmation avec java pour les débutants', 'Ce cours est chargée de présenter les bases du langage java et le faire comprendre grâce aux exemples pratiques simples et compréhensible à tout le monde.\r\n\r\nVous allez comprendre;\r\nInstallez et utilisez la machine virtuelle Java et l\'IDE Eclipse\r\nles différents variables en java\r\nles boucles et les conditions\r\nles méthodes et le constructeur\r\nl\'héritage et polymorphisme\r\nles classes abstraites et les interfaces\r\n\r\nÀ qui ce cours s\'adresse-t-il ?\r\nToute personne qui veut apprendre java\r\nLes développeurs qui sont intéressés par la création des logiciels\r\nLes personnes qui veulent trouver de travail rapidement', '2021-03-01', 1, 1, '2021-03-20', 'dev', 340.5, 'java.png'),
(3, 'Apprendre la programmation en C++', 'Bonjour a tous et bienvenue dans cette formation sur la programmation et plus spécialement sur le développement logiciel en C, je m\'appelle Arnaud Mercier et je serai votre formateur tout au long de ce cours\r\n\r\nje suis ingénieur informatique spécialisé notamment dans l’architecture et le développement logiciel. Lors de mon parcours professionnel j’ai eu l’occasion de travailler sur de très nombreux projets de développement en C comme par exemple:\r\n\r\nJeu vidéo pour la Nintendo DS\r\nDécodeur TV pour Orange.\r\nLogiciel embarqué pour des jumelles d’observation chez Safran\r\n\r\net bien d’autres', '2021-03-01', 1, 2, '2021-03-31', 'dev', 200, 'cp.jpg'),
(4, 'Apprendre Symfony 5 par la création d\'un site e-commerce', 'Je suis intiment convaincu que pour apprendre efficacement il faut pratiquer et se mesurer à un projet complexe. C\'est comme ça que j\'ai appris le développement. Je propose dans cette formation à tous les développeurs PHP un apprentissage pas à pas des concepts de Symfony 5 à travers la création d\'un site e-commerce complet.\r\n\r\n* Vos utilisateurs pourront s\'inscrire, se connecter, filtrer les produits, les mettre dans le panier, accéder au tunnel d\'achat, payer et recevoir les emails de confirmation.\r\n\r\n* Vos administrateurs pourront suivre les commandes, gérer les utilisateurs et les produits à travers une interface dédiée : le backoffice.\r\n\r\nL\'objectif est de vous fournir toutes les notions liées à Symfony 5 à chaque étape du projet. Ainsi, vous serez en mesure ensuite de réutiliser du code et de créer vos projets en autonomie.\r\n\r\nÀ qui ce cours s\'adresse-t-il ?\r\nAux développeur PHP qui souhaitent découvrir Symfony 5 à l\'aide d\'un projet e-commerce concret et complexe\r\nAux développeurs qui souhaitent aller plus loin que la documentation offerte par Symfony et passer à la pratique.', '2021-03-01', 1, 3, '2021-05-31', 'dev', 450, 'symfo.png');

-- --------------------------------------------------------

--
-- Structure de la table `logins`
--

CREATE TABLE `logins` (
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `USERTYPE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `logins`
--

INSERT INTO `logins` (`USERNAME`, `PASSWORD`, `Name`, `Email`, `USERTYPE`) VALUES
('iheb', 'iheb', '', '', 'Admin');

-- --------------------------------------------------------

--
-- Structure de la table `meet`
--

CREATE TABLE `meet` (
  `idM` int(11) NOT NULL,
  `lienM` varchar(50) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `logins`
--
ALTER TABLE `logins`
  ADD PRIMARY KEY (`USERNAME`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
