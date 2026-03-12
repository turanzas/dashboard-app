DROP SCHEMA IF EXISTS financial-entity CASCADE;

CREATE SCHEMA financial-entity;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS financial-entity-status;

CREATE TYPE financial-entity-status AS ENUM ('ACTIVE', 'INACTIVE');

DROP TABLE IF EXISTS financial-entity CASCADE;

CREATE TABLE financial-entity
(
    id uuid NOT NULL,
    name VARCHAR(255) NOT NULL,
    status financial-entity-status NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by uuid NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by uuid NOT NULL,
    CONSTRAINT financial_entity_pkey PRIMARY KEY (id)
);