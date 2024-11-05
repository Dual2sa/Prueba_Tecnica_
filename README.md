# AseoApp - Sistema de Inventario y Control de Clientes

AseoApp es una API REST desarrollada en Java con Spring Boot que permite a una tienda de artículos de aseo personal gestionar el inventario de productos y el control de sus clientes. Esta aplicación permite registrar clientes, productos, facturas y consultar información detallada de cada uno de estos elementos.

## Tecnologías Utilizadas

- **Java JDK**: 22
- **Spring Boot**: Framework principal para la creación de la API REST.
- **MySQL**: Base de datos para almacenar la información de clientes, productos y facturas.
- **Hibernate JPA**: Manejador de la persistencia de datos.
- **Maven**: Herramienta para la gestión de dependencias.

## Estructura del Proyecto

El proyecto cuenta con las siguientes funcionalidades principales:
1. **Registro de Clientes**: Guardar y consultar información de los clientes.
2. **Registro de Productos**: Guardar y consultar información de los productos.
3. **Gestión de Facturas**: Registro de facturas y sus detalles.
4. **Consultas de Facturas**: Consultar facturas de un cliente y ver el detalle de una factura específica.

## Configuración del Entorno

### Prerrequisitos

- Tener **Java JDK 22** instalado.
- Tener **MySQL** instalado y configurado.
- Tener **Maven** instalado para gestionar el proyecto.

### Configuración de la Base de Datos

1. **Base de Datos**: La base de datos debe llamarse `aseo`.
2. **Ubicación**: La base de datos está localizada en la carpeta `DataBase` del proyecto.
3. **Credenciales de la Base de Datos**:
   - Nombre de usuario: `root`
   - Contraseña: `admin`

Asegúrate de que estas credenciales coincidan con las de tu servidor MySQL o actualiza los valores en el archivo de configuración `application.properties` si es necesario.

### Archivo `application.properties`

En `src/main/resources/application.properties`, asegúrate de tener la configuración siguiente:

```properties
spring.application.name=aseo
spring.datasource.url=jdbc:mysql://localhost:3306/aseo
spring.datasource.username=root
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
