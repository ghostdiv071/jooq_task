package daos;

import db.tables.Nomenclature;
import db.tables.records.NomenclatureRecord;
import org.jooq.DSLContext;

import java.util.List;

public class NomenclatureDAO implements DAO<NomenclatureRecord> {

    final DSLContext dsl;

    public NomenclatureDAO(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public NomenclatureRecord get(int id) {
        return dsl.selectFrom(Nomenclature.NOMENCLATURE)
                .where(Nomenclature.NOMENCLATURE.ID.eq(id))
                .fetchOne();
    }

    @Override
    public List<NomenclatureRecord> getAll() {
        return dsl.selectFrom(Nomenclature.NOMENCLATURE).
                fetch();
    }

    @Override
    public boolean save(NomenclatureRecord entity) {
        return dsl.insertInto(Nomenclature.NOMENCLATURE)
                .values(entity.getId(),
                        entity.getInternalCode(),
                        entity.getName()
                ).execute() == 1;
    }

    @Override
    public boolean update(NomenclatureRecord entity) {
        return dsl.update(Nomenclature.NOMENCLATURE)
                .set(dsl.newRecord(Nomenclature.NOMENCLATURE, entity))
                .where(Nomenclature.NOMENCLATURE.ID.eq(entity.getId()))
                .execute() == 1;
    }

    @Override
    public boolean delete(NomenclatureRecord entity) {
        return dsl.deleteFrom(Nomenclature.NOMENCLATURE)
                .where(Nomenclature.NOMENCLATURE.ID.eq(entity.getId()))
                .execute() == 1;
    }
}
