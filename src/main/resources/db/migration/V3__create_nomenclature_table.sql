CREATE TABLE nomenclature (
    id bigint NOT NULL UNIQUE,
    internal_code bigint NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
)