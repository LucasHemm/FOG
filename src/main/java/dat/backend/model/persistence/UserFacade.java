package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User getUserFromUserId(int userid, ConnectionPool connectionPool) throws DatabaseException{
        return UserMapper.getUserFromUserId(userid,connectionPool);
    }
    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException{
        return UserMapper.getAllUsers(connectionPool);
    }


    public static User createUser(String email,String name,String password,int postalcode,String cityName, String streetname, boolean isAdmin, ConnectionPool connectionPool) throws DatabaseException
    {
       return UserMapper.createUser(email,name,password,postalcode,cityName, streetname, connectionPool);
    }


}
