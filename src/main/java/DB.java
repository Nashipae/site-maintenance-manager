import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.*;
//import sun.rmi.rmic.Constants;

import java.net.URI;
import java.net.URISyntaxException;

//import environments.Constants;


public class DB {
   // public static Sql2o sql2o= new Sql2o("jdbc:postgresql://localhost:5432/site_manager", "postgres", "postgres");
    public static Sql2o sql2o= new Sql2o(Constant.URL_PROD, Constant.USERNAME, Constant.PASSWORD);

    public static URI dbUri;
    //public static Sql2o sql2o;
    static Logger logger = LoggerFactory.getLogger(DB.class);

    static {
        try{
            if(System.getenv("DATABASE_URL")==null){
//                dbUri = new URI(Constants.URL_PROD);
                dbUri = new URI("postgres://localhost:5432/site_manager");
                logger.info("Using local database.");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }

            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo()==null) ? Constant.USERNAME : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo()==null) ? Constant.PASSWORD : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://"+host+":"+ port + path, username, password);
        }catch (URISyntaxException e){
            logger.error("Database connection failed.");
        }
    }
}
