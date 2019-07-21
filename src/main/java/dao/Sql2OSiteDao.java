package dao;

import models.Site;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2OSiteDao implements SiteDao {

    private final Sql2o sql2o;
    public Sql2OSiteDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Site site) {
        String sql = "INSERT INTO sites (siteName, siteId, engineerName)" + "VALUES (:siteName, :siteId, :engineerName);";
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(site)
                    .executeUpdate()
                    .getKey();
            site.setId(id); //update object to set id now from database
            System.out.println(sql);
        } catch (Sql2oException ex) {
            System.out.println();
        }
    }

    @Override
    public List<Site> getAll() {
        try(Connection con =DB.sql2o.open()){
            return con.createQuery("SELECT siteName FROM sites")
                    .executeAndFetch(Site.class);
        }
    }


    @Override
    public Site findById(int id) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM sites WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Site.class);
        }
    }

    @Override
    public void update(int id, String newSiteName, int newSiteId, String newEngineerName, int engineerId) {
        String sql = "UPDATE sites SET (siteName, siteId, engineerName, engineerId) = (:siteName, :siteId, :engineerName, :engineerId) WHERE id=:id"; //raw sql
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("siteName", newSiteName)
                    .addParameter("siteId", newSiteId)
                    .addParameter("engineerName", newEngineerName)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println();
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from sites WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println();
        }
    }

    @Override
    public void clearAllSites() {
        String sql = "DELETE from sites";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println();
        }
    }
}