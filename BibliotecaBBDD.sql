CREATE DATABASE `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

CREATE TABLE `copias`
(
    `id_copia`       bigint(20) NOT NULL AUTO_INCREMENT,
    `editorial`      varchar(40) DEFAULT NULL,
    `fd_adquisicion` datetime    DEFAULT NULL,
    `id_libro`       bigint(20)  DEFAULT NULL,
    PRIMARY KEY (`id_copia`),
    KEY `FK_COPIAS_LIBRO` (`id_libro`),
    CONSTRAINT `FK_COPIAS_LIBRO` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `lectores`
(
    `id_lector`       bigint(20) NOT NULL AUTO_INCREMENT,
    `dni`             varchar(9)  DEFAULT NULL,
    `fd_nacimiento`   datetime    DEFAULT NULL,
    `nombre_completo` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`id_lector`),
    UNIQUE KEY `UK_DNI` (`dni`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `libros`
(
    `id_libro` bigint(20) NOT NULL AUTO_INCREMENT,
    `area`     varchar(255) DEFAULT NULL,
    `autor`    varchar(40)  DEFAULT NULL,
    `titulo`   varchar(40)  DEFAULT NULL,
    PRIMARY KEY (`id_libro`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `prestamos`
(
    `id_copia`    bigint(20) NOT NULL,
    `active`      bit(1)     DEFAULT NULL,
    `fd_devolver` datetime   DEFAULT NULL,
    `fd_prestamo` datetime   DEFAULT NULL,
    `id_lector`   bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id_copia`),
    KEY `FK_PRESTAMO_LECTOR` (`id_lector`),
    CONSTRAINT `FK_PRESTAMO_COPIA` FOREIGN KEY (`id_copia`) REFERENCES `copias` (`id_copia`),
    CONSTRAINT `FK_PRESTAMO_LECTOR` FOREIGN KEY (`id_lector`) REFERENCES `lectores` (`id_lector`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `tarjetas`
(
    `id_tarjeta`   bigint(20) NOT NULL AUTO_INCREMENT,
    `active`       bit(1)     DEFAULT NULL,
    `fd_caducidad` datetime   DEFAULT NULL,
    `fd_castigo`   datetime   DEFAULT NULL,
    `fd_creacion`  datetime   DEFAULT NULL,
    `id_lector`    bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id_tarjeta`),
    UNIQUE KEY `UK_LECTOR` (`id_lector`),
    CONSTRAINT `FK_TARJETA_LECTOR` FOREIGN KEY (`id_lector`) REFERENCES `lectores` (`id_lector`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `usuarios`
(
    `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
    `email`      varchar(255) DEFAULT NULL,
    `password`   varchar(255) DEFAULT NULL,
    `rol`        varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id_usuario`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
