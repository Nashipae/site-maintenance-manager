import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
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


//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
////        ArrayList<Engineer> engineers = Engineer.all();
//            model.put("engineer", engineer);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
////
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("engineer", Engineer.all());
////        model.put("template", "index.hbs");
//            return new ModelAndView(model, index.hbs);
//        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Engineer> engineers = (ArrayList<Engineer>) Engineer.all();
            model.put("engineer", engineers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


    }
}