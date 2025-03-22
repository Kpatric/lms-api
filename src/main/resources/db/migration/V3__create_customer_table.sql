-- Create customers table
CREATE TABLE customers (
       id            UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
       customer_number VARCHAR(50) UNIQUE NOT NULL,
       has_active_loan BOOLEAN NOT NULL DEFAULT FALSE,
       date_created  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
       date_modified TIMESTAMP WITHOUT TIME ZONE,
       created_by    VARCHAR(255),
       modified_by   VARCHAR(255)
);