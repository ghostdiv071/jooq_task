/*
 * This file is generated by jOOQ.
 */
package db;


import db.tables.FlywaySchemaHistory;
import db.tables.Invoice;
import db.tables.InvoiceItem;
import db.tables.Nomenclature;
import db.tables.Organisation;
import db.tables.records.FlywaySchemaHistoryRecord;
import db.tables.records.InvoiceItemRecord;
import db.tables.records.InvoiceRecord;
import db.tables.records.NomenclatureRecord;
import db.tables.records.OrganisationRecord;

import javax.annotation.processing.Generated;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * public.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<InvoiceRecord> INVOICE_PKEY = Internal.createUniqueKey(Invoice.INVOICE, DSL.name("invoice_pkey"), new TableField[] { Invoice.INVOICE.ID }, true);
    public static final UniqueKey<InvoiceItemRecord> INVOICE_ITEM_PKEY = Internal.createUniqueKey(InvoiceItem.INVOICE_ITEM, DSL.name("invoice_item_pkey"), new TableField[] { InvoiceItem.INVOICE_ITEM.ID }, true);
    public static final UniqueKey<NomenclatureRecord> NOMENCLATURE_INTERNAL_CODE_KEY = Internal.createUniqueKey(Nomenclature.NOMENCLATURE, DSL.name("nomenclature_internal_code_key"), new TableField[] { Nomenclature.NOMENCLATURE.INTERNAL_CODE }, true);
    public static final UniqueKey<NomenclatureRecord> NOMENCLATURE_PKEY = Internal.createUniqueKey(Nomenclature.NOMENCLATURE, DSL.name("nomenclature_pkey"), new TableField[] { Nomenclature.NOMENCLATURE.ID }, true);
    public static final UniqueKey<OrganisationRecord> ORGANISATION_CHECKING_ACCOUNT_KEY = Internal.createUniqueKey(Organisation.ORGANISATION, DSL.name("organisation_checking_account_key"), new TableField[] { Organisation.ORGANISATION.CHECKING_ACCOUNT }, true);
    public static final UniqueKey<OrganisationRecord> ORGANISATION_NAME_KEY = Internal.createUniqueKey(Organisation.ORGANISATION, DSL.name("organisation_name_key"), new TableField[] { Organisation.ORGANISATION.NAME }, true);
    public static final UniqueKey<OrganisationRecord> ORGANISATION_PKEY = Internal.createUniqueKey(Organisation.ORGANISATION, DSL.name("organisation_pkey"), new TableField[] { Organisation.ORGANISATION.ID }, true);
    public static final UniqueKey<OrganisationRecord> ORGANISATION_TAXPAYER_ID_KEY = Internal.createUniqueKey(Organisation.ORGANISATION, DSL.name("organisation_taxpayer_id_key"), new TableField[] { Organisation.ORGANISATION.TAXPAYER_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<InvoiceRecord, OrganisationRecord> INVOICE__INVOICE_ORGANISATION_ID_FKEY = Internal.createForeignKey(Invoice.INVOICE, DSL.name("invoice_organisation_id_fkey"), new TableField[] { Invoice.INVOICE.ORGANISATION_ID }, Keys.ORGANISATION_PKEY, new TableField[] { Organisation.ORGANISATION.ID }, true);
    public static final ForeignKey<InvoiceItemRecord, InvoiceRecord> INVOICE_ITEM__INVOICE_ITEM_INVOICE_ID_FKEY = Internal.createForeignKey(InvoiceItem.INVOICE_ITEM, DSL.name("invoice_item_invoice_id_fkey"), new TableField[] { InvoiceItem.INVOICE_ITEM.INVOICE_ID }, Keys.INVOICE_PKEY, new TableField[] { Invoice.INVOICE.ID }, true);
    public static final ForeignKey<InvoiceItemRecord, NomenclatureRecord> INVOICE_ITEM__INVOICE_ITEM_NOMENCLATURE_FKEY = Internal.createForeignKey(InvoiceItem.INVOICE_ITEM, DSL.name("invoice_item_nomenclature_fkey"), new TableField[] { InvoiceItem.INVOICE_ITEM.NOMENCLATURE }, Keys.NOMENCLATURE_PKEY, new TableField[] { Nomenclature.NOMENCLATURE.ID }, true);
}
