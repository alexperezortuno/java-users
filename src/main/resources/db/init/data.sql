-- Inserta usuario con ID desde secuencia
INSERT INTO ac.users (id, uuid, name, email, password, last_login, is_active,
                   created_date, created_by, last_modified_date, last_modified_by)
VALUES (NEXT VALUE FOR ac.AC_AUTHORITY_SEQ,
             '550e8400-e29b-41d4-a716-446655440000', -- UUID estático de ejemplo
             'Admin',
             'admin@admin.com',
             '$2a$10$Em1xf.HPS6hSK0QtgqWsGORxXXaqkS9DwUpCS.1iuB119pofM1Vd2', --superPassword
             CURRENT_TIMESTAMP,
             TRUE,
             CURRENT_TIMESTAMP,
             'admin',
             CURRENT_TIMESTAMP,
             'admin');

-- Inserta teléfono relacionado con el usuario (asumiendo que es el único en la tabla)
INSERT INTO ac.phones (id, phone_number, city_code, country_code, user_id,
                    created_date, created_by, last_modified_date, last_modified_by)
VALUES (NEXT VALUE FOR ac.AC_PHONE_SEQ,
             '1234567',
             '1',
             '56',
             (SELECT id FROM ac.users WHERE email = 'admin@admin.com'),
             CURRENT_TIMESTAMP,
             'admin',
             CURRENT_TIMESTAMP,
             'admin');
