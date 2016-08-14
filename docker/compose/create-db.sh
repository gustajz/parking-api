#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE ROLE parking LOGIN PASSWORD 'parking';
    CREATE DATABASE parking WITH OWNER = parking;
EOSQL