package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(email, password, connectionPool);
    }

    public static void createUser(String email,String name,String password,String address,int postalcode,String cityName, String streetname, boolean isAdmin, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper.createUser(email,name,password,postalcode,cityName, streetname, connectionPool);
    }


}
