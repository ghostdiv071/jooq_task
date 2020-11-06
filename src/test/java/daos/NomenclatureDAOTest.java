package daos;

import db.tables.records.NomenclatureRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NomenclatureDAOTest {

    private final static String url = "jdbc:postgresql://127.0.0.1:5432/Suppliers";
    public static final String user = "postgres";
    public static final String password = "anna";

    public NomenclatureDAO nomenclatureDAO() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            return new NomenclatureDAO(dslContext);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLException();
    }

    @Test
    public void get() throws SQLException {
        NomenclatureRecord expected = new NomenclatureRecord(2, 20L, "name4");
        assertEquals(expected, nomenclatureDAO().get(2));
    }

    @Test
    public void getAll() throws SQLException {
        List<NomenclatureRecord> expected = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            expected.add(new NomenclatureRecord(i, (long) i*10, "name" + i*2));
        }
        assertEquals(expected, nomenclatureDAO().getAll());
    }

    @Test
    public void save() throws SQLException {
        assertTrue(nomenclatureDAO().save(new NomenclatureRecord(20, 200L, "nametest")));
    }

    @Test
    public void update() throws SQLException {
        assertTrue(nomenclatureDAO().update(new NomenclatureRecord(20, 250L, "name1")));
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(nomenclatureDAO().delete(new NomenclatureRecord(20, 250L, "name1")));
    }
}