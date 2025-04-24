-- Crear esquema si se usa uno (opcional en H2)
CREATE SCHEMA IF NOT EXISTS ac;

-- Secuencias
CREATE SEQUENCE IF NOT EXISTS ac.AC_AUTHORITY_SEQ INCREMENT 1 MINVALUE 1 NO MAXVALUE START WITH 1000;
CREATE SEQUENCE IF NOT EXISTS ac.AC_PHONE_SEQ INCREMENT 1 MINVALUE 1 NO MAXVALUE START WITH 1000;

-- Tabla users
CREATE TABLE IF NOT EXISTS ac.users (
    id BIGINT PRIMARY KEY DEFAULT nextval('ac.AC_AUTHORITY_SEQ'),
    uuid UUID NOT NULL, name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    last_login TIMESTAMP,
    is_active BOOLEAN,
    created_date TIMESTAMP,
    created_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(255)
    )
;

-- Comments
COMMENT ON TABLE ac.users IS 'Tabla de usuarios';
COMMENT ON COLUMN ac.users.uuid IS 'UUID del usuario';
COMMENT ON COLUMN ac.users.name IS 'Nombre del usuario';
COMMENT ON COLUMN ac.users.email IS 'Email del usuario';
COMMENT ON COLUMN ac.users.password IS 'Contraseña del usuario';
COMMENT ON COLUMN ac.users.last_login IS 'Último inicio de sesión del usuario';
COMMENT ON COLUMN ac.users.is_active IS 'Estado de actividad del usuario';
COMMENT ON COLUMN ac.users.created_date IS 'Fecha de creación del usuario';
COMMENT ON COLUMN ac.users.created_by IS 'Usuario que creó el registro';
COMMENT ON COLUMN ac.users.last_modified_date IS 'Fecha de última modificación del usuario';
COMMENT ON COLUMN ac.users.last_modified_by IS 'Usuario que realizó la última modificación';

-- Tabla phones
CREATE TABLE IF NOT EXISTS ac.phones (
    id BIGINT PRIMARY KEY DEFAULT nextval('ac.AC_PHONE_SEQ'),
    phone_number VARCHAR(20) NOT NULL,
    city_code VARCHAR(10) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    user_id BIGINT NOT NULL,
    created_date TIMESTAMP,
    created_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    CONSTRAINT idx_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    )
;

CREATE INDEX IF NOT EXISTS idx_user_id_phone_number ON ac.phones (user_id, phone_number);

-- Comments
COMMENT ON TABLE ac.phones IS 'Tabla de teléfonos';
COMMENT ON COLUMN ac.phones.phone_number IS 'Número de teléfono';
COMMENT ON COLUMN ac.phones.city_code IS 'Código de ciudad del teléfono';
COMMENT ON COLUMN ac.phones.country_code IS 'Código de país del teléfono';
COMMENT ON COLUMN ac.phones.user_id IS 'ID del usuario asociado al teléfono';

