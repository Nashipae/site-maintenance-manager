package models;

import java.time.LocalDateTime;
import java.util.Objects;


public class Site {
    private int id;
    private int siteId;
    private String siteName;
    private int engineerId;
    private String engineerName;
    private LocalDateTime createdAt;
    private boolean decommissioned;

    public Site(){}

    public Site(int id, int siteId, String siteName, int engineerId, String engineerName, LocalDateTime createdAt, boolean decommissioned) {
        this.id = id;
        this.siteId = siteId;
        this.siteName = siteName;
        this.engineerId = engineerId;
        this.engineerName = engineerName;
        this.createdAt = createdAt;
        this.decommissioned = decommissioned;
    }

    public Site(int siteId, String siteName, String engineerName) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.engineerName = engineerName;
    }

    public Site(int siteId, String siteName, int engineerId, String engineerName) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(int engineerId) {
        this.engineerId = engineerId;
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        this.decommissioned = decommissioned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;
        Site site = (Site) o;
        return getId() == site.getId() &&
                getSiteId() == site.getSiteId() &&
                getEngineerId() == site.getEngineerId() &&
                isDecommissioned() == site.isDecommissioned() &&
                getSiteName().equals(site.getSiteName()) &&
                Objects.equals(getEngineerName(), site.getEngineerName()) &&
                Objects.equals(getCreatedAt(), site.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSiteId(), getSiteName(), getEngineerId(), getEngineerName(), getCreatedAt(), isDecommissioned());
    }
}

