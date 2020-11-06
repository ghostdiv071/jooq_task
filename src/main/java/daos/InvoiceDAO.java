package daos;

import db.tables.Invoice;
import db.tables.records.InvoiceRecord;
import org.jooq.DSLContext;

import java.util.List;

public class InvoiceDAO implements DAO<InvoiceRecord> {

    final DSLContext dsl;

    public InvoiceDAO(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public InvoiceRecord get(int id) {
        return dsl.selectFrom(Invoice.INVOICE)
                .where(Invoice.INVOICE.ID.eq(id))
                .fetchOne();
    }

    @Override
    public List<InvoiceRecord> getAll() {
        return dsl.selectFrom(Invoice.INVOICE).
                fetch();
    }

    @Override
    public boolean save(InvoiceRecord entity) {
        return dsl.insertInto(Invoice.INVOICE)
                .values(entity.getId(),
                        entity.getDate(),
                        entity.getOrganisationId()
                ).execute() == 1;
    }

    @Override
    public boolean update(InvoiceRecord entity) {
        return dsl.update(Invoice.INVOICE)
                .set(dsl.newRecord(Invoice.INVOICE, entity))
                .where(Invoice.INVOICE.ID.eq(entity.getId()))
                .execute() == 1;
    }

    @Override
    public boolean delete(InvoiceRecord entity) {
        return dsl.deleteFrom(Invoice.INVOICE)
                .where(Invoice.INVOICE.ID.eq(entity.getId()))
                .execute() == 1;
    }
}
