DROP SCHEMA IF EXISTS account CASCADE;

CREATE SCHEMA account;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS account_status;

CREATE TYPE account_status AS ENUM ('ACTIVE', 'INACTIVE', 'CLOSED');

DROP TYPE IF EXISTS financial_entity_status;

CREATE TYPE financial_entity_status AS ENUM ('ACTIVE', 'INACTIVE');

DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE account
(
    id uuid NOT NULL,
    financial_entity_id uuid NOT NULL,
    user_id uuid NOT NULL,
    balance NUMERIC(20, 2) NOT NULL,
    status account_status NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by uuid NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by uuid NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS financial_entity CASCADE;

CREATE TABLE financial_entity
(
    id uuid NOT NULL,
    status financial_entity_status NOT NULL,
    CONSTRAINT financial_entity_pkey PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT fk_financial_entity
    FOREIGN KEY (financial_entity_id)
    REFERENCES financial_entity (id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;