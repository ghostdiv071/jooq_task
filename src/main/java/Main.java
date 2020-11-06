import daos.InvoiceDAO;
import daos.InvoiceItemDAO;
import daos.NomenclatureDAO;
import daos.OrganisationDAO;
import db.tables.Invoice;
import db.tables.Nomenclature;
import db.tables.Organisation;
import db.tables.records.InvoiceItemRecord;
import db.tables.records.InvoiceRecord;
import db.tables.records.NomenclatureRecord;
import db.tables.records.OrganisationRecord;
import org.flywaydb.core.Flyway;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Calendar;

import static db.tables.InvoiceItem.INVOICE_ITEM;

public class Main {

    private final static String url = "jdbc:postgresql://127.0.0.1:5432/Suppliers";
    public static final String user = "postgres";
    public static final String password = "anna";

    public static void main(String[] args) {
        final Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();
        System.out.println("Migration completed");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection Ok.");

            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);

            OrganisationDAO organisationDAO = new OrganisationDAO(dslContext);
            InvoiceDAO invoiceDAO = new InvoiceDAO(dslContext);
            NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(dslContext);
            InvoiceItemDAO invoiceItemDAO = new InvoiceItemDAO(dslContext);

            createOrganisationData(organisationDAO);
            createInvoiceData(organisationDAO, invoiceDAO);
            createNomenclatureData(nomenclatureDAO);
            createInvoiceItemData(invoiceDAO, invoiceItemDAO, nomenclatureDAO);
            
            System.out.println(firstQuery(dslContext));
            System.out.println();
            System.out.println(secondQuery(dslContext, 1700));
            System.out.println();
            System.out.println(thirdQuery(dslContext, new Date(Calendar.YEAR+119, 4, 5), new Date(Calendar.YEAR+119, 8, 9)));
            System.out.println();
            System.out.println(fourthQuery(dslContext, new Date(Calendar.YEAR+119, 4, 5), new Date(Calendar.YEAR+119, 8, 9)));
            System.out.println();
            System.out.println(fifthQuery(dslContext, new Date(Calendar.YEAR+119, 4, 5), new Date(Calendar.YEAR+119, 8, 9)));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Result<Record1<String>> firstQuery(DSLContext dsl) {
        return dsl.select(Organisation.ORGANISATION.NAME)
                .from(INVOICE_ITEM)
                .join(Invoice.INVOICE)
                .on(INVOICE_ITEM.INVOICE_ID.eq(Invoice.INVOICE.ID.cast(Long.class)))
                .join(Organisation.ORGANISATION)
                .on(Invoice.INVOICE.ORGANISATION_ID.eq(Organisation.ORGANISATION.ID.cast(Long.class)))
                .orderBy(INVOICE_ITEM.AMOUNT)
                .limit(10)
                .fetch();
    }

    public static Result<Record2<Integer, String>> secondQuery(DSLContext dsl, long limit) {
        return dsl.select(Organisation.ORGANISATION.ID, Organisation.ORGANISATION.NAME)
                .from(INVOICE_ITEM)
                .join(Invoice.INVOICE)
                .on(INVOICE_ITEM.INVOICE_ID.eq(Invoice.INVOICE.ID.cast(Long.class)))
                .join(Organisation.ORGANISATION)
                .on(Invoice.INVOICE.ORGANISATION_ID.eq(Organisation.ORGANISATION.ID.cast(Long.class)))
                .groupBy(Organisation.ORGANISATION.ID)
                .having(DSL.sum(INVOICE_ITEM.PRICE).greaterThan(BigDecimal.valueOf(limit)))
                .orderBy(Organisation.ORGANISATION.ID)
                .fetch();
    }

    public static Result<Record1<Integer>> thirdQuery(DSLContext dsl, Date lower, Date upper) {
        return dsl.select(INVOICE_ITEM.AMOUNT.multiply(INVOICE_ITEM.PRICE).as("total"))
                .from(Invoice.INVOICE)
                .join(INVOICE_ITEM)
                .on(Invoice.INVOICE.ID.eq(INVOICE_ITEM.INVOICE_ID.cast(Integer.class)))
                .where(Invoice.INVOICE.DATE.greaterThan(lower))
                .and(Invoice.INVOICE.DATE.lessThan(upper))
                .fetch();
    }

    public static Record1<BigDecimal> fourthQuery(DSLContext dsl, Date lower, Date upper) {
        return dsl.select(DSL.avg(INVOICE_ITEM.PRICE.multiply(INVOICE_ITEM.AMOUNT)))
                .from(Invoice.INVOICE)
                .join(INVOICE_ITEM)
                .on(Invoice.INVOICE.ID.eq(INVOICE_ITEM.INVOICE_ID.cast(Integer.class)))
                .where(Invoice.INVOICE.DATE.greaterThan(lower))
                .and(Invoice.INVOICE.DATE.lessThan(upper))
                .fetchOne();
    }

    public static Result<Record2<String, String>> fifthQuery(DSLContext dsl, Date lower, Date upper) {
        return dsl.select(Organisation.ORGANISATION.NAME.as("organisation"),
                Nomenclature.NOMENCLATURE.NAME.as("name"))
                .from(Organisation.ORGANISATION)
                .join(Invoice.INVOICE)
                .on(Organisation.ORGANISATION.ID.eq(Invoice.INVOICE.ORGANISATION_ID.cast(Integer.class)))
                .join(INVOICE_ITEM)
                .on(Invoice.INVOICE.ID.eq(INVOICE_ITEM.INVOICE_ID.cast(Integer.class)))
                .join(Nomenclature.NOMENCLATURE)
                .on(INVOICE_ITEM.NOMENCLATURE.eq(Nomenclature.NOMENCLATURE.ID.cast(Long.class)))
                .where(Invoice.INVOICE.DATE.greaterThan(lower))
                .and(Invoice.INVOICE.DATE.lessThan(upper))
                .groupBy(Nomenclature.NOMENCLATURE.NAME, Organisation.ORGANISATION.NAME, Invoice.INVOICE.DATE)
                .orderBy(Organisation.ORGANISATION.NAME)
                .fetch();
    }

    public static void createInvoiceItemData(InvoiceDAO invoiceDAO, InvoiceItemDAO itemDAO, NomenclatureDAO nomenclatureDAO) {
        for (int i = 1; i < 11; i++) {
            itemDAO.save(new InvoiceItemRecord(i, (long)i*100, i*3, (long) nomenclatureDAO.get(i).getId(), (long) invoiceDAO.get(i).getId()));
        }

        for (int i = 11; i < 21; i++) {
            itemDAO.save(new InvoiceItemRecord(i, (long)i*100, i*3, (long) nomenclatureDAO.get(i-10).getId(), (long) invoiceDAO.get(i).getId()));
        }
    }

    public static void createNomenclatureData(NomenclatureDAO nomenclatureDAO) {
        for (int i = 1; i < 11; i++) {
            nomenclatureDAO.save(new NomenclatureRecord(i, (long) i*10, "name" + i*2));
        }
    }

    public static void createInvoiceData(OrganisationDAO organisationDAO, InvoiceDAO invoiceDAO) {
        for (int i = 1; i < 11; i++) {
            invoiceDAO.save(new InvoiceRecord(i, new Date(Calendar.YEAR + 119, i, i), (long) organisationDAO.get(i).getId()));
        }

        for (int i = 11; i < 21; i++) {
            invoiceDAO.save(new InvoiceRecord(i, new Date(Calendar.YEAR + 119, i , i), (long) organisationDAO.get(i-10).getId()));
        }
    }

    public static void createOrganisationData(OrganisationDAO organisationDAO) {
        for (int i = 1; i < 12; i++) {
            organisationDAO.save(new OrganisationRecord(i, "name" + i, (long) i, String.valueOf(i)));
        }
    }
}
