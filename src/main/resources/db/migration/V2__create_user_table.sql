-- Create users table
CREATE TABLE  IF NOT EXISTS users
(
    id            UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    date_created  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    date_modified TIMESTAMP WITHOUT TIME ZONE,
    created_by    VARCHAR(255),
    modified_by   VARCHAR(255),
    deleted       BOOLEAN          NOT NULL DEFAULT FALSE
);

-- Ensure you have the extension for handling UUIDs, if not already
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";
