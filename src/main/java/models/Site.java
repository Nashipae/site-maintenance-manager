package models;

import dao.DB;
import org.sql2o.Connection;

import java.time.LocalDateTime;
import java.util.List;


public class Site {
    private int id;
    private int siteId;
    private String name;
    private int engineerId;
    private String engineerName;
    private LocalDateTime createdAt;
    private boolean decommissioned;

    @Override
    public boolean equals(Object otherSite){
        if(!(otherSite instanceof Site)){
            return false;
        }else{
            Site newSite = (Site) otherSite;
            return this.getName().equals(newSite.getName()) &&
                    this.getId() == newSite.getId() &&
                    this.getEngineerId() == newSite.getEngineerId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sites (name, engineerid, engineer_name) VALUES (:name, :EngineerId,:engineerName)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("engineerId", this.engineerId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String name){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE sites SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM sites WHERE id =:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public Site(String name, int engineerId) {
        this.name = name;
        decommissioned = false;
        createdAt = LocalDateTime.now();
        this.engineerId = engineerId;
    }

    public int getEngineerId(){
        return engineerId;
    }

    public int getsiteId(){return siteId;}

    public String getEngineerName(){return engineerName;}

    public String getName() {
        return name;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setsiteId(int siteId) {
        this.siteId = siteId;
    }

    public static List<Site> all() {
        String sql = "SELECT id, name, engineerId FROM sites";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Site.class);
        }
    }

    public int getId() {
        return id;
    }

    public static Site find(int siteId) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sites where id=:id";
            Site site = con.createQuery(sql).addParameter("id", siteId).executeAndFetchFirst(Site.class);
            return site;
        }
    }
}

