package daos;

import db.tables.records.InvoiceRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class InvoiceDAOTest {

    private final static String url = "jdbc:postgresql://127.0.0.1:5432/Suppliers";
    public static final String user = "postgres";
    public static final String password = "anna";

    public InvoiceDAO invoiceDAO() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            return new InvoiceDAO(dslContext);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLException();
    }

    @Test
    public void get() throws SQLException {
        InvoiceRecord expected = new InvoiceRecord(2, new Date(Calendar.YEAR+119, 3, 2), 2L);
        assertEquals(expected, invoiceDAO().get(2));
    }

    @Test
    public void getAll() throws SQLException {
        List<InvoiceRecord> expected = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            expected.add(new InvoiceRecord(i, new Date(Calendar.YEAR + 119, i, i), (long)i));
        }

        for (int i = 11; i < 21; i++) {
            expected.add(new InvoiceRecord(i, new Date(Calendar.YEAR + 119, i , i), (long) i-10));
        }
        assertEquals(expected, invoiceDAO().getAll());
    }

    @Test
    public void save() throws SQLException {
        assertTrue(invoiceDAO().save(new InvoiceRecord(20, new Date(2020-11-30), 1L)));
    }

    @Test
    public void update() throws SQLException {
        assertTrue(invoiceDAO().save(new InvoiceRecord(20, new Date(2021-1-1), 1L)));
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(invoiceDAO().delete(new InvoiceRecord(20, new Date(2021-1-1), 1L)));
    }
}