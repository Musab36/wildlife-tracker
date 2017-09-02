import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
	public static void main(String[] args) {
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
        // Home page route
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
      
      // Animals route
	get("/animals", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("animals", Animals.all());
		model.put("template", "templates/animals.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
     
     // Posting animals
	post("/animals", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		ArrayList<Animals> animals = request.session().attribute("animals");
		if (animals == null) {
			animals = new ArrayList<Animals>();
			request.session().attribute("animals", animals);
		}
		String name = request.queryParams("name");
		Animals newAnimal = new Animals(name);
		newAnimal.save();
		model.put("template", "templates/animals-success.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
     
     // Animals id
	get("/animals/:id", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		Animals animals = Animals.find(Integer.parseInt(request.params(":id")));
		model.put("animals", animals);
		model.put("template", "templates/animals.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());

	get("/animals-form", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("template", "templates/animals-form.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());

	// Endangered animals route
	get("/endangered-animals", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("endangeredanimals", EndangeredAnimals.all());
		model.put("template", "templates/endangered-animals.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());

	post("/endangered-animals", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		ArrayList<EndangeredAnimals> endangeredanimals = request.session().attribute("endangeredanimals");
		if (endangeredanimals == null) {
			endangeredanimals = new ArrayList<EndangeredAnimals>();
			request.session().attribute("endangeredanimals", endangeredanimals);
		}

		String name = request.queryParams("name");
		String health = request.queryParams("health");
		int age = Integer.parseInt(request.queryParams("age"));
		EndangeredAnimals newEndangeredAnimals = new EndangeredAnimals(name, health, age);
		newEndangeredAnimals.save();
		model.put("template", "templates/endangered-animals-success.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());

	get("/endangered-animals/:id", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		EndangeredAnimals endangeredanimals = EndangeredAnimals.find(Integer.parseInt(request.params(":id")));
		model.put("endangeredanimals", endangeredanimals);
		model.put("template", "templates/endangered-animals.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());

	get("/endangered-animals-form", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("template", "templates/endangered-animals-form.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
}
}