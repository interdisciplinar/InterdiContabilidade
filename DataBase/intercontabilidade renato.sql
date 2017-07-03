-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 03, 2017 at 02:50 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `intercontabilidade`
--
CREATE DATABASE IF NOT EXISTS `intercontabilidade` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `intercontabilidade`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `AlteraProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AlteraProdutos` (`v_prod_cod` VARCHAR(10), `v_prod_servico` INT(1), `v_prod_custo` DOUBLE(7,2), `v_prod_usu_id_alteracao` INT(4))  BEGIN
UPDATE produtos SET `prod_servico`= v_prod_servico, `prod_custo` = v_prod_custo, `prod_dt_alteracao`=CURDATE(), `prod_usu_id_alteracao`= v_prod_usu_id_alteracao
WHERE prod_cod = v_prod_cod;

END$$

DROP PROCEDURE IF EXISTS `AlteraStatusUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AlteraStatusUsuario` (`v_usu_login` VARCHAR(20), `v_usu_status` INT(2))  BEGIN
UPDATE `usuario` SET `usu_status`= v_usu_status WHERE usu_login = v_usu_login;
END$$

DROP PROCEDURE IF EXISTS `BuscaPrimProdutoNome`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaPrimProdutoNome` ()  BEGIN
SELECT prod_nome from produtos ORDER BY prod_nome LIMIT 1;
END$$

DROP PROCEDURE IF EXISTS `BuscaProdutoCod`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaProdutoCod` (`v_prod_cod` VARCHAR(10))  BEGIN
SELECT * from produtos where prod_cod = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `BuscaProdutoNome`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaProdutoNome` ()  BEGIN
SELECT prod_nome from produtos ORDER BY prod_nome;
END$$

DROP PROCEDURE IF EXISTS `BuscaProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaProdutos` ()  BEGIN
SELECT * FROM produtos ORDER BY prod_nome;
END$$

DROP PROCEDURE IF EXISTS `custoFichaProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `custoFichaProduto` (`v_ft_nome` VARCHAR(70))  BEGIN
SELECT SUM(`ft_prod_custo` * `ft_prod_qtd`) totalft FROM `produtosficha` WHERE `ft_nome` = v_ft_nome;
END$$

DROP PROCEDURE IF EXISTS `DeletaProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletaProduto` (`v_prod_cod` VARCHAR(10))  BEGIN
DELETE FROM `produtos` WHERE `prod_cod` = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `DeletaProdutoFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletaProdutoFicha` (`v_ft_nome` VARCHAR(70), `v_ft_prod_cod` VARCHAR(10))  BEGIN
DELETE FROM `produtosficha` WHERE ft_nome = v_ft_nome AND ft_prod_cod = v_ft_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `EditaUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditaUsuario` (`v_usu_nome` VARCHAR(70), `v_usu_login` VARCHAR(20), `v_usu_senha` VARCHAR(20), `v_usu_perfil_id` INT(2))  BEGIN
UPDATE usuario SET `usu_nome`= v_usu_nome,`usu_senha`= v_usu_senha,`usu_perfil_id`= v_usu_perfil_id
WHERE `usu_login` = v_usu_login;
END$$

DROP PROCEDURE IF EXISTS `IncluirFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `IncluirFicha` (IN `v_ft_nome` VARCHAR(70), IN `v_ft_usu_id_criacao` INT(4))  BEGIN
INSERT INTO `fichatecnica`(`ft_nome`, `ft_dt_criacao`, `ft_usu_id_criacao`)VALUES (v_ft_nome,CURDATE(), v_ft_usu_id_criacao);
END$$

DROP PROCEDURE IF EXISTS `InserirProdutoFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirProdutoFicha` (`v_ft_nome` VARCHAR(70), `v_ft_prod_nome` VARCHAR(70), `v_ft_prod_cod` VARCHAR(10), `v_ft_prod_custo` DECIMAL(10,2), `v_ft_prod_qtd` INT(4))  BEGIN
INSERT INTO `produtosficha`(`ft_nome`, `ft_prod_nome`, `ft_prod_cod`, `ft_prod_custo`, `ft_prod_qtd`) VALUES (v_ft_nome, v_ft_prod_nome, v_ft_prod_cod, v_ft_prod_custo, v_ft_prod_qtd);
END$$

DROP PROCEDURE IF EXISTS `InserirProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirProdutos` (`v_prod_cod` VARCHAR(10), `v_prod_nome` VARCHAR(70), `v_prod_servico` INT(1), `v_prod_custo` DOUBLE(7,2), `v_prod_usu_id_criacao` INT(4))  BEGIN
INSERT INTO `produtos`(`prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`,`prod_usu_id_criacao`) VALUES (v_prod_cod, v_prod_nome, v_prod_servico, v_prod_custo, CURDATE(), v_prod_usu_id_criacao);

END$$

DROP PROCEDURE IF EXISTS `InserirUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirUsuario` (`v_usu_nome` VARCHAR(70), `v_usu_login` VARCHAR(20), `v_usu_senha` VARCHAR(20), `v_usu_perfil_id` INT(2))  BEGIN
INSERT INTO `usuario`(`usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`,`usu_status`)
VALUES (v_usu_nome, v_usu_login, v_usu_senha, v_usu_perfil_id,1);
END$$

DROP PROCEDURE IF EXISTS `ListaFichaTecnica`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListaFichaTecnica` ()  BEGIN
SELECT * FROM `fichatecnica`;
END$$

DROP PROCEDURE IF EXISTS `ListaProdutosFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListaProdutosFicha` (`v_ft_nome` VARCHAR(70))  BEGIN
SELECT * FROM `produtosficha` WHERE ft_nome = v_ft_nome ;
END$$

DROP PROCEDURE IF EXISTS `ListaUsuarios`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListaUsuarios` ()  BEGIN
SELECT * FROM usuario ORDER BY usu_status DESC;
END$$

DROP PROCEDURE IF EXISTS `ProdutoInfo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProdutoInfo` (`v_prod_nome` VARCHAR(70))  BEGIN
SELECT prod_cod, prod_custo from produtos WHERE prod_nome = v_prod_nome;
END$$

DROP PROCEDURE IF EXISTS `ValidaCodProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ValidaCodProduto` (`v_prod_cod` VARCHAR(10))  BEGIN
SELECT * from produtos WHERE prod_cod = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `ValidaNomeProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ValidaNomeProduto` (`v_prod_nome` VARCHAR(70))  BEGIN
SELECT * from produtos WHERE prod_nome = v_prod_nome;
END$$

DROP PROCEDURE IF EXISTS `VerificaFichaTecnica`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificaFichaTecnica` (`v_ft_nome` VARCHAR(70))  BEGIN
SELECT * FROM `fichatecnica` WHERE ft_nome = v_ft_nome;
END$$

DROP PROCEDURE IF EXISTS `verificaProdutoFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificaProdutoFicha` (`v_ft_nome` VARCHAR(70), `v_ft_prod_cod` VARCHAR(10))  BEGIN
SELECT * FROM `produtosficha` WHERE `ft_nome` = v_ft_nome AND `ft_prod_nome` = v_ft_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `VerificaStatus`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificaStatus` (`v_usu_login` VARCHAR(20))  BEGIN
SELECT `usu_status` FROM `usuario` WHERE usu_login = v_usu_login;
END$$

DROP PROCEDURE IF EXISTS `VerificaUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificaUsuario` (`v_usu_login` VARCHAR(20))  BEGIN
SELECT * FROM `usuario` WHERE usu_login = v_usu_login;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `fichatecnica`
--

DROP TABLE IF EXISTS `fichatecnica`;
CREATE TABLE IF NOT EXISTS `fichatecnica` (
  `ft_id` int(6) NOT NULL AUTO_INCREMENT,
  `ft_nome` varchar(70) NOT NULL,
  `ft_dt_criacao` date NOT NULL,
  `ft_dt_alteracao` date DEFAULT NULL,
  `ft_total_custo` decimal(7,2) DEFAULT NULL,
  `ft_usu_id_criacao` int(4) NOT NULL,
  `ft_usu_id_alteracao` int(4) DEFAULT NULL,
  `ft_percent` decimal(4,2) DEFAULT NULL,
  `ft_enquad_fiscal` int(1) DEFAULT NULL,
  PRIMARY KEY (`ft_id`),
  UNIQUE KEY `ft_id` (`ft_id`),
  UNIQUE KEY `ft_nome` (`ft_nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fichatecnica`
--

INSERT INTO `fichatecnica` (`ft_id`, `ft_nome`, `ft_dt_criacao`, `ft_dt_alteracao`, `ft_total_custo`, `ft_usu_id_criacao`, `ft_usu_id_alteracao`, `ft_percent`, `ft_enquad_fiscal`) VALUES
(3, 'teste', '2017-07-02', NULL, NULL, 4, 4, NULL, NULL),
(4, 'teste1', '2017-07-02', NULL, NULL, 4, 4, NULL, NULL),
(5, 'teste2', '2017-07-02', NULL, NULL, 4, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE IF NOT EXISTS `produtos` (
  `prod_id` int(6) NOT NULL AUTO_INCREMENT,
  `prod_cod` varchar(10) NOT NULL,
  `prod_nome` varchar(70) NOT NULL,
  `prod_servico` int(1) NOT NULL,
  `prod_custo` decimal(10,2) NOT NULL,
  `prod_dt_criacao` date NOT NULL,
  `prod_dt_alteracao` date DEFAULT NULL,
  `prod_usu_id_criacao` int(4) NOT NULL,
  `prod_usu_id_alteracao` int(4) DEFAULT NULL,
  PRIMARY KEY (`prod_id`),
  UNIQUE KEY `prod_id` (`prod_id`),
  UNIQUE KEY `prod_cod` (`prod_cod`),
  UNIQUE KEY `prod_nome` (`prod_nome`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produtos`
--

INSERT INTO `produtos` (`prod_id`, `prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`, `prod_dt_alteracao`, `prod_usu_id_criacao`, `prod_usu_id_alteracao`) VALUES
(8, '1', '1', 0, '1.00', '2017-07-02', NULL, 4, NULL),
(9, '2', '2', 0, '2.00', '2017-07-02', NULL, 4, NULL),
(10, '3', '3', 0, '3.00', '2017-07-02', NULL, 4, NULL),
(11, '4', '4', 0, '4.00', '2017-07-02', NULL, 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `produtosficha`
--

DROP TABLE IF EXISTS `produtosficha`;
CREATE TABLE IF NOT EXISTS `produtosficha` (
  `id_prod_ficha` int(4) NOT NULL AUTO_INCREMENT,
  `ft_nome` varchar(70) NOT NULL,
  `ft_prod_nome` varchar(70) NOT NULL,
  `ft_prod_cod` varchar(10) NOT NULL,
  `ft_prod_custo` decimal(10,2) NOT NULL,
  `ft_prod_qtd` int(4) NOT NULL,
  PRIMARY KEY (`id_prod_ficha`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produtosficha`
--

INSERT INTO `produtosficha` (`id_prod_ficha`, `ft_nome`, `ft_prod_nome`, `ft_prod_cod`, `ft_prod_custo`, `ft_prod_qtd`) VALUES
(9, 'teste', '1', '1', '1.00', 1),
(10, 'teste', '2', '2', '2.00', 2),
(11, 'teste', '3', '3', '3.00', 3),
(12, 'teste1', '2', '2', '2.00', 2),
(13, 'teste1', '3', '3', '3.00', 3),
(14, 'teste1', '1', '1', '1.00', 1),
(15, 'teste2', '2', '2', '2.00', 2),
(16, 'teste2', '1', '1', '1.00', 1),
(17, 'teste2', '3', '3', '3.00', 1),
(18, 'teste2', '4', '4', '4.00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `usu_id` int(4) NOT NULL AUTO_INCREMENT,
  `usu_nome` varchar(70) NOT NULL,
  `usu_login` varchar(20) NOT NULL,
  `usu_senha` varchar(20) NOT NULL,
  `usu_perfil_id` int(2) NOT NULL,
  `usu_status` int(2) NOT NULL,
  PRIMARY KEY (`usu_id`),
  UNIQUE KEY `usu_id` (`usu_id`),
  UNIQUE KEY `usu_login` (`usu_login`(4))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`usu_id`, `usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`, `usu_status`) VALUES
(1, 'Jean', 'jean', '1234', 1, 1),
(2, 'vinicius123456', 'vinicius1234', 'vinicius1234', 1, 1),
(3, 'Marcio', 'marcio', '1234', 1, 1),
(4, 'Jose Renato', 'jose', '1234', 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
