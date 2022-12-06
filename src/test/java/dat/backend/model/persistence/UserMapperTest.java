package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "sangill2312";
    private final static String URL = "jdbc:mysql://localhost:3306/fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool = new ConnectionPool();

    @Test
    void testytest(){
        try {
            UserMapper.createUser("mail@mail","NAvn","kode",9999,"byby","bejvej",connectionPool );
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

}