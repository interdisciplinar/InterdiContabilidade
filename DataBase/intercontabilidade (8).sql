-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27-Jul-2017 às 00:29
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `intercontabilidade`
--
CREATE DATABASE IF NOT EXISTS `intercontabilidade` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `intercontabilidade`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `AlteraProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AlteraProdutos`(`v_prod_cod` VARCHAR(10), `v_prod_servico` INT(1), `v_prod_custo` DOUBLE(7,2), `v_prod_usu_id_alteracao` INT(4))
BEGIN
UPDATE produtos SET `prod_servico`= v_prod_servico, `prod_custo` = v_prod_custo, `prod_dt_alteracao`=CURDATE(), `prod_usu_id_alteracao`= v_prod_usu_id_alteracao
WHERE prod_cod = v_prod_cod;

END$$

DROP PROCEDURE IF EXISTS `AlteraStatusUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AlteraStatusUsuario`(`v_usu_login` VARCHAR(20), `v_usu_status` INT(2))
BEGIN
UPDATE `usuario` SET `usu_status`= v_usu_status WHERE usu_login = v_usu_login;
END$$

DROP PROCEDURE IF EXISTS `BuscaPrimProdutoNome`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaPrimProdutoNome`()
BEGIN
SELECT prod_nome from produtos ORDER BY prod_nome LIMIT 1;
END$$

DROP PROCEDURE IF EXISTS `BuscaProdutoCod`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaProdutoCod`(`v_prod_cod` VARCHAR(10))
BEGIN
SELECT * from produtos where prod_cod = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `BuscaProdutoNome`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaProdutoNome`()
BEGIN
SELECT prod_nome from produtos ORDER BY prod_nome;
END$$

DROP PROCEDURE IF EXISTS `BuscaProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscaProdutos`()
BEGIN
SELECT * FROM produtos ORDER BY prod_nome;
END$$

DROP PROCEDURE IF EXISTS `custoFichaProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `custoFichaProduto`(`v_ft_nome` VARCHAR(70))
BEGIN
SELECT SUM(`ft_prod_custo` * `ft_prod_qtd`) totalft FROM `produtosficha` WHERE `ft_nome` = v_ft_nome;
END$$

DROP PROCEDURE IF EXISTS `DeletaProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletaProduto`(`v_prod_cod` VARCHAR(10))
BEGIN
DELETE FROM `produtos` WHERE `prod_cod` = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `DeletaProdutoFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletaProdutoFicha`(`v_ft_nome` VARCHAR(70), `v_ft_prod_cod` VARCHAR(10))
BEGIN
DELETE FROM `produtosficha` WHERE ft_nome = v_ft_nome AND ft_prod_cod = v_ft_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `EditaUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EditaUsuario`(`v_usu_nome` VARCHAR(70), `v_usu_login` VARCHAR(20), `v_usu_senha` VARCHAR(20), `v_usu_perfil_id` INT(2))
BEGIN
UPDATE usuario SET `usu_nome`= v_usu_nome,`usu_senha`= v_usu_senha,`usu_perfil_id`= v_usu_perfil_id
WHERE `usu_login` = v_usu_login;
END$$

DROP PROCEDURE IF EXISTS `IncluirFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `IncluirFicha`(IN `v_ft_nome` VARCHAR(70), IN `v_ft_usu_id_criacao` INT(4))
BEGIN
INSERT INTO `fichatecnica`(`ft_nome`, `ft_dt_criacao`, `ft_usu_id_criacao`)VALUES (v_ft_nome,CURDATE(), v_ft_usu_id_criacao);
END$$

DROP PROCEDURE IF EXISTS `InserirProdutoFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirProdutoFicha`(`v_ft_nome` VARCHAR(70), `v_ft_prod_nome` VARCHAR(70), `v_ft_prod_cod` VARCHAR(10), `v_ft_prod_custo` DECIMAL(10,2), `v_ft_prod_qtd` INT(4))
BEGIN
INSERT INTO `produtosficha`(`ft_nome`, `ft_prod_nome`, `ft_prod_cod`, `ft_prod_custo`, `ft_prod_qtd`) VALUES (v_ft_nome, v_ft_prod_nome, v_ft_prod_cod, v_ft_prod_custo, v_ft_prod_qtd);
END$$

DROP PROCEDURE IF EXISTS `InserirProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirProdutos`(`v_prod_cod` VARCHAR(10), `v_prod_nome` VARCHAR(70), `v_prod_servico` INT(1), `v_prod_custo` DOUBLE(7,2), `v_prod_usu_id_criacao` INT(4))
BEGIN
INSERT INTO `produtos`(`prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`,`prod_usu_id_criacao`) VALUES (v_prod_cod, v_prod_nome, v_prod_servico, v_prod_custo, CURDATE(), v_prod_usu_id_criacao);

END$$

DROP PROCEDURE IF EXISTS `InserirUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirUsuario`(`v_usu_nome` VARCHAR(70), `v_usu_login` VARCHAR(20), `v_usu_senha` VARCHAR(20), `v_usu_perfil_id` INT(2))
BEGIN
INSERT INTO `usuario`(`usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`,`usu_status`)
VALUES (v_usu_nome, v_usu_login, v_usu_senha, v_usu_perfil_id,1);
END$$

DROP PROCEDURE IF EXISTS `ListaFichaTecnica`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListaFichaTecnica`()
BEGIN
SELECT * FROM `fichatecnica`;
END$$

DROP PROCEDURE IF EXISTS `ListaProdutosFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListaProdutosFicha`(`v_ft_nome` VARCHAR(70))
BEGIN
SELECT * FROM `produtosficha` WHERE ft_nome = v_ft_nome ;
END$$

DROP PROCEDURE IF EXISTS `ListaUsuarios`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListaUsuarios`()
BEGIN
SELECT * FROM usuario ORDER BY usu_status DESC;
END$$

DROP PROCEDURE IF EXISTS `ProdutoInfo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProdutoInfo`(`v_prod_nome` VARCHAR(70))
BEGIN
SELECT prod_cod, prod_custo from produtos WHERE prod_nome = v_prod_nome;
END$$

DROP PROCEDURE IF EXISTS `ValidaCodProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ValidaCodProduto`(`v_prod_cod` VARCHAR(10))
BEGIN
SELECT * from produtos WHERE prod_cod = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `ValidaNomeProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ValidaNomeProduto`(`v_prod_nome` VARCHAR(70))
BEGIN
SELECT * from produtos WHERE prod_nome = v_prod_nome;
END$$

DROP PROCEDURE IF EXISTS `VerificaFichaTecnica`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificaFichaTecnica`(`v_ft_nome` VARCHAR(70))
BEGIN
SELECT * FROM `fichatecnica` WHERE ft_nome = v_ft_nome;
END$$

DROP PROCEDURE IF EXISTS `verificaProdutoFicha`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificaProdutoFicha`(`v_ft_nome` VARCHAR(70), `v_ft_prod_cod` VARCHAR(10))
BEGIN
SELECT * FROM `produtosficha` WHERE `ft_nome` = v_ft_nome AND `ft_prod_nome` = v_ft_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `VerificaStatus`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificaStatus`(`v_usu_login` VARCHAR(20))
BEGIN
SELECT `usu_status` FROM `usuario` WHERE usu_login = v_usu_login;
END$$

DROP PROCEDURE IF EXISTS `VerificaUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificaUsuario`(`v_usu_login` VARCHAR(20))
BEGIN
SELECT * FROM `usuario` WHERE usu_login = v_usu_login;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fichatecnica`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Extraindo dados da tabela `fichatecnica`
--

INSERT INTO `fichatecnica` (`ft_id`, `ft_nome`, `ft_dt_criacao`, `ft_dt_alteracao`, `ft_total_custo`, `ft_usu_id_criacao`, `ft_usu_id_alteracao`, `ft_percent`, `ft_enquad_fiscal`) VALUES
(11, 'Ficha 01', '2017-07-26', NULL, '215.42', 1, 1, '20.00', 0),
(12, 'Ficha 02', '2017-07-26', NULL, '92.66', 1, 1, '15.00', 1),
(13, 'Ficha 03', '2017-07-26', NULL, '501.29', 1, 1, '10.00', 1),
(14, 'Ficha 04', '2017-07-26', NULL, '175.35', 1, 1, '10.00', 1),
(15, 'Ficha 05', '2017-07-26', NULL, '866.61', 1, 1, '18.00', 1),
(16, 'Ficha 06', '2017-07-26', NULL, '489.51', 1, 1, '18.00', 0),
(17, 'Ficha 07', '2017-07-26', NULL, '1183.22', 1, 1, '50.00', 1),
(18, 'Ficha 08', '2017-07-26', NULL, '253.70', 1, 1, '12.00', 1),
(19, 'Ficha 09', '2017-07-26', NULL, '303.08', 1, 1, '25.00', 1),
(20, 'Ficha 10', '2017-07-26', NULL, '406.98', 1, 1, '22.30', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=30 ;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`prod_id`, `prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`, `prod_dt_alteracao`, `prod_usu_id_criacao`, `prod_usu_id_alteracao`) VALUES
(15, 'cod 01', 'Nome 01', 0, '10.20', '2017-07-26', NULL, 1, NULL),
(16, 'cod 02', 'Nome 02', 0, '21.30', '2017-07-26', NULL, 1, NULL),
(17, 'cod 03', 'Nome 03', 0, '1.20', '2017-07-26', NULL, 1, NULL),
(18, 'cod 04', 'Nome 04', 1, '60.20', '2017-07-26', NULL, 1, NULL),
(19, 'cod 05', 'Nome 05', 0, '3.33', '2017-07-26', NULL, 1, NULL),
(20, 'cod 06', 'Nome 06', 0, '8.51', '2017-07-26', NULL, 1, NULL),
(21, 'cod 07', 'Nome 07', 0, '1.89', '2017-07-26', NULL, 1, NULL),
(22, 'cod 09', 'Nome 09', 1, '80.45', '2017-07-26', NULL, 1, NULL),
(23, 'cod 10', 'Nome 10', 0, '5.65', '2017-07-26', NULL, 1, NULL),
(24, 'cod 11', 'Nome 11', 0, '20.30', '2017-07-26', NULL, 1, NULL),
(25, 'cod 12', 'Nome 12', 1, '8.80', '2017-07-26', NULL, 1, NULL),
(26, 'cod 13', 'Nome 13', 0, '0.55', '2017-07-26', NULL, 1, NULL),
(27, 'cod 14', 'Nome 14', 0, '1.99', '2017-07-26', NULL, 1, NULL),
(28, 'cod 15', 'Nome 15', 0, '4.21', '2017-07-26', NULL, 1, NULL),
(29, 'cod 08', 'Nome 08', 0, '23.20', '2017-07-26', NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtosficha`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=72 ;

--
-- Extraindo dados da tabela `produtosficha`
--

INSERT INTO `produtosficha` (`id_prod_ficha`, `ft_nome`, `ft_prod_nome`, `ft_prod_cod`, `ft_prod_custo`, `ft_prod_qtd`) VALUES
(29, 'Ficha 01', 'Nome 04', 'cod 04', '60.20', 1),
(30, 'Ficha 01', 'Nome 05', 'cod 05', '3.33', 5),
(31, 'Ficha 01', 'Nome 13', 'cod 13', '0.55', 20),
(32, 'Ficha 01', 'Nome 09', 'cod 09', '80.45', 1),
(33, 'Ficha 02', 'Nome 02', 'cod 02', '21.30', 2),
(34, 'Ficha 02', 'Nome 03', 'cod 03', '1.20', 15),
(35, 'Ficha 02', 'Nome 07', 'cod 07', '1.89', 4),
(36, 'Ficha 02', 'Nome 15', 'cod 15', '4.21', 2),
(37, 'Ficha 03', 'Nome 11', 'cod 11', '20.30', 2),
(38, 'Ficha 03', 'Nome 10', 'cod 10', '5.65', 42),
(39, 'Ficha 03', 'Nome 04', 'cod 04', '60.20', 1),
(40, 'Ficha 03', 'Nome 06', 'cod 06', '8.51', 7),
(41, 'Ficha 03', 'Nome 03', 'cod 03', '1.20', 22),
(42, 'Ficha 04', 'Nome 13', 'cod 13', '0.55', 56),
(43, 'Ficha 04', 'Nome 09', 'cod 09', '80.45', 1),
(44, 'Ficha 04', 'Nome 05', 'cod 05', '3.33', 10),
(45, 'Ficha 05', 'Nome 05', 'cod 01', '10.20', 7),
(46, 'Ficha 05', 'Nome 06', 'cod 06', '8.51', 30),
(47, 'Ficha 05', 'Nome 01', 'cod 01', '10.20', 20),
(48, 'Ficha 05', 'Nome 11', 'cod 11', '20.30', 2),
(49, 'Ficha 05', 'Nome 12', 'cod 12', '8.80', 5),
(50, 'Ficha 05', 'Nome 14', 'cod 14', '1.99', 42),
(51, 'Ficha 06', 'Nome 01', 'cod 01', '10.20', 12),
(52, 'Ficha 06', 'Nome 02', 'cod 02', '21.30', 1),
(53, 'Ficha 06', 'Nome 03', 'cod 03', '1.20', 12),
(54, 'Ficha 06', 'Nome 04', 'cod 04', '60.20', 1),
(55, 'Ficha 06', 'Nome 06', 'cod 06', '8.51', 20),
(56, 'Ficha 07', 'Nome 15', 'cod 15', '4.21', 6),
(57, 'Ficha 07', 'Nome 12', 'cod 12', '8.80', 23),
(58, 'Ficha 07', 'Nome 06', 'cod 06', '8.51', 55),
(59, 'Ficha 07', 'Nome 02', 'cod 02', '21.30', 2),
(60, 'Ficha 08', 'Nome 02', 'cod 01', '10.20', 1),
(61, 'Ficha 08', 'Nome 04', 'cod 04', '60.20', 2),
(62, 'Ficha 08', 'Nome 09', 'cod 09', '80.45', 1),
(63, 'Ficha 09', 'Nome 08', 'cod 08', '23.20', 3),
(64, 'Ficha 09', 'Nome 04', 'cod 04', '60.20', 1),
(65, 'Ficha 09', 'Nome 12', 'cod 12', '8.80', 7),
(66, 'Ficha 09', 'Nome 05', 'cod 05', '3.33', 12),
(67, 'Ficha 10', 'Nome 05', 'cod 05', '3.33', 15),
(68, 'Ficha 10', 'Nome 10', 'cod 10', '5.65', 23),
(69, 'Ficha 10', 'Nome 06', 'cod 06', '8.51', 10),
(70, 'Ficha 10', 'Nome 13', 'cod 13', '0.55', 40),
(71, 'Ficha 10', 'Nome 12', 'cod 12', '8.80', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`usu_id`, `usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`, `usu_status`) VALUES
(1, 'Jean', 'jean', '1234', 1, 1),
(3, 'Marcio', 'marcio', '1234', 1, 1),
(5, 'Renato', 'renato', '1234', 1, 1),
(6, 'Vinicius', 'vinicius', '1234', 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
