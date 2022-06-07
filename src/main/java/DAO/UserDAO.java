package DAO;

import Model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.*;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserDAO {
//
//    @SqlUpdate("CREATE TABLE \"user\" (id INTEGER PRIMARY KEY, \"name\" VARCHAR)")
//    void createTable();

    @SqlUpdate("INSERT INTO USER_PROFILE (id, name , phone) VALUES (:id, :name  , :phone)")
    void insertUser(@BindBean User user);

    @SqlUpdate("DELETE FROM USER_PROFILE WHERE  ID = :id" )
    void deleteUser(@Bind("id") long id);

    @SqlQuery("SELECT * FROM USER_PROFILE ORDER BY name")
    @RegisterBeanMapper(User.class)
    List<User> listUsers();

}
