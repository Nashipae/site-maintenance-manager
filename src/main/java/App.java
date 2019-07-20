import dao.DB;
import dao.Sql2OEngineerDao;
import dao.Sql2OSiteDao;
import models.Engineer;
import models.Site;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
//        String layout = "templates/layout.hbs";
        Sql2OSiteDao siteDao = new Sql2OSiteDao(DB.sql2o);
        Sql2OEngineerDao engineerDao = new Sql2OEngineerDao(DB.sql2o);

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4568;
        }
        port(port);


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = engineerDao.getAll();
            model.put("engineers", allEngineers);
            List<Site> sites = siteDao.getAll();
            model.put("engineers", allEngineers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


//        get: show new engineer form
        get("/engineers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = engineerDao.getAll();
            model.put("engineers", engineers);
            return new ModelAndView(model, "engineer-add-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/engineer/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = engineerDao.getAll();
            int emp_no = Integer.parseInt(request.queryParams("emp_no"));
            String name = request.queryParams("eng-name");
            Engineer newEngineer = new Engineer(name, emp_no);
            System.out.println(name);
            engineerDao.add(newEngineer);
//            newEngineer.getEngineerDetails();
            model.put("engineers", engineers);
            return new ModelAndView(model, "/engineer-success.hbs");

        }, new HandlebarsTemplateEngine());


        //get: show all sites in all engineers and show all Engineers
        get("/engineer/all-engineers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> allEngineers = engineerDao.getAll();
            model.put("engineers", allEngineers);
            List<Site> sites = siteDao.getAll();
            model.put("sites", sites);
            return new ModelAndView(model, "all-engineers.hbs");
        }, new HandlebarsTemplateEngine());



//        Get details of each engineer
        get("/engineers/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEngineerToFind = Integer.parseInt(request.params("id"));
            Engineer foundEngineer = engineerDao.findById(idOfEngineerToFind);
            model.put("engineers", foundEngineer);   //add it to model for template to display
            return new ModelAndView(model, "engineer_details.hbs");  //individual post page.
        }, new HandlebarsTemplateEngine());



        get("/coming-soon.hbs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "coming-soon.hbs");
        }, new HandlebarsTemplateEngine());



//        show new site form
        get("/sites/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = engineerDao.getAll();
            model.put("engineers", engineers);
            return new ModelAndView(model, "siteadd-form.hbs");
        }, new HandlebarsTemplateEngine());


//        process new site form
        post("/sites", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Site> allSites = siteDao.getAll();
            model.put("sites", allSites);
            int siteId = Integer.parseInt(req.queryParams("siteId"));
            String siteName = req.queryParams("siteName");
            String engineerName = req.queryParams("engineerName");
            int engineerId = Integer.parseInt(req.queryParams("engineerId"));
            Site newSite = new Site(siteId, siteName , engineerId, engineerName);
            System.out.println(newSite.getEngineerId());
            siteDao.add(newSite);
            res.redirect("/sites/all-sites");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all sites in all engineers and show all Engineers
        get("/sites/all-sites", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Site> allSites = siteDao.getAll();
            model.put("sites", allSites);
//            model.put("engineers", allEngineers);
            List<Site> sites = siteDao.getAll();
            model.put("sites", sites);
            return new ModelAndView(model, "all-sites-list.hbs");
        }, new HandlebarsTemplateEngine());



    }

}
