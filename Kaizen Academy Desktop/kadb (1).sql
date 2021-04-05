-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 01 avr. 2021 à 04:04
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
-- Base de données : `kadb`
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
(23, 3, 1, '2021-03-09', '200.000'),
(24, 3, 1, '2021-03-11', '200.000'),
(25, 2, 1, '2021-03-11', '340.500'),
(26, 4, 1, '2021-03-11', '450.000'),
(27, 3, 1, '2021-03-11', '200.000'),
(28, 4, 1, '2021-03-11', '450.000'),
(29, 2, 1, '2021-03-11', '340.500'),
(30, 4, 1, '2021-03-11', '450.000'),
(31, 2, 1, '2021-03-11', '340.500'),
(32, 3, 1, '2021-03-11', '200.000'),
(33, 2, 1, '2021-03-11', '340.500'),
(34, 3, 1, '2021-03-11', '200.000'),
(35, 2, 1, '2021-03-11', '340.500'),
(36, 3, 1, '2021-03-11', '200.000'),
(37, 4, 1, '2021-03-11', '450.000'),
(38, 2, 1, '2021-03-11', '340.500'),
(39, 3, 1, '2021-03-11', '200.000'),
(40, 4, 1, '2021-03-11', '450.000'),
(41, 2, 1, '2021-03-15', '340.500'),
(42, 3, 1, '2021-03-15', '200.000'),
(43, 2, 1, '2021-03-15', '340.500'),
(44, 3, 1, '2021-03-15', '200.000'),
(45, 4, 1, '2021-03-16', '450.000'),
(46, 4, 1, '2021-03-16', '450.000'),
(47, 2, 1, '2021-03-16', '340.500'),
(48, 3, 1, '2021-03-16', '200.000'),
(49, 4, 1, '2021-03-16', '450.000');

-- --------------------------------------------------------

--
-- Structure de la table `cour`
--

CREATE TABLE `cour` (
  `idC` int(11) NOT NULL,
  `nomCour` varchar(50) NOT NULL,
  `nomEnseignant` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cour`
--

INSERT INTO `cour` (`idC`, `nomCour`, `nomEnseignant`, `description`) VALUES
(2, 'pidev', 'Ahmed', 'projet 3A');

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `enseignan` (
  `id` int(20) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(8) NOT NULL,
  `statut` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `enseignan`
--

INSERT INTO `enseignan` (`id`, `nom`, `prenom`, `email`, `telephone`, `statut`) VALUES
(0, '', '', '', '', ''),
(55, 'emna', 'ouakassi', 'emna@gmail.com', '123456', 'vacataire');

--
-- Structure de la table `devoir`
--

CREATE TABLE `devoir` (
  `idD` int(11) NOT NULL,
  `nomD` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `devoir`
--

INSERT INTO `devoir` (`idD`, `nomD`, `description`) VALUES
(1, 'tp12', ' A FAIRE'),
(2, 'tp1', ' A FAIRE');

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
  `nom_evaluation` varchar(20) NOT NULL,
  `lien_evaluation` varchar(110) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id_eval`, `nom_evaluation`, `lien_evaluation`) VALUES
(1, 'evaluation_symfony', 'lien de l\'evaluationhttps://welovedevs.com/app/fr/tests/symfony-5'),
(6, 'quiz Java', 'https://www.quizz.biz/quizz-442822.html'),
(7, 'quiz PHP', 'https://www.qcmquiz.com/QUESTIONNAIRES/QCM-PHP7.php');

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
  `date_fin` date NOT NULL,
  `type` varchar(20) NOT NULL,
  `prix` float NOT NULL,
  `img` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id_f`, `intitule`, `description`, `date_debut`, `id_eval`, `date_fin`, `type`, `prix`, `img`) VALUES
(2, 'Programmation avec java pour les débutants', 'Ce cours est chargée de présenter les bases du langage java et le faire comprendre grâce aux exemples pratiques simples et compréhensible à tout le monde.\r\n\r\nVous allez comprendre;\r\nInstallez et utilisez la machine virtuelle Java et l\'IDE Eclipse\r\nles différents variables en java\r\nles boucles et les conditions\r\nles méthodes et le constructeur\r\nl\'héritage et polymorphisme\r\nles classes abstraites et les interfaces\r\n\r\nÀ qui ce cours s\'adresse-t-il ?\r\nToute personne qui veut apprendre java\r\nLes développeurs qui sont intéressés par la création des logiciels\r\nLes personnes qui veulent trouver de travail rapidement', '2021-03-01', 1, '2021-03-20', 'développement', 340.5, 'java.png'),
(3, 'Apprendre la programmation en C++', 'Bonjour a tous et bienvenue dans cette formation sur la programmation et plus spécialement sur le développement logiciel en C, je m\'appelle Arnaud Mercier et je serai votre formateur tout au long de ce cours\r\n\r\nje suis ingénieur informatique spécialisé notamment dans l’architecture et le développement logiciel. Lors de mon parcours professionnel j’ai eu l’occasion de travailler sur de très nombreux projets de développement en C comme par exemple:\r\n\r\nJeu vidéo pour la Nintendo DS\r\nDécodeur TV pour Orange.\r\nLogiciel embarqué pour des jumelles d’observation chez Safran\r\n\r\net bien d’autres', '2021-03-01', 1, '2021-03-31', 'développement', 200, 'cp.jpg'),
(4, 'Apprendre Symfony 5 par la création d\'un site e-commerce', 'Je suis intiment convaincu que pour apprendre efficacement il faut pratiquer et se mesurer à un projet complexe. C\'est comme ça que j\'ai appris le développement. Je propose dans cette formation à tous les développeurs PHP un apprentissage pas à pas des concepts de Symfony 5 à travers la création d\'un site e-commerce complet.\r\n\r\n* Vos utilisateurs pourront s\'inscrire, se connecter, filtrer les produits, les mettre dans le panier, accéder au tunnel d\'achat, payer et recevoir les emails de confirmation.\r\n\r\n* Vos administrateurs pourront suivre les commandes, gérer les utilisateurs et les produits à travers une interface dédiée : le backoffice.\r\n\r\nL\'objectif est de vous fournir toutes les notions liées à Symfony 5 à chaque étape du projet. Ainsi, vous serez en mesure ensuite de réutiliser du code et de créer vos projets en autonomie.\r\n\r\nÀ qui ce cours s\'adresse-t-il ?\r\nAux développeur PHP qui souhaitent découvrir Symfony 5 à l\'aide d\'un projet e-commerce concret et complexe\r\nAux développeurs qui souhaitent aller plus loin que la documentation offerte par Symfony et passer à la pratique.', '2021-03-01', 1, '2021-05-31', 'développement', 450, 'symfo.png'),
(5, 'français ', 'Apprenez le français rapidement! formation de valeur à Kaizen academy ', '2021-03-18', 1, '2021-03-18', 'langues', 100, 'fr.jpg');

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
(2, 'https://meet.google.com/bht-mcuo-xjm', 2),
(3, 'https://meet.google.com/uub-snsv-hgp', 2);

-- --------------------------------------------------------

--
-- Structure de la table `message_reclam`
--

CREATE TABLE `message_reclam` (
  `id` int(11) NOT NULL,
  `id_reclam` int(11) NOT NULL,
  `id_send` int(11) NOT NULL,
  `message` text NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `message_reclam`
--

INSERT INTO `message_reclam` (`id`, `id_reclam`, `id_send`, `message`, `date`) VALUES
(7, 9, 5, 'Bonjour mr maki', '2021-03-11 00:07:26'),
(8, 9, 4, 'Bonjour', '2021-03-11 00:07:36'),
(11, 11, 4, 'salut', '2021-03-11 00:12:23'),
(12, 11, 5, 'salut je vais vérifier', '2021-03-11 00:12:43'),
(14, 13, 5, 'qsdfghj', '2021-03-11 00:28:00'),
(15, 13, 4, 'qSDFGHGJK', '2021-03-11 00:28:17'),
(16, 14, 5, 'raaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2021-03-11 07:42:33'),
(17, 14, 4, 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb', '2021-03-11 07:42:47');

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id` int(11) NOT NULL,
  `num` varchar(12) NOT NULL,
  `date_exp` date NOT NULL,
  `code` varchar(4) NOT NULL,
  `solde` decimal(10,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id`, `num`, `date_exp`, `code`, `solde`) VALUES
(1, '539912344321', '2022-06-01', '4545', '6019.500'),
(2, '539912344322', '2021-03-10', '5050', '999009.500');

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
(10, 4, '22.000', '2021-03-11'),
(11, 2, '45.000', '2021-03-11'),
(12, 2, '12.000', '2021-03-11'),
(13, 3, '15.000', '2021-03-11'),
(14, 3, '85.000', '2021-03-11'),
(15, 2, '47.000', '2021-03-11'),
(16, 3, '19.000', '2021-03-11'),
(17, 2, '17.000', '2021-03-11'),
(18, 5, '50.000', '2021-03-30');

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
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclam`, `id_user`, `sujet`, `type`, `contenu`, `date`) VALUES
(3, 1, 'Problé d\'un achat', 'vente', 'Bonjour ,\nj ai eu un probléme lors de la confirmation d\'un achat \nmerci de m aider .', '2021-03-10'),
(4, 1, 'probléme de conexion', 'probléme technique', 'Bonjour je peut pas me connect à votre site web il ya 2 jours .\nmerci de m\'aider svp', '2021-03-10'),
(5, 1, 'Probléme ', 'autre', 'bla bla ...\nasasa \nsasasa k,lfnzkfnk a,kla,lk, \n', '2021-03-03'),
(7, 4, 'Probléme d\'achat', 'vente', 'Bonjour ,\nje peut confirmer mon achat .\nmerci de m\'aider svp .', '2021-03-02'),
(9, 4, 'Probléme de conexion', 'probléme technique', 'Bonjour ,\nje peut pas me connecter à votre plateform web .\nmerci de m\'aider svp .', '2021-03-08'),
(11, 4, 'Probléme x', 'probléme technique', 'Bonjour ,\naide moi svp .', '2021-03-11'),
(13, 4, 'QSDFGH', 'vente', 'qsdfh', '2021-03-09'),
(14, 4, 'pokijhgfdoliukhjghf', 'vente', '\n$^poiuytrezsdfghjklmù', '2021-03-11'),
(15, 4, 'formation retarde', 'probléme technique', '*ùmlkjhgf', '2021-03-11');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(500) NOT NULL,
  `email` varchar(500) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` enum('Etudiant','Admin','Enseingant') NOT NULL,
  `id_etud` int(11) DEFAULT NULL,
  `id_ense` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `role`, `id_etud`, `id_ense`) VALUES
(1, 'rania', 'rania.sellami@esprit.tn', '123', 'Etudiant', NULL, NULL),
(3, 'admin_rania', 'admin@gmail.com', '123', 'Admin', NULL, NULL),
(4, 'maki', 'makki.benmbarek@esprit.tn', '123', 'Etudiant', NULL, NULL),
(5, 'admin_reclam', 'admin@gmail.com', '123', 'Admin', NULL, NULL),
(6, 'test', 'chokri.mechichi@esprit.tn', '123', 'Etudiant', NULL, NULL),
(7, 'zahra_ens', 'zahra@gmail.com', '0000', 'Enseingant', NULL, NULL),
(8, 'zahra_Etu', 'zahra@gmail.com', '0000', 'Etudiant', NULL, NULL),
(9, 'ameni', 'ameni@esprit.tn', '1107', 'Admin', NULL, NULL),
(10, 'ameni_etu', 'ameniETU@esprit.tn', '1107', 'Etudiant', NULL, NULL);

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
-- Index pour la table `devoir`
--
ALTER TABLE `devoir`
  ADD PRIMARY KEY (`idD`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id_ens`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id-etud`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id_eval`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id_f`),
  ADD KEY `fk_form_eval` (`id_eval`);

--
-- Index pour la table `meet`
--
ALTER TABLE `meet`
  ADD PRIMARY KEY (`idM`);

--
-- Index pour la table `message_reclam`
--
ALTER TABLE `message_reclam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_msg_reclam` (`id_reclam`),
  ADD KEY `fk_msg_user` (`id_send`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`);

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
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_etud` (`id_etud`),
  ADD KEY `fk_user_ense` (`id_ense`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `achat`
--
ALTER TABLE `achat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT pour la table `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `id_eval` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id_f` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `message_reclam`
--
ALTER TABLE `message_reclam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclam` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `achat`
--
ALTER TABLE `achat`
  ADD CONSTRAINT `fk_achat_etud` FOREIGN KEY (`id_etud`) REFERENCES `enseignant` (`id_ens`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_achat_form` FOREIGN KEY (`id_f`) REFERENCES `formation` (`id_f`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `fk_form_eval` FOREIGN KEY (`id_eval`) REFERENCES `evaluation` (`id_eval`);

--
-- Contraintes pour la table `message_reclam`
--
ALTER TABLE `message_reclam`
  ADD CONSTRAINT `fk_msg_reclam` FOREIGN KEY (`id_reclam`) REFERENCES `reclamation` (`id_reclam`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_msg_user` FOREIGN KEY (`id_send`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `fk_promo_form` FOREIGN KEY (`id_f`) REFERENCES `formation` (`id_f`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_reclam_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_ense` FOREIGN KEY (`id_ense`) REFERENCES `enseignant` (`id_ens`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_etud` FOREIGN KEY (`id_etud`) REFERENCES `etudiant` (`id-etud`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
