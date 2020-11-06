/*
 * This file is generated by jOOQ.
 */
package db;


import db.tables.FlywaySchemaHistory;
import db.tables.Invoice;
import db.tables.InvoiceItem;
import db.tables.Nomenclature;
import db.tables.Organisation;

import javax.annotation.processing.Generated;


/**
 * Convenience access to all tables in public.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.invoice</code>.
     */
    public static final Invoice INVOICE = Invoice.INVOICE;

    /**
     * The table <code>public.invoice_item</code>.
     */
    public static final InvoiceItem INVOICE_ITEM = InvoiceItem.INVOICE_ITEM;

    /**
     * The table <code>public.nomenclature</code>.
     */
    public static final Nomenclature NOMENCLATURE = Nomenclature.NOMENCLATURE;

    /**
     * The table <code>public.organisation</code>.
     */
    public static final Organisation ORGANISATION = Organisation.ORGANISATION;
}