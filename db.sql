create database bowlfull_buddies;

INSERT INTO users (joined_date, full_name, email, password, users_category)
VALUES (
    CURRENT_DATE,
    'Admin',
    'admin@mail.com',
    '$2a$10$DIvDxXMaYIblu9wG0P2Q7.zezs9HXG6G3omtoI18X2IXXyMM0A25e',
    'ADMIN'
);
