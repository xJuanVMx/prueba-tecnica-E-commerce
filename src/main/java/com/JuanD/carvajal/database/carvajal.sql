create database carvajal;
use carvajal;

create table categoria (
    id int not null auto_increment,
    nombre varchar(100) not null,
    descripcion varchar(255),
    primary key (id)
);

create table producto (
    id int not null auto_increment,
    nombre varchar(150) not null,
    descripcion varchar(255),
    precio double not null,
    cantidad_stock int not null,
    categoria_id int not null,
    primary key (id),
    foreign key (categoria_id) references categoria(id)
);

create table lista_deseos (
    id int not null auto_increment,
    usuario_id int not null,
    producto_id int not null,
    cantidad int,
    fecha_agregado datetime,
    primary key (id),
    foreign key (producto_id) references producto(id)
);

create table historico_lista_deseos (
    id int not null auto_increment,
    usuario_id int not null,
    producto_id int not null,
    nombre_producto varchar(150),
    accion varchar(50) not null,
    fecha datetime,
    primary key (id)
);

insert into categoria (nombre, descripcion) values
('Papeleria', 'Articulos de oficina y escritura'),
('Tecnologia', 'Dispositivos y accesorios electronicos'),
('Hogar', 'Articulos para el hogar y decoracion'),
('Deportes', 'Ropa y equipos deportivos'),
('Libros', 'Libros, revistas y material educativo'),
('Ropa', 'Prendas de vestir y accesorios');

-- productos papeleria (categoria_id = 1)
insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Cuaderno universitario', 'Cuaderno 100 hojas rayado', 8500, 50, 1),
('Lapicero azul', 'Lapicero tinta azul punta fina', 1500, 120, 1),
('Resma papel carta', 'Resma 500 hojas papel bond', 25000, 30, 1),
('Agenda 2025', 'Agenda ejecutiva tapa dura', 35000, 15, 1),
('Carpeta argollada', 'Carpeta con 3 argollas', 12000, 0, 1),
('Marcadores x12', 'Set 12 marcadores de colores', 18000, 25, 1);

-- productos tecnologia (categoria_id = 2)
insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Calculadora basica', 'Calculadora 12 digitos', 22000, 10, 2),
('USB 32GB', 'Memoria USB 32 gigabytes', 45000, 0, 2),
('Mouse inalambrico', 'Mouse bluetooth 1600 DPI', 55000, 20, 2),
('Teclado mecanico', 'Teclado USB retroiluminado', 120000, 8, 2),
('Audífonos bluetooth', 'Audifonos inalambricos 20h bateria', 95000, 0, 2);

-- productos hogar (categoria_id = 3)
insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Lampara de escritorio', 'Lampara LED regulable', 65000, 12, 3),
('Organizador de cajones', 'Set 6 organizadores plastico', 28000, 35, 3),
('Cojin decorativo', 'Cojin 45x45 varios colores', 32000, 18, 3),
('Marco de fotos x3', 'Set 3 marcos madera natural', 42000, 0, 3);

-- productos deportes (categoria_id = 4)
insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Balon de futbol', 'Balon #5 cuero sintetico', 75000, 15, 4),
('Tenis running', 'Zapatillas deportivas talla 42', 180000, 6, 4),
('Botella termica', 'Botella 750ml acero inoxidable', 48000, 40, 4),
('Guantes boxeo', 'Guantes 12oz cuero', 95000, 0, 4);

-- productos libros (categoria_id = 5)
insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Clean Code', 'Libro programacion Robert C. Martin', 85000, 10, 5),
('El principito', 'Novela clasica Antoine de Saint-Exupery', 22000, 30, 5),
('Ingles sin barreras', 'Libro metodo rapido de ingles', 45000, 0, 5),
('Atlas mundial 2025', 'Atlas geografico actualizado', 68000, 5, 5);

-- productos ropa (categoria_id = 6)
insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Camiseta polo', 'Polo algodon talla M', 55000, 25, 6),
('Chaqueta impermeable', 'Chaqueta lluvia talla L', 120000, 8, 6),
('Gorra deportiva', 'Gorra ajustable logo bordado', 35000, 0, 6),
('Medias x6', 'Pack 6 pares medias algodon', 28000, 50, 6);
