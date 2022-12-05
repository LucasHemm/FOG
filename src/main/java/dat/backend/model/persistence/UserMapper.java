package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper
{
    static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {

                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {

                    int id = rs.getInt("iduser");
                    String name = rs.getString("name");
                    int addressid = rs.getInt("addressid");
                    String address = getAddressFromAddressid(addressid,connectionPool);
                    int postalcode = getPostalCodeFromAddressid(addressid,connectionPool);
                    String cityname = getCityNameFromPostalCode(postalcode,connectionPool);
                    boolean isadmin = rs.getBoolean("isadmin");

                    user = new User(id,email,name,password,address,postalcode,cityname,isadmin);
                    System.out.println(user);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

//    static User createUser(int userid,String email,String name,String password,String address,int postalcode,String cityName,boolean isAdmin, ConnectionPool connectionPool) throws DatabaseException
//    {
//        Logger.getLogger("web").log(Level.INFO, "");
//        User user;
//        String sql = "insert into user (username, password, role) values (?,?,?)";
//        try (Connection connection = connectionPool.getConnection())
//        {
//            try (PreparedStatement ps = connection.prepareStatement(sql))
//            {
//                ps.setString(1, username);
//                ps.setString(2, password);
//                ps.setString(3, role);
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected == 1)
//                {
//                    user = new User(id,email,name,password,address, postalcode,isadmin);
//                } else
//                {
//                    throw new DatabaseException("The user with username = " + username + " could not be inserted into the database");
//                }
//            }
//        }
//        catch (SQLException ex)
//        {
//            throw new DatabaseException(ex, "Could not insert username into database");
//        }
//        return user;
//    }

    private static String getCityNameFromPostalCode(int postalCode, ConnectionPool connectionPool) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        String cityName = null;

        String sql = "SELECT * FROM postalcodes WHERE postalcode = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, postalCode);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    cityName = rs.getString("cityname");

                } else
                {
                    throw new DatabaseException("Wrong postalcode");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return cityName;
    }
    private static String getAddressFromAddressid(int addressid, ConnectionPool connectionPool) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        String address = null;

        String sql = "SELECT * FROM addresses WHERE idaddress = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, addressid);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    address = rs.getString("streetname");

                } else
                {
                    throw new DatabaseException("Wrong addressid");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return address;
    }
    private static int getPostalCodeFromAddressid(int addressid, ConnectionPool connectionPool) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        int postalcode = 0;

        String sql = "SELECT * FROM addresses WHERE idaddress = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, addressid );
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    postalcode = rs.getInt("postalcode");

                } else
                {
                    throw new DatabaseException("Wrong addressid");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return postalcode;
    }


    public static User getUserFromUserId(int userid, ConnectionPool connectionPool) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM users WHERE iduser = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userid);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int addressid = rs.getInt("addressid");
                    String address = getAddressFromAddressid(addressid,connectionPool);
                    int postalcode = getPostalCodeFromAddressid(addressid,connectionPool);
                    String cityname = getCityNameFromPostalCode(postalcode,connectionPool);
                    boolean isadmin = rs.getBoolean("isadmin");

                    user = new User(userid,email,name,password,address,postalcode,cityname,isadmin);
                    System.out.println(user);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    public static ArrayList<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;
        ArrayList<User> userArrayList = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int userid = rs.getInt("iduser");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    int addressid = rs.getInt("addressid");
                    String address = getAddressFromAddressid(addressid,connectionPool);
                    int postalcode = getPostalCodeFromAddressid(addressid,connectionPool);
                    String cityname = getCityNameFromPostalCode(postalcode,connectionPool);
                    boolean isadmin = rs.getBoolean("isadmin");

                    user = new User(userid,email,name,password,address,postalcode,cityname,isadmin);
                    userArrayList.add(user);
                    System.out.println(user);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return userArrayList;
    }
}
