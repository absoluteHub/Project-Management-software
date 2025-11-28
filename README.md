Project Management Software

Веб-система для керування програмними проєктами. Доступні функції:

    створення та редагування проєктів;

    управління задачами;

    канбан-дошка;

    спринти (ітерації);

    вимоги (functional / non-functional);

    версії (Composite);

    команди учасників проєкту;

    валідація задач (Chain of Responsibility)

 Технології

    Java 21

    Spring Boot

    Spring MVC

    Spring Data JPA / Hibernate

    MySQL 8

    Thymeleaf

    Lombok

    Maven

Попередні вимоги

    Java 21+

    Maven 3+

    MySQL 8+


Запуск проєкту

  1. Клонування репозиторію

    git clone https://github.com/absoluteHub/Project-Management-software.git
    cd REPO
 
  2. Налаштування бази даних

Запустіть MySQL сервер.

Виконайте SQL-скрипт:

    mysql -u root -p < db/create-database.sql

  3. Налаштування конфігурації

У проєкті є файл-шаблон:

    src/main/resources/application-example.properties

Скопіюйте його:

    cp src/main/resources/application-example.properties \ src/main/resources/application.properties
  
  
  4. Збірка та запуск

    mvn clean package

Запуск застосунку:

    mvn spring-boot:run

Після запуску система буде доступна за адресою:

    http://localhost:8080
