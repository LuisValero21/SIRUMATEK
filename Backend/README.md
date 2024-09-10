# SIRUMATEK

Este proyecto es una aplicación web para la gestión de empleados de la empresa SIRUMATEK, desarrollada con Spring Boot para el backend y Angular para el frontend.

## Requisitos

- Java 8
- Maven 3.6+
- MySQL 8.0.39
- Node.js 12+
- Angular CLI 9.1+

## Configuración del Backend

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tuusuario/sirumatek.git
    cd sirumatek
    ```

2. Configura la base de datos en MySQL:

    ```sql
    CREATE DATABASE SIRUMATEK;
    ```

    Importa el archivo `sirumatek.sql` en MySQL:

    ```bash
    mysql -u root -p SIRUMATEK < src/main/resources/sirumatek.sql
    ```

3. Configura las credenciales de la base de datos en el archivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/sirumatek
    spring.datasource.username=root
    spring.datasource.password=basededatos1$
    ```

4. Construye y ejecuta el backend:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Configuración del Frontend

1. Navega a la carpeta `frontend`:

    ```bash
    cd frontend
    ```

2. Instala las dependencias:

    ```bash
    npm install
    ```

3. Ejecuta la aplicación Angular:

    ```bash
    ng serve
    ```

4. Accede a la aplicación en [http://localhost:4200](http://localhost:4200).

## Estructura del Proyecto

- `src/main/java`: Contiene el código fuente del backend.
- `src/main/resources`: Contiene los recursos del backend, incluyendo el archivo `application.properties` y `sirumatek.sql`.
- `src/test/java`: Contiene las pruebas unitarias.
- `frontend`: Contiene el código fuente del frontend desarrollado con Angular.

## Contribución

Si deseas contribuir a este proyecto, por favor, haz un fork del repositorio, crea una nueva rama, realiza tus cambios y envía un pull request.

## Licencia

Este proyecto está bajo la licencia MIT.