package daos;

import db.tables.records.InvoiceItemRecord;
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

public class InvoiceItemDAOTest {

    private final static String url = "jdbc:postgresql://127.0.0.1:5432/Suppliers";
    public static final String user = "postgres";
    public static final String password = "anna";

    public InvoiceItemDAO itemDAO() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            return new InvoiceItemDAO(dslContext);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLException();
    }

    @Test
    public void get() throws SQLException {
        InvoiceItemRecord expected = new InvoiceItemRecord(2, 200L, 6, 2L, 2L);
        assertEquals(expected, itemDAO().get(2));
    }

    @Test
    public void getAll() throws SQLException {
        List<InvoiceItemRecord> expected = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            expected.add(new InvoiceItemRecord(i, (long)i*100, i*3, (long) i, (long) i));
        }

        for (int i = 11; i < 21; i++) {
            expected.add(new InvoiceItemRecord(i, (long)i*100, i*3, (long) i-10, (long) i));
        }

        assertEquals(expected, itemDAO().getAll());
    }

    @Test
    public void save() throws SQLException {
        assertTrue(itemDAO().save(new InvoiceItemRecord(25, 20L, 21, 1L, 1L)));
    }

    @Test
    public void update() throws SQLException {
        assertTrue(itemDAO().update(new InvoiceItemRecord(25, 200L, 22, 1L, 1L)));
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(itemDAO().delete(new InvoiceItemRecord(25, 200L, 22, 1L, 1L)));
    }
}