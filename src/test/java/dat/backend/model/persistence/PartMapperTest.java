package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool = new ConnectionPool();


    @Test
    void newPartInitializer() {
        try {
            PartFacade.newPartInitializer("Part2 blah3",100,60,"blah3", "blah3", "blah3", "blah3","nameblah3",300,660,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}