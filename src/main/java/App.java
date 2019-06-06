import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.hbs";

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            model.put("engineers", engineers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
////
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("engineer", Engineer.all());
////        model.put("template", "index.hbs");
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Engineer> engineers = (ArrayList<Engineer>) Engineer.all();
//            model.put("engineer", engineers);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());


        //get: show new site form
        get("/sites/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            model.put("engineers", engineers);
            return new ModelAndView(model, "siteadd-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sites", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            Engineer engineer = Engineer.find(Integer.parseInt(request.queryParams("engineerId")));
            String name = request.queryParams("name");
            Site newSite = new Site(name, engineer.getId());
            newSite.save();
            model.put("engineers", engineers);
            return new ModelAndView(model, "site-success.hbs");
        }, new HandlebarsTemplateEngine());

        //Delete a site
        get("/sites/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Engineer engineer = Engineer.find(Integer.parseInt(req.queryParams("engineerId")));
            Site newSite = new Site(name, engineer.getId());
            newSite.delete();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//        get: show new engineer form
        get("/engineer/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            model.put("engineers", engineers);
            return new ModelAndView(model, "engineer-add-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/engineers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            int emp_no = Integer.parseInt(request.queryParams("emp_no"));
            String name = request.queryParams("eng-name");
            Engineer newEngineer = new Engineer(name, emp_no);
            System.out.println(name);
            newEngineer.save();
            return new ModelAndView(model, "engineer-success.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/engineers/:eng_id/sites/:site_id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":eng_id")));
//            Site site = Site.find(Integer.parseInt(request.params(":site_id")));
//            model.put("engineer", engineer);
//            model.put("site", site);
//            return new ModelAndView(model, "all-engineers.hbs");
//        }, new HandlebarsTemplateEngine());
////
//        post("/engineers/:engineer_id/sites/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Site site = Site.find(Integer.parseInt(request.params("id")));
//            String name = request.queryParams("name");
//            Engineer engineer = Engineer.find(site.getEngineerId());
//            site.update(name);
//            String url = String.format("/engineers/%d/sites/%d", engineer.getId(), site.getId());
//            response.redirect(url);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/sites/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Site site = Site.find(Integer.parseInt(request.params(":id")));
//            model.put("site", site);
//            return new ModelAndView(model, "/");
//        }, new HandlebarsTemplateEngine());
//
//
//        post("/engineers/:engineer_id/sites/:id/delete", (request, response) -> {
//            HashMap<String, Object> model = new HashMap<>();
//            Site site = Site.find(Integer.parseInt(request.params("id")));
//            Engineer engineer = Engineer.find(site.getEngineerId());
//            site.delete();
//            model.put("engineer", engineer);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//
////        get("/engineers/new", (request, response) -> {
////            Map<String, Object> model = new HashMap<>();
////            return new ModelAndView(model, "layout.hbs");
////        }, new HandlebarsTemplateEngine());
////
//
////
////        get("/engineers", (request, response) -> {
////            Map<String, Object> model = new HashMap<>();
////            model.put("engineers", Engineer.all());
////            return new ModelAndView(model, "layout.hbs");
////        }, new HandlebarsTemplateEngine());
////
//        get("/engineers/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":id")));
//            model.put("engineer", engineer);
//            return new ModelAndView(model, "/");
//        }, new HandlebarsTemplateEngine());
////
//        get("engineers/:id/sites/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Engineer engineer = Engineer.find(Integer.parseInt(request.params(":id")));
//            model.put("engineer", engineer);
//            return new ModelAndView(model, "layout.hbs");
//        }, new HandlebarsTemplateEngine());
    }
//
//}
//
//
//
//
//

//    }
    }
