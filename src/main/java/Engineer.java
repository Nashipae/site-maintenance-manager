import java.time.LocalDateTime;
import java.util.List;

import org.sql2o.*;


public class Engineer{
    private int id;
    private int emp_no;
    private String name;
    private String siteAllocation;
    private LocalDateTime hireDate;
    private String engineerDetails;
    private int mobileNo;


    @Override
    public boolean equals(Object otherEngineer) {
        if (!(otherEngineer instanceof Engineer)) {
            return false;
        } else {
            Engineer newEngineer = (Engineer) otherEngineer;
            return this.getName().equals(newEngineer.getName()) && this.getId() == newEngineer.getId();
        }

        }

    public int getEmp_no(){return emp_no;}

    public Engineer(String name, int emp_no){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSiteAllocation(){
        return siteAllocation;
    }

    public String getEngineerDetails(){
        return engineerDetails;
    }

    public int getMobileNo(){ return mobileNo;}

    public LocalDateTime getHireDate(){
        return hireDate;
    }

    public static List<Engineer> all() {
        String sql = "SELECT id, name FROM engineers";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Engineer.class);
        }
    }

    public int getId() {
        return id;
    }

    public static Engineer find(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM engineers where id=:id";
            Engineer engineer = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Engineer.class);
            return engineer;
        }
    }

    public List<Site> getSites() {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sites where engineerId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Site.class);
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO engineers (name, emp_no) VALUES (:name, emp_no)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("emp_no", this.emp_no)
                    .executeUpdate()
                    .getKey();
        }
    }

}