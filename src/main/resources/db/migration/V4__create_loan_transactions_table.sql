-- Create customers table
CREATE TABLE loan_transactions (
    id            UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    customer_number VARCHAR(50) UNIQUE NOT NULL,
    amount    DECIMAL(19, 2) NOT NULL,
    status   VARCHAR(50) NOT NULL,
    score   INTEGER NOT NULL,
    limit_amount   DECIMAL(19, 2) NOT NULL,
    date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    date_modified TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    created_by VARCHAR(100) NOT NULL,
    modified_by VARCHAR(100),
    FOREIGN KEY (customer_number) REFERENCES customers (customer_number)
);