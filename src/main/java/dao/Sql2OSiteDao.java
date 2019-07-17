package dao;

import models.Site;
import org.sql2o.*;
import java.util.List;

public class Sql2OSiteDao implements SiteDao { //implementing our interface

    private final Sql2o sql2o;

    public Sql2OSiteDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Site site) {
        String sql = "INSERT INTO sites (site_name, site_no, engineerid, engineer_name) VALUES (:siteName, :siteNumber, :engineerid, :engineerName)";
        try(Connection con = DB.sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(site)
                    .executeUpdate()
                    .getKey();
            site.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println();
        }
    }

    @Override
    public List<Site> getAll() {
        try(Connection con =DB.sql2o.open()){
            return con.createQuery("SELECT name FROM sites")
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
    public void update(String newSite_Name, String newSite_Number ,int newEngineerId){
        String sql = "UPDATE sites SET (site_name, site_number, engineerid) = (:site_name, :site_number, :engineerid) WHERE id=:id"; //raw sql
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("site_name", newSite_Name)
                    .addParameter("site_number", newSite_Number)
                    .addParameter("engineerid", newEngineerId)
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