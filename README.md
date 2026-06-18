# Carvajal 

Aplicación backend desarrollada con Java + Spring Boot que permite gestionar una lista de deseos de productos para el modelo E-commerce de Carvajal.

---

## Requisitos previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

| Herramienta | Versión recomendada |
|-------------|-------------------|
| Java JDK | 21 |
| Maven | 3.9+ |
| MySQL | 8.0+ |
| Postman | Cualquier versión |

---

##  Configuración de la base de datos

### 1. Crear la base de datos

Abre MySQL Workbench o tu cliente de preferencia y ejecuta el siguiente script:

```sql
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
```

### 2. Insertar datos de ejemplo

```sql
insert into categoria (nombre, descripcion) values
('Papeleria', 'Articulos de oficina y escritura'),
('Tecnologia', 'Dispositivos y accesorios electronicos'),
('Hogar', 'Articulos para el hogar y decoracion'),
('Deportes', 'Ropa y equipos deportivos'),
('Libros', 'Libros, revistas y material educativo'),
('Ropa', 'Prendas de vestir y accesorios');

insert into producto (nombre, descripcion, precio, cantidad_stock, categoria_id) values
('Cuaderno universitario', 'Cuaderno 100 hojas rayado', 8500, 50, 1),
('Lapicero azul', 'Lapicero tinta azul punta fina', 1500, 120, 1),
('Resma papel carta', 'Resma 500 hojas papel bond', 25000, 30, 1),
('Agenda 2025', 'Agenda ejecutiva tapa dura', 35000, 15, 1),
('Carpeta argollada', 'Carpeta con 3 argollas', 12000, 0, 1),
('Marcadores x12', 'Set 12 marcadores de colores', 18000, 25, 1),
('Calculadora basica', 'Calculadora 12 digitos', 22000, 10, 2),
('USB 32GB', 'Memoria USB 32 gigabytes', 45000, 0, 2),
('Mouse inalambrico', 'Mouse bluetooth 1600 DPI', 55000, 20, 2),
('Teclado mecanico', 'Teclado USB retroiluminado', 120000, 8, 2),
('Audifonos bluetooth', 'Audifonos inalambricos 20h bateria', 95000, 0, 2),
('Lampara de escritorio', 'Lampara LED regulable', 65000, 12, 3),
('Organizador de cajones', 'Set 6 organizadores plastico', 28000, 35, 3),
('Cojin decorativo', 'Cojin 45x45 varios colores', 32000, 18, 3),
('Marco de fotos x3', 'Set 3 marcos madera natural', 42000, 0, 3),
('Balon de futbol', 'Balon #5 cuero sintetico', 75000, 15, 4),
('Tenis running', 'Zapatillas deportivas talla 42', 180000, 6, 4),
('Botella termica', 'Botella 750ml acero inoxidable', 48000, 40, 4),
('Guantes boxeo', 'Guantes 12oz cuero', 95000, 0, 4),
('Clean Code', 'Libro programacion Robert C. Martin', 85000, 10, 5),
('El principito', 'Novela clasica Antoine de Saint-Exupery', 22000, 30, 5),
('Ingles sin barreras', 'Libro metodo rapido de ingles', 45000, 0, 5),
('Atlas mundial 2025', 'Atlas geografico actualizado', 68000, 5, 5),
('Camiseta polo', 'Polo algodon talla M', 55000, 25, 6),
('Chaqueta impermeable', 'Chaqueta lluvia talla L', 120000, 8, 6),
('Gorra deportiva', 'Gorra ajustable logo bordado', 35000, 0, 6),
('Medias x6', 'Pack 6 pares medias algodon', 28000, 50, 6);
```

---

##  Configuración del proyecto

### Credenciales de base de datos

Abre el archivo `src/main/resources/application.yaml` y ajusta las credenciales según tu entorno:

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/carvajal
    username: root
    password: 
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

server:
  port: 6065

```

Si tu MySQL tiene un usuario o contraseña diferente, cámbialo aquí antes de ejecutar.

---

##  Manual de despliegue

### Opción 1 — Desde Visual Studio

1. Abre Visual Studio
2. Selecciona **File → Open** y elige la carpeta del proyecto
3. Espera que Maven descargue las dependencias automáticamente
4. Abre el archivo `CarvajalApplication.java`
5. Haz clic en el botón ▶️ **Run**
6. El servidor inicia en `http://localhost:6065`

### Opción 2 — Desde la terminal

```

# 1. Entrar a la carpeta del proyecto
cd carvajal

# 2. Compilar el proyecto
mvn clean install

# 3. Ejecutar el proyecto
mvn spring-boot:run
```

---

##  Endpoints disponibles

Base URL: `http://localhost:6065`

### Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Ver catálogo completo con stock |

### Lista de deseos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/lista-deseos` | Agregar producto a lista de deseos |
| PUT | `/api/lista-deseos/{id}` | Actualizar cantidad de un deseo |
| DELETE | `/api/lista-deseos/{id}` | Eliminar producto de lista de deseos |


##  Estructura del proyecto

```
src/main/java/com/JuanD/carvajal/
├── CarvajalApplication.java
├── controller/
│   ├── ProductoController.java
│   └── ListaDeseosController.java
├── dto/
│   ├── HttpGlobalResponse.java
│   ├── CategoriaDTO.java
│   ├── ProductoResponseDTO.java
│   ├── ListaDeseosRequestDTO.java
│   ├── ListaDeseosResponseDTO.java
│   └── HistoricoResponseDTO.java
├── entity/
│   ├── Categoria.java
│   ├── Producto.java
│   ├── ListaDeseos.java
│   └── Historico.java
├── repository/
│   ├── CategoriaRepository.java
│   ├── ProductoRepository.java
│   ├── ListaDeseosRepository.java
│   └── HistoricoRepository.java
└── service/
    ├── ProductoService.java
    └── ListaDeseosService.java
```

---

##  Dependencias principales

| Dependencia | Uso |
|-------------|-----|
| Spring Web | Creación de endpoints REST |
| Spring Data JPA | Conexión y manejo de base de datos |
| MySQL Driver | Conector con MySQL |
| Lombok | Reducción de código con @Data, @AllArgsConstructor |

---


## Autor

Juan David Vera Moreno
Prueba Técnica  
Carvajal E-commerce
ADSO 3231651
