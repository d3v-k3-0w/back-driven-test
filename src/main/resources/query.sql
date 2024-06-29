CREATE DATABASE dbdriventests;
USE dbdriventests;

CREATE TABLE usuario (
idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
nombre VARCHAR(255) NOT NULL,
apellidos VARCHAR(255) NOT NULL,
correo VARCHAR(255) NOT NULL,
telefono VARCHAR(255) NOT NULL,
perfil VARCHAR(255) NOT NULL,
enable BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE rol (
idRol BIGINT AUTO_INCREMENT PRIMARY KEY,
nombreRol VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios_roles (
idUsuario BIGINT,
idRol BIGINT,
PRIMARY KEY (idUsuario, idRol),
FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE categoria (
idCategoria BIGINT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE examen (
idExamen BIGINT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
descripcion VARCHAR(255) NOT NULL,
puntosMax VARCHAR(255) NOT NULL,
numPreguntas VARCHAR(255) NOT NULL,
isActivo BOOLEAN NOT NULL DEFAULT FALSE,
idCategoria BIGINT,
FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria)
);

CREATE TABLE pregunta (
idPregunta BIGINT AUTO_INCREMENT PRIMARY KEY,
contenido TEXT NOT NULL,
imagen VARCHAR(255),
opcion1 VARCHAR(255) NOT NULL,
opcion2 VARCHAR(255) NOT NULL,
opcion3 VARCHAR(255) NOT NULL,
opcion4 VARCHAR(255) NOT NULL,
respuesta VARCHAR(255) NOT NULL,
idExamen BIGINT,
FOREIGN KEY (idExamen) REFERENCES examen(idExamen)
);

INSERT INTO rol (nombreRol) VALUES ('ADMIN');
INSERT INTO rol (nombreRol) VALUES ('USER');

INSERT INTO usuario (username, password, nombre, apellidos, correo, telefono, perfil, enable) VALUES 
('admin', '$2a$10$QJ3VtrNtNJwzHV3pR/5ddumUf/9mxxs28sH54ud.NHtukHGmC5fXC', 'John', 'Wick', 'john.wick@example.com', '123456789', 'ADMIN', TRUE), -- a123
('mary', '$2a$10$Vgs/OKfL9fIe14EQYNAtmO98acafGc6d6UfJjuVGYdSh.RSSxFisi', 'Mary', 'Jane', 'mary.jane@example.com', '543216789', 'USER', TRUE); -- m123

INSERT INTO usuarios_roles (idUsuario, idRol) VALUES (1, 1); -- John Wick tiene rol ADMIN
INSERT INTO usuarios_roles (idUsuario, idRol) VALUES (2, 2); -- Mary Jane tiene rol USER

INSERT INTO categoria (titulo, descripcion) VALUES 
('Java', 'Exámenes del lenguaje de programación Java'),
('JavaScript', 'Exámenes del lenguaje de programación JavaScript');

INSERT INTO examen (titulo, descripcion, puntosMax, numPreguntas, isActivo, idCategoria) VALUES 
('Java Básico', 'Evaluar conocimientos básicos de Java', '100', '4', TRUE, 1),
('JavaScript Básico', 'Evaluar conocimientos básicos de JavaScript', '100', '4', TRUE, 2);

INSERT INTO pregunta (contenido, imagen, opcion1, opcion2, opcion3, opcion4, respuesta, idExamen) VALUES 
-- Preguntas para el examen de Java
('¿Qué es un objeto en Java?', NULL, 'una instancia de una clase', 'una clase', 'un método', 'un paquete', 'una instancia de una clase', 1),
('¿Cuál es el modificador de acceso más restrictivo en Java?', NULL, 'public', 'protected', 'private', 'default', 'private', 1),
('¿Qué palabra clave se utiliza para heredar una clase en Java?', NULL, 'extends', 'implements', 'inherits', 'super', 'extends', 1),
('¿Qué es un JRE en Java?', NULL, 'un entorno de ejecución para aplicaciones Java', 
 'un conjunto de herramientas para desarrollar aplicaciones Java', 
 'una máquina virtual para ejecutar bytecode de Java', 
 'una herramienta para compilar código fuente Java', 
 'un entorno de ejecución para aplicaciones Java', 1),

-- Preguntas para el examen de JavaScript
('¿Qué método se utiliza para escribir en la consola en JavaScript?', NULL, 'console.log()', 'document.write()', 'alert()', 'print()', 'console.log()', 2),
('¿Cuál de los siguientes es un tipo de datos primitivo en JavaScript?', NULL, 'Object', 'Function', 'Number', 'Array', 'Number', 2),
('¿Cómo se declara una variable en JavaScript?', NULL, 'var', 'let', 'const', 'Todas las anteriores', 'Todas las anteriores', 2),
('¿Qué método se utiliza para convertir un objeto a una cadena JSON en JavaScript?', NULL, 'JSON.parse()', 'JSON.stringify()',
 'Object.toString()', 'JSON.convert()', 'JSON.stringify()', 2);
