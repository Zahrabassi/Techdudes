-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 08 mars 2021 à 17:28
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdkaizen`
--

-- --------------------------------------------------------

--
-- Structure de la table `chairman`
--

CREATE TABLE `chairman` (
  `Chairman_ID` varchar(20) NOT NULL,
  `Chairman_Name` varchar(30) NOT NULL,
  `Chairman_Email` varchar(30) DEFAULT NULL,
  `Chairman_Blood_Group` varchar(10) DEFAULT NULL,
  `Chairman_Contact_Number` varchar(20) DEFAULT NULL,
  `Chairman_Address` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `chairman`
--

INSERT INTO `chairman` (`Chairman_ID`, `Chairman_Name`, `Chairman_Email`, `Chairman_Blood_Group`, `Chairman_Contact_Number`, `Chairman_Address`) VALUES
('iheb', 'iheb', 'iheb', '', '', ''),
('SM', 'Shahriar Manzoor', 'smanzoor@gmail.com', '', '', '');

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
  `Nom_evaluation` varchar(20) NOT NULL,
  `lien_evaluation` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id_eval`, `Nom_evaluation`, `lien_evaluation`) VALUES
(1, 'java', 'https://www.qcmquiz.com/QUESTIONNAIRES/QCM-Langage-Objet-Java.php'),
(2, 'php', 'https://www.w3schools.com/php/php_quiz.asphttps://www.w3schools.com/php/php_quiz.asp');

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
  `PASSWORD` varchar(30) NOT NULL,
  `USERTYPE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `logins`
--

INSERT INTO `logins` (`USERNAME`, `PASSWORD`, `USERTYPE`) VALUES
('123', '123', 'Student'),
('AR', '123', 'Teacher'),
('iheb', 'iheb', 'Chairman'),
('KIA', '123', 'Teacher'),
('KMH', '123', 'Teacher'),
('RAJ', '123', 'Teacher'),
('SM', '123', 'Chairman');

-- --------------------------------------------------------

--
-- Structure de la table `meet`
--

CREATE TABLE `meet` (
  `idM` int(11) NOT NULL,
  `lienM` varchar(50) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `student`
--

CREATE TABLE `student` (
  `Student_ID` varchar(20) NOT NULL,
  `Student_Name` varchar(30) NOT NULL,
  `Student_Email` varchar(30) DEFAULT NULL,
  `Student_Blood_Group` varchar(10) DEFAULT NULL,
  `Student_Contact_Number` varchar(20) DEFAULT NULL,
  `Student_Address` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `student`
--

INSERT INTO `student` (`Student_ID`, `Student_Name`, `Student_Email`, `Student_Blood_Group`, `Student_Contact_Number`, `Student_Address`) VALUES
('123', 'test', 'test@email.com', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `teacher`
--

CREATE TABLE `teacher` (
  `Teacher_ID` varchar(20) NOT NULL,
  `Teacher_Name` varchar(30) NOT NULL,
  `Teacher_Email` varchar(30) DEFAULT NULL,
  `Teacher_Blood_Group` varchar(10) DEFAULT NULL,
  `Teacher_Contact_Number` varchar(20) DEFAULT NULL,
  `Teacher_Address` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `teacher`
--

INSERT INTO `teacher` (`Teacher_ID`, `Teacher_Name`, `Teacher_Email`, `Teacher_Blood_Group`, `Teacher_Contact_Number`, `Teacher_Address`) VALUES
('AR', 'Ashiqur Rahman', 'ashiq.seu@gmail.com', '', '', ''),
('KIA', 'Kimia Aksir', 'kimia.aksir@gmail.com', '', '', ''),
('KMH', 'Monirul Hasan', 'kmhasan@gmail.com', '', '', ''),
('RAJ', 'Roksana Akhter Jolly', 'roksana.seu@gmail.com', '', '', '');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chairman`
--
ALTER TABLE `chairman`
  ADD PRIMARY KEY (`Chairman_ID`);

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
-- Index pour la table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Student_ID`);

--
-- Index pour la table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`Teacher_ID`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chairman`
--
ALTER TABLE `chairman`
  ADD CONSTRAINT `CHAIRMAN` FOREIGN KEY (`Chairman_ID`) REFERENCES `logins` (`USERNAME`);

--
-- Contraintes pour la table `eval_etudiant`
--
ALTER TABLE `eval_etudiant`
  ADD CONSTRAINT `fk_etudiant_eval` FOREIGN KEY (`id_etudiant`) REFERENCES `student` (`Student_ID`),
  ADD CONSTRAINT `fk_eval_etud` FOREIGN KEY (`id_eval`) REFERENCES `evaluation` (`id_eval`);

--
-- Contraintes pour la table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `STUDENT` FOREIGN KEY (`Student_ID`) REFERENCES `logins` (`USERNAME`);

--
-- Contraintes pour la table `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `TEACHER` FOREIGN KEY (`Teacher_ID`) REFERENCES `logins` (`USERNAME`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
