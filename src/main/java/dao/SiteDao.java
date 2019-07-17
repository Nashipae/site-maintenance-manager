package dao;

import models.Site;
import java.util.List;

public interface SiteDao {

    // LIST
    List<Site> getAll();

    // CREATE
    void add(Site site);

    // READ
    Site findById(int id);

    // UPDATE
    void update(String site_name, String site_number,int engineerid);

    // DELETE
    void deleteById(int id);

    void clearAllSites();
}