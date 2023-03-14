# Biblioteca

Este proyecto ha sido realizado durante el Bootcamp FullStack Developer 
para aprender a desarrollar APIs con Spring.

Un CRUD básico que "simula" ser una biblioteca, donde puedas consultar libros, prestamos... 
que un lector pueda hacer préstamos de libros...
si se pasa la fecha máxima de devolución se "castigará" al lector... 
y demás funcionalidades.

Para probar esta aplicación con Postman se puede importar el archivo `./biblioteca.postman_collection.json`

Para la BBDD necesitamos MariaDB y configurar el puerto, usuario y contraseña de la BBDD en ``src/main/resources/application.properties``.
Para crear la BBDD y las tablas, ejecutar el script ``./BibliotecaBBDD.sql``