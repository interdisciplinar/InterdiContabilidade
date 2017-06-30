-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 30-Jun-2017 às 23:35
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
CREATE DATABASE IF NOT EXISTS `intercontabilidade` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `custoFichaProduto`(v_ft_nome VARCHAR(70))
BEGIN
SELECT SUM(`ft_prod_custo`) FROM `produtosficha` WHERE `ft_nome` = v_ft_nome;
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `IncluirFicha`(`v_ft_nome` VARCHAR(70), `v_ft_usu_id_criacao` INT(4))
BEGIN
INSERT INTO `fichatecnica`(`ft_nome`, `ft_dt_criacao`, `ft_total_custo`, `ft_usu_id_criacao`)VALUES (v_ft_nome,CURDATE(), v_ft_usu_id_criacao);
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificaProdutoFicha`(v_ft_nome VARCHAR(70), v_ft_prod_cod VARCHAR(10))
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=45 ;

--
-- Extraindo dados da tabela `fichatecnica`
--

INSERT INTO `fichatecnica` (`ft_id`, `ft_nome`, `ft_dt_criacao`, `ft_dt_alteracao`, `ft_total_custo`, `ft_usu_id_criacao`, `ft_usu_id_alteracao`, `ft_percent`, `ft_enquad_fiscal`) VALUES
(1, 'teste1', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(2, 'teste2', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(3, 'teste', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(4, 'teste7', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(5, 'teste8', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(6, 'teste9', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(7, 'teste10', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(8, 'teste11', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(9, 'teste12', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(10, 'teste13', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(11, 'teste14', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(12, 'teste15', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(13, 'teste16', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(14, 'teste19', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(15, 'teste27', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(16, 'teste28', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(17, 'teste88', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(18, 'teste89', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(19, 'teste90', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(20, 'teste100', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(21, 'teste101', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(22, 'teste8000', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(23, 'teste9000', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(24, 'teste9001', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(25, 'teste9002', '2017-06-23', NULL, '32.00', 1, NULL, NULL, NULL),
(26, 'teste10000', '2017-06-25', NULL, '32.00', 1, NULL, NULL, NULL),
(27, 'teste10001', '2017-06-25', NULL, '666.66', 1, NULL, NULL, NULL),
(28, 'teste10002', '2017-06-25', NULL, '32.00', 1, 4, NULL, NULL),
(29, 'teste11000', '2017-06-25', NULL, '670.00', 1, NULL, NULL, NULL),
(30, 'teste9999', '2017-06-26', NULL, '669.00', 1, NULL, NULL, NULL),
(31, 'aaa', '2017-06-26', NULL, '32.00', 1, NULL, NULL, NULL),
(32, '24teste24', '2017-06-26', NULL, '32.00', 1, NULL, NULL, NULL),
(33, 'porqwohqofawihf', '2017-06-26', NULL, '32.00', 1, NULL, NULL, NULL),
(34, 'teste9nklxnklnkl', '2017-06-26', NULL, '32.00', 1, NULL, NULL, NULL),
(35, 'kjkjkjbkjdgkjb', '2017-06-26', NULL, '32.00', 1, NULL, NULL, NULL),
(36, 'teste33333', '2017-06-26', NULL, '32.00', 1, NULL, NULL, NULL),
(37, 'teste333333', '2017-06-26', NULL, '2222.00', 1, NULL, NULL, NULL),
(38, 'teste777777777', '2017-06-28', NULL, '32.00', 1, NULL, NULL, NULL),
(39, 'teste99999999', '2017-06-28', NULL, '32.00', 1, NULL, NULL, NULL),
(40, 'teste8888888', '2017-06-28', NULL, '32.00', 1, NULL, NULL, NULL),
(41, 'teste333333333333', '2017-06-28', NULL, '32.00', 1, NULL, NULL, NULL),
(42, 'teste95995959', '2017-06-28', NULL, '32.00', 1, NULL, NULL, NULL),
(43, 'tesafawdawdawdawd', '2017-06-28', NULL, '32.00', 1, NULL, NULL, NULL),
(44, 'fffgfgfgfgfgfg', '2017-06-28', NULL, '699.00', 1, NULL, NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=48 ;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`prod_id`, `prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`, `prod_dt_alteracao`, `prod_usu_id_criacao`, `prod_usu_id_alteracao`) VALUES
(17, 'sssss', 'tevvvvvv', 1, '0.01', '2017-06-12', '2017-06-18', 1, 1),
(21, 'teste667', 'teste667', 0, '666.66', '2017-06-12', '2017-06-20', 1, 1),
(22, 'teste668', 'teste668', 1, '668.00', '2017-06-12', NULL, 1, NULL),
(23, 'teste669', 'teste669', 1, '669.00', '2017-06-12', NULL, 1, NULL),
(24, 'teste670', 'teste670', 1, '670.00', '2017-06-12', NULL, 1, NULL),
(25, 'teste671', 'teste671', 1, '671.00', '2017-06-12', NULL, 1, NULL),
(26, 'teste672', 'teste672', 1, '672.00', '2017-06-12', NULL, 1, NULL),
(27, 'teste673', 'teste673', 1, '673.00', '2017-06-12', NULL, 1, NULL),
(29, 'teste674', 'teste674', 1, '674.00', '2017-06-12', NULL, 1, NULL),
(30, 'teste675', 'teste675', 1, '675.00', '2017-06-12', NULL, 1, NULL),
(31, 'teste676', 'teste676', 1, '676.00', '2017-06-12', NULL, 1, NULL),
(32, 'teste677', 'teste677', 1, '677.00', '2017-06-12', NULL, 1, NULL),
(35, 'teste678', 'teste678', 1, '678.00', '2017-06-12', NULL, 1, NULL),
(36, 'teste6789', 'teste6789', 1, '678.00', '2017-06-12', NULL, 1, NULL),
(37, 'teste680', 'teste680', 1, '680.00', '2017-06-12', NULL, 1, NULL),
(38, 'teste681', 'teste681', 1, '681.00', '2017-06-12', NULL, 1, NULL),
(39, 'teste685', 'teste685', 1, '685.00', '2017-06-12', NULL, 1, NULL),
(40, 'teste699', 'teste699', 0, '699.00', '2017-06-13', NULL, 1, NULL),
(41, 'teste5555', 'super produto', 1, '2222.00', '2017-06-17', '2017-06-19', 1, 1),
(43, 'teste9898', 'teste9898', 0, '9898.00', '2017-06-19', NULL, 1, NULL),
(44, 'testebtn', 'testebtn', 0, '212.00', '2017-06-19', NULL, 1, NULL),
(46, '1234', 'marcios', 0, '32.00', '2017-06-20', NULL, 1, NULL),
(47, '123456', 'teste7895555', 0, '33.00', '2017-06-26', NULL, 4, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=97 ;

--
-- Extraindo dados da tabela `produtosficha`
--

INSERT INTO `produtosficha` (`id_prod_ficha`, `ft_nome`, `ft_prod_nome`, `ft_prod_cod`, `ft_prod_custo`, `ft_prod_qtd`) VALUES
(1, 'teste10', 'marcios', '1234', '32.00', 0),
(2, 'teste100', 'marcios', '1234', '32.00', 0),
(3, 'teste10000', 'marcios', '1234', '32.00', 0),
(4, 'teste101', 'marcios', '1234', '32.00', 0),
(5, 'teste11', 'marcios', '1234', '32.00', 0),
(6, 'teste12', 'marcios', '1234', '32.00', 0),
(7, 'teste13', 'marcios', '1234', '32.00', 0),
(8, 'teste14', 'marcios', '1234', '32.00', 0),
(9, 'teste15', 'super produto', '1234', '32.00', 0),
(10, 'teste16', 'marcios', '1234', '32.00', 0),
(11, 'teste19', 'marcios', '1234', '32.00', 0),
(12, 'teste27', 'marcios', '1234', '32.00', 0),
(13, 'teste28', 'marcios', '1234', '32.00', 0),
(14, 'teste8000', 'marcios', '1234', '32.00', 0),
(15, 'teste88', 'marcios', '1234', '32.00', 0),
(16, 'teste89', 'marcios', '1234', '32.00', 0),
(18, 'teste90', 'marcios', '1234', '32.00', 0),
(19, 'teste9000', 'marcios', '1234', '32.00', 0),
(20, 'teste9001', 'marcios', '1234', '32.00', 0),
(21, 'teste9002', 'marcios', '1234', '32.00', 0),
(22, 'teste10001', 'teste667', 'teste667', '666.66', 0),
(24, 'teste10002', 'teste668', 'teste668', '668.00', 0),
(25, 'teste10002', 'teste670', 'teste670', '670.00', 0),
(26, 'teste11000', 'teste670', 'teste670', '670.00', 0),
(27, 'teste10', 'super produto', 'teste5555', '2222.00', 0),
(28, 'teste10', 'marcios', '1234', '32.00', 0),
(34, 'teste9', 'marcios', '1234', '32.00', 0),
(36, 'teste9', 'teste669', 'teste669', '669.00', 0),
(37, 'teste9999', 'teste669', 'teste669', '669.00', 0),
(38, 'teste9999', 'marcios', '1234', '32.00', 0),
(39, 'teste9', 'teste667', 'teste667', '666.66', 0),
(42, 'aaa', 'marcios', '1234', '32.00', 3),
(43, 'aaa', 'super produto', 'teste5555', '2222.00', 0),
(44, '24teste24', '', '1234', '32.00', 222),
(45, '24teste24', 'super produto', 'teste5555', '2222.00', 0),
(46, '24teste24', 'teste667', 'teste667', '666.66', 0),
(47, '24teste24', 'teste667', 'teste667', '666.66', 0),
(48, '24teste24', 'teste699', 'teste699', '699.00', 0),
(49, '24teste24', 'teste699', 'teste699', '699.00', 0),
(50, '24teste24', 'marcios', '1234', '32.00', 0),
(51, 'porqwohqofawihf', 'marcios', '1234', '32.00', 33),
(52, 'porqwohqofawihf', 'teste668', 'teste668', '668.00', 0),
(53, 'porqwohqofawihf', 'teste668', 'teste668', '668.00', 0),
(54, 'porqwohqofawihf', 'teste670', 'teste670', '670.00', 0),
(55, 'teste9', 'super produto', 'teste5555', '2222.00', 0),
(56, 'teste9', 'teste7895555', '123456', '33.00', 0),
(57, 'teste9', 'teste7895555', '123456', '33.00', 0),
(58, 'teste9nklxnklnkl', 'teste7895555', '1234', '32.00', 3),
(59, 'kjkjkjbkjdgkjb', 'marcios', '1234', '32.00', 3),
(60, 'kjkjkjbkjdgkjb', 'marcios', '1234', '32.00', 0),
(61, 'kjkjkjbkjdgkjb', 'marcios', '1234', '32.00', 0),
(62, 'kjkjkjbkjdgkjb', 'teste667', 'teste667', '666.66', 0),
(63, 'kjkjkjbkjdgkjb', 'teste667', 'teste667', '666.66', 0),
(64, 'teste33333', '', '1234', '32.00', 33),
(65, 'teste33333', '', '1234', '32.00', 0),
(66, 'teste33333', 'super produto', 'teste5555', '2222.00', 0),
(67, 'teste333333', 'super produto', 'teste5555', '2222.00', 33),
(68, 'teste777777777', 'marcios', '1234', '32.00', 33),
(69, 'teste777777777', 'super produto', 'teste5555', '2222.00', 0),
(70, 'teste99999999', 'marcios', '1234', '32.00', 33),
(71, 'teste99999999', 'super produto', 'teste5555', '2222.00', 0),
(72, 'teste8888888', 'marcios', '1234', '32.00', 3),
(73, 'teste8888888', 'teste668', 'teste668', '668.00', 3),
(74, 'teste8888888', 'teste671', 'teste671', '671.00', 0),
(75, 'teste333333333333', 'marcios', '1234', '32.00', 33),
(76, 'teste95995959', 'marcios', '1234', '32.00', 33),
(77, 'teste95995959', 'marcios', '1234', '32.00', 0),
(78, 'teste95995959', 'marcios', '1234', '32.00', 0),
(79, 'tesafawdawdawdawd', 'marcios', '1234', '32.00', 22),
(80, 'tesafawdawdawdawd', 'teste668', 'teste668', '668.00', 0),
(81, 'tesafawdawdawdawd', 'teste670', 'teste670', '670.00', 0),
(82, 'tesafawdawdawdawd', 'teste670', 'teste670', '670.00', 0),
(83, 'tesafawdawdawdawd', 'teste699', 'teste699', '699.00', 0),
(84, 'fffgfgfgfgfgfg', 'teste699', 'teste699', '699.00', 3),
(85, 'fffgfgfgfgfgfg', 'teste7895555', '123456', '33.00', 0),
(86, 'fffgfgfgfgfgfg', 'teste7895555', '123456', '33.00', 3),
(87, 'fffgfgfgfgfgfg', 'teste7895555', '123456', '33.00', 5),
(88, 'fffgfgfgfgfgfg', 'tevvvvvv', 'sssss', '0.01', 3),
(89, 'fffgfgfgfgfgfg', 'marcios', '1234', '32.00', 5),
(90, 'uuuuuuuuuuuuuu', 'marcios', '1234', '32.00', 1),
(91, 'teste100023', 'marcios', '1234', '32.00', 6),
(92, 'teste100022', 'marcios', '1234', '32.00', 1),
(95, 'teste10002', 'teste680', 'teste680', '680.00', 0),
(96, 'teste10002', 'marcios', '1234', '32.00', 9);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`usu_id`, `usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`, `usu_status`) VALUES
(1, 'Jean', 'jean', '1234', 1, 1),
(2, 'vinicius123456', 'vinicius1234', 'vinicius1234', 1, 1),
(3, 'Marcio', 'marcio', '1234', 1, 1),
(4, 'Jose Renato', 'jose', '1234', 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
