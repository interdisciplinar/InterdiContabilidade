-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 20-Jun-2017 às 21:32
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
-- Esse comando aqui não vem junto quando exporta o banco, mas é esse o nome da tabela que é pra criar(Vinicius)
CREATE DATABASE intercontabilidade

-- --------------------------------------------------------

--
-- Estrutura da tabela `fichatecnica`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE IF NOT EXISTS `produtos` (
  `prod_id` int(6) NOT NULL AUTO_INCREMENT,
  `prod_cod` varchar(10) NOT NULL,
  `prod_nome` varchar(70) NOT NULL,
  `prod_servico` int(1) NOT NULL,
  `prod_custo` decimal(7,2) NOT NULL,
  `prod_dt_criacao` date NOT NULL,
  `prod_dt_alteracao` date DEFAULT NULL,
  `prod_usu_id_criacao` int(4) NOT NULL,
  `prod_usu_id_alteracao` int(4) DEFAULT NULL,
  PRIMARY KEY (`prod_id`),
  UNIQUE KEY `prod_id` (`prod_id`),
  UNIQUE KEY `prod_cod` (`prod_cod`),
  UNIQUE KEY `prod_nome` (`prod_nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

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
(33, '123', 'marcio', 1, '0.32', '2017-06-12', '2017-06-20', 1, 1),
(35, 'teste678', 'teste678', 1, '678.00', '2017-06-12', NULL, 1, NULL),
(36, 'teste6789', 'teste6789', 1, '678.00', '2017-06-12', NULL, 1, NULL),
(37, 'teste680', 'teste680', 1, '680.00', '2017-06-12', NULL, 1, NULL),
(38, 'teste681', 'teste681', 1, '681.00', '2017-06-12', NULL, 1, NULL),
(39, 'teste685', 'teste685', 1, '685.00', '2017-06-12', NULL, 1, NULL),
(40, 'teste699', 'teste699', 0, '699.00', '2017-06-13', NULL, 1, NULL),
(41, 'teste5555', 'super produto', 1, '2222.00', '2017-06-17', '2017-06-19', 1, 1),
(43, 'teste9898', 'teste9898', 0, '9898.00', '2017-06-19', NULL, 1, NULL),
(44, 'testebtn', 'testebtn', 0, '212.00', '2017-06-19', NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtosficha`
--

CREATE TABLE IF NOT EXISTS `produtosficha` (
  `ft_nome` varchar(70) NOT NULL,
  `ft_prod_nome` varchar(70) NOT NULL,
  `ft_prod_cod` varchar(10) NOT NULL,
  `ft_prod_custo` decimal(7,2) NOT NULL,
  `ft_prod_qtd` int(4) NOT NULL,
  UNIQUE KEY `ft_nome` (`ft_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

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
(2, 'Vinicius', 'vini', 'vinicius123', 1, 0),
(3, 'Marcio', 'marcio', '1234', 0, 0),
(4, 'Jose Renato', 'jose', '1234', 1, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
