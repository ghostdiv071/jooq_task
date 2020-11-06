package daos;

import db.tables.InvoiceItem;
import db.tables.records.InvoiceItemRecord;
import org.jooq.DSLContext;

import java.util.List;

public class InvoiceItemDAO implements DAO<InvoiceItemRecord>{

    final DSLContext dsl;

    public InvoiceItemDAO(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public InvoiceItemRecord get(int id) {
        return dsl.selectFrom(InvoiceItem.INVOICE_ITEM)
                .where(InvoiceItem.INVOICE_ITEM.ID.eq(id))
                .fetchOne();
    }

    @Override
    public List<InvoiceItemRecord> getAll() {
        return dsl.selectFrom(InvoiceItem.INVOICE_ITEM).
                fetch();
    }

    @Override
    public boolean save(InvoiceItemRecord entity) {
        return dsl.insertInto(InvoiceItem.INVOICE_ITEM)
                .values(entity.getId(),
                        entity.getPrice(),
                        entity.getAmount(),
                        entity.getNomenclature(),
                        entity.getInvoiceId()
                ).execute() == 1;
    }

    @Override
    public boolean update(InvoiceItemRecord entity) {
        return dsl.update(InvoiceItem.INVOICE_ITEM)
                .set(dsl.newRecord(InvoiceItem.INVOICE_ITEM, entity))
                .where(InvoiceItem.INVOICE_ITEM.ID.eq(entity.getId()))
                .execute() == 1;
    }

    @Override
    public boolean delete(InvoiceItemRecord entity) {
        return dsl.deleteFrom(InvoiceItem.INVOICE_ITEM)
                .where(InvoiceItem.INVOICE_ITEM.ID.eq(entity.getId()))
                .execute() == 1;
    }
}
