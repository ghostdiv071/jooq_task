CREATE TABLE invoice_item (
    id bigint NOT NULL UNIQUE,
    price bigint check (price > 0),
    amount int check (amount >= 0),
    nomenclature bigint references public.nomenclature (id) NOT NULL,
    invoice_id bigint references public.invoice (id) NOT NULL,
    PRIMARY KEY (id)
);