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
            PartFacade.newPartInitializer("hulbånd 1x20 mm. 10 mtr.",297,199,"Til vindkryds på spær", "HollowBand", "Stk", "Stk","hollowband",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        try {
            PartFacade.newPartInitializer("universal 190 mm højre",32,23,"Til montering af spær på rem", "RightFitting", "Stk", "Stk","rightfitting",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("universal 190 mm venstre",32,23,"Til montering af spær på rem", "LeftFitting", "Stk", "Stk","leftfitting",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


    }

    @Test
    void partArray(){
        System.out.println(PartFacade.partsLengthFromPartid(3,connectionPool));
    }
}