package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

  public static Result index() {
    return ok(index.render());
  }

  // Simple GET example
  // curl http://localhost:9001/say1
  public static Result sayHello() {
    return ok("Hello Friend!");

  }

  // Simple POST query string example
  // curl http://localhost:9001/say2?name=Abraham
  public static Result sayHelloToString(String name) {
    return ok("Hello " + name + "! Your name is " + name.length()
        + " letters long");
  }

  // POST JSON in body example
  // curl -H "Content-Type: application/json" -d "{\"name\":\"Lucas\"}" http://localhost:9001/say3  
  @BodyParser.Of(play.mvc.BodyParser.Json.class)
  public static Result sayHelloToJson() {
    org.codehaus.jackson.JsonNode json = request().body().asJson();
    String name = json.findPath("name").getTextValue();
    if (name == null) {
      return badRequest("Missing parameter [name]");
    } else {
      // note this responds to the json request with a json response, 
      // which seems appropriate and also seems to make the jsRoutes / jQuery
      // .ajax() call happier!
      return sayHelloWithJson(name);
    }
  }

  // GET JSON example
  // curl http://localhost:9001/say4?name=Kyle
  public static Result sayHelloWithJson(String name) {
    org.codehaus.jackson.node.ObjectNode result = play.libs.Json.newObject();
    result.put("message", "Hello " + name + "!");
    result.put("namelength", name.length());
    return ok(result);
  }

  // -- Javascript routing
  public static Result javascriptRoutes() {
    response().setContentType("text/javascript");
    return ok(Routes.javascriptRouter(
        "jsRoutes",
        controllers.routes.javascript.Application.sayHello(),
        controllers.routes.javascript.Application.sayHelloToString(),
        controllers.routes.javascript.Application.sayHelloToJson(),
        controllers.routes.javascript.Application.sayHelloWithJson()));
  }

}