# Reto prison-game

# Como probar la aplicación:

Desde un cliente como postman envia una peticion post, esta peticion recibe como body un objeto json con una sola propiedad "prison", esta propiedad es un arreglo 
de strings el cual contiene la información del laberinto.

URL: http://3.14.136.83:8080/prisoner

BODY:
{
  "prison": ["||||||S||","|P ||   |","||  | | |","|v| | < |","| |   | |","|   |   |","|||||||||"]
}

![image](https://user-images.githubusercontent.com/3578372/235269247-7f81a2d7-7f88-4189-b6ff-ecec802827d1.png)

Para obtener el estado de los prisioneros, contedo de escapes satisfactorios, insatisfactorios y ratio de satisfactorios puede probar haciendo un llamado con el metodo 
Get.

URL: http://3.14.136.83:8080/stats

![image](https://user-images.githubusercontent.com/3578372/235269431-7d56f008-9dbe-4fdd-9941-374f1ca1f005.png)


Las respuesta recibida si el prisionero encuentra la salida es un Http Status 200, en caso contrario recibe un 401.
Esta informacion se guarda en una base de datos en una tabla prisioner:

![image](https://user-images.githubusercontent.com/3578372/235266923-af5b0b0a-c2e0-439b-87ec-2493e2c372c9.png)

### Requerimientos para construir la aplicacion:
1. Terminal cmd o bash
2. Java 17 Minimo
3. MySQL 

### Pasos para ejecutar la aplicación localmente:
1. Asegurese de tener una base de datos para la aplicación
2. Edite el application.properties sobreescriba los valores

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

con los parametros de conexión a su base de datos en ejecución.
Cabe destacar que la URL debe indicarse de la siguiente manera

jdbc:mysql://localhost:3306/challenge
1. Acceder al proyecto raiz desde la terminal
3. Ejecutar el comando ./gradlew build
   3. El proyecto se construira bajo el nombre prison-game-0.0.1-SNAPSHOT.jar 
3. Ejecutar la aplicacion desde la terminal con el comando

java -jar build/libs/prison-game-0.0.1-SNAPSHOT.jar

