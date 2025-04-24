-- Crear esquema si se usa uno (opcional en H2)
CREATE SCHEMA IF NOT EXISTS ac;

-- Secuencias
CREATE SEQUENCE IF NOT EXISTS ac.AC_AUTHORITY_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS ac.AC_PHONE_SEQ START WITH 1 INCREMENT BY 1;

-- Tabla users
CREATE TABLE IF NOT EXISTS ac.users (id BIGINT PRIMARY KEY, uuid UUID NOT NULL, name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    last_login TIMESTAMP,
    is_active BOOLEAN,
    created_date TIMESTAMP,
    created_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(255)
    );

-- Tabla phones
CREATE TABLE IF NOT EXISTS ac.phones (id BIGINT PRIMARY KEY, phone_number VARCHAR(20) NOT NULL,
    city_code VARCHAR(10) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    user_id BIGINT NOT NULL,
    created_date TIMESTAMP,
    created_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
