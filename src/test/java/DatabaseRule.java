import dao.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/site_manager", "postgres", "postgres");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM sites *;";
            String deleteStylistsQuery = "DELETE FROM engineers *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }
}