import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MainTest {

    private final static String url = "jdbc:postgresql://127.0.0.1:5432/Suppliers";
    public static final String user = "postgres";
    public static final String password = "anna";

    public DSLContext dslContext() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            return DSL.using(connection, SQLDialect.POSTGRES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }

    @Test
    public void firstQuery() throws SQLException {
        List<String> expected = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            expected.add("name"+i);
        }

        assertEquals(expected, Main.firstQuery(dslContext()).getValues("name"));
    }

    @Test
    public void secondQuery() throws SQLException {
        Map<Long, String> expected = new HashMap<>();
        for (int i = 4; i < 11; i++) {
            expected.put((long)i, "name"+i);
        }
        long limit = 1700;

        assertEquals(expected, Main.secondQuery(dslContext(), limit).intoMap("id", "name"));
    }

    @Test
    public void thirdQuery() throws SQLException {
        Date lower = new Date(2020-5-5);
        Date upper = new Date(2020-9-9);
        List<Long> expected = new ArrayList<>();
        expected.add(7500L);
        expected.add(10800L);
        expected.add(14700L);
        expected.add(19200L);

        assertEquals(expected, Main.thirdQuery(dslContext(), lower, upper).getValues("total"));
    }

    @Test
    public void fourthQuery() throws SQLException {
        Date lower = new Date(2020-5-5);
        Date upper = new Date(2020-9-9);
        BigDecimal expected = new BigDecimal(13050);

        assertEquals(expected, Main.fourthQuery(dslContext(), lower, upper).value1());
    }

    @Test
    public void fifthQuery() throws SQLException {
        Date lower = new Date(2020-5-5);
        Date upper = new Date(2020-9-9);
        Map<String, String> expected = new HashMap<>();
        for (int i = 5; i < 9; i++) {
            expected.put("name"+i, "name"+(i*2));
        }
        assertEquals(expected, Main.fifthQuery(dslContext(), lower, upper).intoMap("organisation", "name"));
    }
}