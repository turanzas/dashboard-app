DROP SCHEMA IF EXISTS financial_entity CASCADE;

CREATE SCHEMA financial_entity;

CREATE EXTENSION IF NOT EXISTS uuid_ossp;

DROP TYPE IF EXISTS financial_entity_status;

CREATE TYPE financial_entity_status AS ENUM ('ACTIVE', 'INACTIVE');

DROP TABLE IF EXISTS financial_entity CASCADE;

CREATE TABLE financial_entity
(
    id uuid NOT NULL,
    name VARCHAR(255) NOT NULL,
    status financial_entity_status NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by uuid NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by uuid NOT NULL,
    CONSTRAINT financial_entity_pkey PRIMARY KEY (id)
);