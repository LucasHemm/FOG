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

    public static User createUser(String email,String name,String password,int postalcode,String cityName, String streetName, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        int id = 0;
        User user = null;

        String sql = "insert into users (email, name, password, addressid) values (?,?,?,?)";
        System.out.println(email + name + password + postalcode + cityName + streetName);

        boolean check = checkForPostalCode(postalcode, connectionPool);
        if (!check) {
            insertIntoPostalCode(postalcode, cityName, connectionPool);
        }
        int addressid = insertUserAddress(streetName, postalcode, connectionPool);

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setInt(4, addressid);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    id = rs.getInt(1);
                    user = new User(id, email, name, password, streetName, postalcode, cityName, false);
                } else
                {
                    throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert email into database");
        }
        return user;
    }

    private static void insertIntoPostalCode(int postalCode, String cityName, ConnectionPool connectionPool) {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "INSERT INTO postalcodes (postalcode, cityname) VALUES(?,?)";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, postalCode);
                ps.setString(2, cityName);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static int insertUserAddress(String streetname, int postalcode, ConnectionPool connectionPool) {
        Logger.getLogger("web").log(Level.INFO, "");

        int addressid = 0;

        String sql = "INSERT INTO addresses (streetname, postalcode) VALUES(?,?)";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, streetname);
                ps.setInt(2, postalcode);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                addressid = rs.getInt(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addressid;

    }

    private static Boolean checkForPostalCode(int DBpostal, ConnectionPool connectionPool) {

        String sql = "SELECT * FROM postalcodes where postalcode = "+DBpostal;

        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return  true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }


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
            throw new DatabaseException(ex, "Error logging in. Something went wrong in getCity");
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
            throw new DatabaseException(ex, "Error logging in. Something went wrong in getAddress");
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
            throw new DatabaseException(ex, "Error logging in. Something went wrong in getPostalcode");
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
                while (rs.next())
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
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return userArrayList;
    }
}
