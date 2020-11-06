CREATE TABLE invoice (
    id bigint NOT NULL UNIQUE,
    date date NOT NULL,
    organisation_id bigint references public.organisation (id) NOT NULL,
    PRIMARY KEY (id)
);