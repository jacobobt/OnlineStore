-- Online Store - Script de Base de Datos
-- INNERJOINSQUAD - P3
-- Incluye creación de la BD, tablas, procedimiento, inserción de datos iniciales.

DROP DATABASE IF EXISTS online_store;
CREATE DATABASE online_store;
USE online_store;


-- Tabla clientes
CREATE TABLE clientes (
    email_cliente VARCHAR(100) NOT NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    domicilio_cliente VARCHAR(150) NOT NULL,
    nif_cliente VARCHAR(20) NOT NULL,
    tipo_cliente VARCHAR(20) NOT NULL,
    DTYPE VARCHAR(31) NOT NULL DEFAULT 'Cliente',
    CONSTRAINT pk_clientes PRIMARY KEY (email_cliente)
);

-- Tabla articulos
CREATE TABLE articulos (
    codigo_articulo VARCHAR(20) NOT NULL,
    descripcion VARCHAR(150) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    gastos_envio DECIMAL(10,2) NOT NULL,
    tiempo_preparacion_min INT NOT NULL,
    CONSTRAINT pk_articulos PRIMARY KEY (codigo_articulo)
);

-- Tabla pedidos
CREATE TABLE pedidos (
    numero_pedido INT NOT NULL AUTO_INCREMENT,
    email_cliente VARCHAR(100) NOT NULL,
    codigo_articulo VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    CONSTRAINT pk_pedidos PRIMARY KEY (numero_pedido),
    CONSTRAINT fk_pedidos_clientes FOREIGN KEY (email_cliente) REFERENCES clientes(email_cliente),
    CONSTRAINT fk_pedidos_articulos FOREIGN KEY (codigo_articulo) REFERENCES articulos(codigo_articulo)
);

-- Procedimiento Almacenado
DELIMITER //

CREATE PROCEDURE insertar_cliente_proc (
    IN p_email VARCHAR(100),
    IN p_nombre VARCHAR(100),
    IN p_domicilio VARCHAR(150),
    IN p_nif VARCHAR(20),
    IN p_tipo VARCHAR(20)
)
BEGIN
    INSERT INTO clientes (
        email_cliente,
        nombre_cliente,
        domicilio_cliente,
        nif_cliente,
        tipo_cliente
    )
    VALUES (
        p_email,
        p_nombre,
        p_domicilio,
        p_nif,
        p_tipo
    );
END //

DELIMITER ;

-- Insertar Clientes
INSERT INTO clientes (email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente)
VALUES
('joelb@gmail.com', 'Joel Borrás', 'Calle Mallorca, 112', '432210704X', 'ESTANDAR'),
('clarag@gmail.com', 'Clara García', 'Av. Roma, 1', '47078860G', 'ESTANDAR'),
('salmap@gmail.com', 'Salma Pérez', 'Calle Cartagena, 23', '37068054W', 'PREMIUM'),
('paub@gmail.com', 'Pau Bel', 'Pg. de Gràcia, 222', '43032205A', 'PREMIUM');

-- Insertar Artículos
INSERT INTO articulos(codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion_min)
VALUES
('S207','Silla plegable',25.50, 30.00, 60),
('M207','Mesa plegable',35.50, 30.00, 60),
('S111','Silla madera antigua',40.00, 30.00, 60),
('M111','Mesa madera antigua',85.00, 30.00, 60);

-- Insertar Pedidos
INSERT INTO pedidos (email_cliente, codigo_articulo, cantidad, fecha_hora)
VALUES
('joelb@gmail.com', 'S207', 2, NOW()),
('salmap@gmail.com', 'M111', 1, NOW());