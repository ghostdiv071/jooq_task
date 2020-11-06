package daos;

import db.tables.records.OrganisationRecord;
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

public class OrganisationDAOTest {

    private final static String url = "jdbc:postgresql://127.0.0.1:5432/Suppliers";
    public static final String user = "postgres";
    public static final String password = "anna";

    public OrganisationDAO organisationDAO() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            return new OrganisationDAO(dslContext);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLException();
    }

    @Test
    public void get() throws SQLException {
        OrganisationRecord expected = new OrganisationRecord(2, "name2", 2L, "2");
        assertEquals(expected, organisationDAO().get(2));
    }

    @Test
    public void getAll() throws SQLException {
        List<OrganisationRecord> expected = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            expected.add(new OrganisationRecord(i, "name" + i, (long) i, String.valueOf(i)));
        }

        assertEquals(expected, organisationDAO().getAll());
    }

    @Test
    public void save() throws SQLException {
        assertTrue(organisationDAO().save(new OrganisationRecord(20, "testname", 20L, "12345")));
    }

    @Test
    public void update() throws SQLException {
        assertTrue(organisationDAO().update(new OrganisationRecord(20, "test", 200L, "123")));
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(organisationDAO().delete(new OrganisationRecord(20, "test", 200L, "123")));
    }
}