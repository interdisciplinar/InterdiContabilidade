-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25-Jun-2017 às 06:56
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

DROP PROCEDURE IF EXISTS `DeletaProduto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletaProduto`(`v_prod_cod` VARCHAR(10))
BEGIN
DELETE FROM `produtos` WHERE `prod_cod` = v_prod_cod;
END$$

DROP PROCEDURE IF EXISTS `InserirProdutos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InserirProdutos`(`v_prod_cod` VARCHAR(10), `v_prod_nome` VARCHAR(70), `v_prod_servico` INT(1), `v_prod_custo` DOUBLE(7,2), `v_prod_usu_id_criacao` INT(4))
BEGIN
INSERT INTO `produtos`(`prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`,`prod_usu_id_criacao`) VALUES (v_prod_cod, v_prod_nome, v_prod_servico, v_prod_custo, CURDATE(), v_prod_usu_id_criacao);

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
  `ft_total_custo` decimal(7,2) NOT NULL,
  `ft_usu_id_criacao` int(4) NOT NULL,
  `ft_usu_id_alteracao` int(4) DEFAULT NULL,
  PRIMARY KEY (`ft_id`),
  UNIQUE KEY `ft_id` (`ft_id`),
  UNIQUE KEY `ft_nome` (`ft_nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Extraindo dados da tabela `fichatecnica`
--

INSERT INTO `fichatecnica` (`ft_id`, `ft_nome`, `ft_dt_criacao`, `ft_dt_alteracao`, `ft_total_custo`, `ft_usu_id_criacao`, `ft_usu_id_alteracao`) VALUES
(1, 'teste1', '2017-06-23', NULL, '32.00', 1, NULL),
(2, 'teste2', '2017-06-23', NULL, '32.00', 1, NULL),
(3, 'teste', '2017-06-23', NULL, '32.00', 1, NULL),
(4, 'teste7', '2017-06-23', NULL, '32.00', 1, NULL),
(5, 'teste8', '2017-06-23', NULL, '32.00', 1, NULL),
(6, 'teste9', '2017-06-23', NULL, '32.00', 1, NULL),
(7, 'teste10', '2017-06-23', NULL, '32.00', 1, NULL),
(8, 'teste11', '2017-06-23', NULL, '32.00', 1, NULL),
(9, 'teste12', '2017-06-23', NULL, '32.00', 1, NULL),
(10, 'teste13', '2017-06-23', NULL, '32.00', 1, NULL),
(11, 'teste14', '2017-06-23', NULL, '32.00', 1, NULL),
(12, 'teste15', '2017-06-23', NULL, '32.00', 1, NULL),
(13, 'teste16', '2017-06-23', NULL, '32.00', 1, NULL),
(14, 'teste19', '2017-06-23', NULL, '32.00', 1, NULL),
(15, 'teste27', '2017-06-23', NULL, '32.00', 1, NULL),
(16, 'teste28', '2017-06-23', NULL, '32.00', 1, NULL),
(17, 'teste88', '2017-06-23', NULL, '32.00', 1, NULL),
(18, 'teste89', '2017-06-23', NULL, '32.00', 1, NULL),
(19, 'teste90', '2017-06-23', NULL, '32.00', 1, NULL),
(20, 'teste100', '2017-06-23', NULL, '32.00', 1, NULL),
(21, 'teste101', '2017-06-23', NULL, '32.00', 1, NULL),
(22, 'teste8000', '2017-06-23', NULL, '32.00', 1, NULL),
(23, 'teste9000', '2017-06-23', NULL, '32.00', 1, NULL),
(24, 'teste9001', '2017-06-23', NULL, '32.00', 1, NULL),
(25, 'teste9002', '2017-06-23', NULL, '32.00', 1, NULL),
(26, 'teste10000', '2017-06-25', NULL, '32.00', 1, NULL),
(27, 'teste10001', '2017-06-25', NULL, '666.66', 1, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=47 ;

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
(46, '1234', 'marcios', 0, '32.00', '2017-06-20', NULL, 1, NULL);

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
  PRIMARY KEY (`id_prod_ficha`),
  UNIQUE KEY `ft_nome` (`ft_nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

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
(17, 'teste9', 'marcios', '1234', '32.00', 0),
(18, 'teste90', 'marcios', '1234', '32.00', 0),
(19, 'teste9000', 'marcios', '1234', '32.00', 0),
(20, 'teste9001', 'marcios', '1234', '32.00', 0),
(21, 'teste9002', 'marcios', '1234', '32.00', 0),
(22, 'teste10001', 'teste667', 'teste667', '666.66', 0);

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
(1, 'Jean', 'jean', '1234', 1, 0),
(2, 'vinicius1234', 'vinicius1234', 'vinicius1234', 1, 1),
(3, 'Marcio', 'marcio', '1234', 1, 1),
(4, 'Jose Renato', 'jose', '1234', 1, 1);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `produtosficha`
--
ALTER TABLE `produtosficha`
  ADD CONSTRAINT `produtosficha_ibfk_1` FOREIGN KEY (`ft_nome`) REFERENCES `fichatecnica` (`ft_nome`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
