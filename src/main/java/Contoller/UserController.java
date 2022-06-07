package Contoller;

import DAO.UserDAO;
import Model.User;
import io.javalin.http.Context;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class UserController {

    public  static   Jdbi jdbi ;//make it singelton ;
    public UserController(Jdbi jdbi){
      this.jdbi = jdbi ;
    }
    public  static    void listUsers(Context ctx){
        List<User> userNames = jdbi.withExtension(UserDAO.class, dao -> {
            return  dao.listUsers();
        });
//        return userNames;
        ctx.json(userNames) ;
//        ctx.result("Hello") ;


    }

    public  void  addUser(User user){
        jdbi.withExtension(UserDAO.class,dao->{
            dao.insertUser(user);
            return 1;
        });
    }


    public  void deleteUser(long id){
        jdbi.withExtension(UserDAO.class,dao->{
            dao.deleteUser(id);
            return 1;
        });
    }






}
