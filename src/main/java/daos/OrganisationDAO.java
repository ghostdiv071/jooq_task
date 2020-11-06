package daos;

import db.tables.Organisation;
import db.tables.records.OrganisationRecord;
import org.jooq.DSLContext;
import org.jooq.Result;

public class OrganisationDAO implements DAO<OrganisationRecord> {

    final DSLContext dsl;

    public OrganisationDAO(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public OrganisationRecord get(int id) {
        return dsl.selectFrom(Organisation.ORGANISATION)
                .where(Organisation.ORGANISATION.ID.eq(id))
                .fetchOne();
    }

    @Override
    public Result<OrganisationRecord> getAll() {
        return dsl.selectFrom(Organisation.ORGANISATION).
                fetch();
    }

    @Override
    public boolean save(OrganisationRecord entity) {
        return dsl.insertInto(Organisation.ORGANISATION)
                .values(entity.getId(),
                        entity.getName(),
                        entity.getTaxpayerId(),
                        entity.getCheckingAccount()
                ).execute() == 1;
    }

    @Override
    public boolean update(OrganisationRecord entity) {
        return dsl.update(Organisation.ORGANISATION)
                .set(dsl.newRecord(Organisation.ORGANISATION, entity))
                .where(Organisation.ORGANISATION.ID.eq(entity.getId()))
                .execute() == 1;
    }

    @Override
    public boolean delete(OrganisationRecord entity) {
        return dsl.deleteFrom(Organisation.ORGANISATION)
                .where(Organisation.ORGANISATION.ID.eq(entity.getId()))
                .execute() == 1;
    }
}
