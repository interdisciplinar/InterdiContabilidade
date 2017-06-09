-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 09-Jun-2017 às 13:42
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
  `ft_prod_id` int(6) NOT NULL,
  `ft_qtd_prod` decimal(7,2) NOT NULL,
  `ft_dt_criacao` date NOT NULL,
  `ft_dt_alteracao` date DEFAULT NULL,
  `ft_total_custo` decimal(4,0) NOT NULL,
  `ft_usu_id_criacao` int(4) NOT NULL,
  `ft_usu_id_alteracao` int(4) DEFAULT NULL,
  PRIMARY KEY (`ft_id`),
  UNIQUE KEY `ft_id` (`ft_id`)
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`prod_id`, `prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`, `prod_dt_alteracao`, `prod_usu_id_criacao`, `prod_usu_id_alteracao`) VALUES
(8, 'teste1234', 'teste222', 1, '1233.00', '2017-06-08', NULL, 1, NULL),
(10, 'teste12345', 'teste356', 1, '222.00', '2017-06-08', NULL, 1, NULL),
(13, 'teste6539', 'teste', 1, '33232.00', '2017-06-08', NULL, 1, NULL);

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
  PRIMARY KEY (`usu_id`),
  UNIQUE KEY `usu_id` (`usu_id`),
  UNIQUE KEY `usu_login` (`usu_login`(4))
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`usu_id`, `usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`) VALUES
(1, 'Jean', 'jean', '1234', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
