CREATE DATABASE IF NOT EXISTS project_management_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE project_management_db;


CREATE USER IF NOT EXISTS 'pm_user'@'localhost' IDENTIFIED BY 'pm_password';
GRANT ALL PRIVILEGES ON project_management_db.* TO 'pm_user'@'localhost';
FLUSH PRIVILEGES;


CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     full_name VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
    );


INSERT INTO users (full_name, role, email) VALUES
                                               ('Андрій', 'Project Manager', 'andriy@example.com'),
                                               ('Сергій', 'Developer', 'sergiy@example.com'),
                                               ('Марія', 'QA Engineer', 'maria@example.com'),
                                               ('Іван', 'Architect', 'ivan@example.com');
