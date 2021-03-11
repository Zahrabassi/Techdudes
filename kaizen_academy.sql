-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 11 mars 2021 à 09:18
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
-- Structure de la table `achat`
--

CREATE TABLE `achat` (
  `id` int(11) NOT NULL,
  `id_f` int(11) NOT NULL,
  `id_etud` int(11) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `prix` decimal(10,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`id`, `id_f`, `id_etud`, `date`, `prix`) VALUES
(3, 2, 1, '2021-03-06', '340.500'),
(4, 3, 1, '2021-03-06', '200.000'),
(5, 4, 1, '2021-03-06', '450.000'),
(6, 2, 1, '2021-03-06', '340.500'),
(7, 3, 1, '2021-03-06', '200.000'),
(8, 2, 1, '2021-03-06', '340.500'),
(9, 3, 1, '2021-03-06', '200.000'),
(10, 4, 1, '2021-03-06', '450.000'),
(11, 2, 1, '2021-03-06', '340.500'),
(12, 3, 1, '2021-03-06', '200.000'),
(13, 2, 1, '2021-03-06', '340.500'),
(14, 2, 1, '2021-03-07', '340.500'),
(15, 3, 1, '2021-03-07', '200.000'),
(16, 4, 1, '2021-03-07', '450.000'),
(17, 4, 1, '2021-03-08', '450.000'),
(18, 2, 1, '2021-03-08', '340.500'),
(19, 3, 1, '2021-03-08', '200.000'),
(20, 4, 1, '2021-03-08', '450.000'),
(21, 3, 1, '2021-03-09', '200.000'),
(22, 2, 1, '2021-03-09', '340.500'),
(23, 3, 1, '2021-03-09', '200.000');

-- --------------------------------------------------------

--
-- Structure de la table `cour`
--

CREATE TABLE `cour` (
  `idC` int(11) NOT NULL,
  `nomCour` varchar(20) NOT NULL,
  `nomEnseignant` varchar(20) NOT NULL,
  `description` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cour`
--

INSERT INTO `cour` (`idC`, `nomCour`, `nomEnseignant`, `description`) VALUES
(2, 'eco', 'Narjess massoudi', 'économie'),
(4, 'GL', ' Mohamed', 'genie logiciel'),
(55, 'oooooo', ' scsdvaaa', 'aaaaa'),
(66, 'pppppppp', ' oioii', 'uuuu'),
(88, 'fvbfg', ' scsdv', 'sdfwsdv');

-- --------------------------------------------------------

--
-- Structure de la table `emplois`
--

CREATE TABLE `emplois` (
  `idEmp` varchar(20) NOT NULL,
  `classe` varchar(15) NOT NULL,
  `matiere` varchar(50) NOT NULL,
  `heureDebut` int(15) NOT NULL,
  `date` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id_eval` int(11) NOT NULL,
  `Nom_evaluation` varchar(20) NOT NULL,
  `lien_evaluation` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id_eval`, `Nom_evaluation`, `lien_evaluation`) VALUES
(1, 'java', 'https://www.qcmquiz.com/QUESTIONNAIRES/QCM-Langage-Objet-Java.php'),
(2, 'php', 'https://www.alsacreations.com/quiz/lire/12-PHP-debutant'),
(3, 'symfony', 'https://www.m2iformation.fr/qcm/formation-symfony-4-demarrer/SYMF4-N1/');

-- --------------------------------------------------------

--
-- Structure de la table `eval_etudiant`
--

CREATE TABLE `eval_etudiant` (
  `id_eval` int(11) NOT NULL,
  `id_etudiant` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `PASSWORD` varchar(100) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `USERTYPE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `logins`
--

INSERT INTO `logins` (`USERNAME`, `PASSWORD`, `Name`, `Email`, `USERTYPE`) VALUES
('ahmed', 'ahmed', 'ahmed', 'iheb.hamdi.1@esprit.tn', 'Etudiant'),
('iheb', 'iheb', 'iheb', '', 'Admin');

-- --------------------------------------------------------

--
-- Structure de la table `meet`
--

CREATE TABLE `meet` (
  `idM` int(11) NOT NULL,
  `lienM` varchar(50) NOT NULL,
  `ratee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `meet`
--

INSERT INTO `meet` (`idM`, `lienM`, `ratee`) VALUES
(1, 'popooooooo', 5),
(2, 'pppp', 5),
(6, 'yyyyyyyyyyyyyyyyyyyyy', 5),
(7, 'www.meet.com', 5),
(8, 'm', 2);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) NOT NULL,
  `id_f` int(11) NOT NULL,
  `promo` decimal(10,3) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id`, `id_f`, `promo`, `date`) VALUES
(1, 3, '33.000', '2021-03-09'),
(3, 2, '75.000', '2021-03-01'),
(4, 4, '60.000', '2021-03-04');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclam` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `sujet` varchar(500) NOT NULL,
  `type` varchar(500) NOT NULL,
  `contenu` text NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `achat`
--
ALTER TABLE `achat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_achat_form` (`id_f`),
  ADD KEY `fk_achat_etud` (`id_etud`);

--
-- Index pour la table `cour`
--
ALTER TABLE `cour`
  ADD PRIMARY KEY (`idC`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id_eval`);

--
-- Index pour la table `eval_etudiant`
--
ALTER TABLE `eval_etudiant`
  ADD PRIMARY KEY (`id_eval`,`id_etudiant`),
  ADD KEY `fk_etudiant_eval` (`id_etudiant`);

--
-- Index pour la table `logins`
--
ALTER TABLE `logins`
  ADD PRIMARY KEY (`USERNAME`);

--
-- Index pour la table `meet`
--
ALTER TABLE `meet`
  ADD PRIMARY KEY (`idM`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_promo_form` (`id_f`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclam`),
  ADD KEY `fk_reclam_user` (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `achat`
--
ALTER TABLE `achat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclam` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
