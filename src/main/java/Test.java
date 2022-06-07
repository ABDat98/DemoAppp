import Contoller.UserController;
import Model.User;
import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;
import java.util.*;




public class Test {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7777);  // app object it is start in port 7777
        app._conf.enableWebjars() ;


        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/EQUPTAL?user=root&password=123654789.3") ;
        jdbi.installPlugin(new SqlObjectPlugin()); //install the plugin into your Jdbi instance
        UserController userController = new UserController(jdbi) ;
        //////////////////////////////return vue component //////////////////////////////
        app.get("/vue", new VueComponent("hello-world"));//return vue component ;
        //////////////////////////////

        ////////////////////////////// return List of User Using Static keyword //////////////////////////////
        app.get("/user" ,UserController::listUsers);



        
        app.post("/user", ctx -> {
            User user = ctx.bodyAsClass(User.class) ;
            String s =user.getName();
            userController.addUser(user);
            ctx.result("Helllo " +user.getName());
//            ctx.json(user);
            ctx.status(201);

        }) ;
        //guid ;
        //uuid ;
        app.delete("/user/<id>" ,ctx->{
             userController.deleteUser(Integer.parseInt(ctx.pathParam("id")));
             ctx.status(200) ;
        });










//        jdbi.installPlugin(new SqlObjectPlugin()); what this do ;
//
//        List<User> users = jdbi.withHandle(handle -> {
//            return null;
//        }) ;
//
//         jdbi.useHandle(handle -> {
//            handle.execute("create table contacts (id int primary key, name varchar(100))");
//        });
//
//        jdbi.withExtension(User.class,dao ->{
//            dao.getUsers();
//            return  null ;
//        }) ;
//        Handle handle = jdbi.open();
//        handle.createQuery("").bind("a",2).mapTo(String.class).one();
//        handle.createUpdate("");
//
//
//
//        //three type of handler
//        //1-before 2-endpoint 3-after
//        app.before(ctx -> {
//            // runs before all requests
//        });
//        app.before("/before", ctx -> {
//        });
//        app.get("/before", ctx -> {
//        });
//
//        //path-parameters
//        app.get("/hello/<name>/{id}", ctx -> { // the <> syntax allows slashes ('/') as part of the parameter
//            ctx.result("Hello: " + ctx.pathParam("name") + ctx.pathParam("id"));
//            ctx.status(201);//Created
//        });
//
//        //wildcard parameters
//        //http://localhost:7777/path/2/r
//        //You are here because /path/2/r matches /path/*
//
//        app.get("/path/*", ctx -> { // will match anything starting with /path/
//            ctx.result("You are here because " + ctx.path() + " matches " + ctx.matchedPath());
//        });
//
//        //after request
//        app.after(ctx -> {
//            // run after all requests
//        });
//        app.after("/path/*", ctx -> {
//            // runs after request to /path/*
//        });
//        app.post("/body",ctx ->
//        {
//            ctx.result( "this is body" + ctx.bodyAsBytes() + ctx.protocol())  ;
//        }) ;
//        app.get("/users" , new User().getUsers());
//
//    }
    }
}
