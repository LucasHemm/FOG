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
            PartFacade.newPartInitializer("38x73 mm. Lægte ubh.",134,96,"til z på bagside af dør", "Lath", "Stk", "Stk","lath",420,420,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("19x100 mm. trykimp. Brædt ",53,38,"til beklædning af skur 1 på 2", "Board", "Stk", "Stk","board",300,300,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("45x95 mm. Reglar ub. ",16,11,"løsholter til skur gavle", "LooseHollowGable", "Stk", "M","gablehollow",300,540,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("45x95 mm. Reglar ub. ",16,11,"løsholter til skur sider", "LooseHollowSide", "Stk", "M","sidehollow",300,540,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("'4,5 x 70 mm. Skruer 400 stk.' ",184,131,"Til beklædning af skur", "Screw", "Stk", "Stk","screw",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("stalddørsgreb 50x75 ",215,154,"Til lås på dør i skur", "DoorHandle", "Stk", "Stk","doorhandle",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("t hængsel 390 mm ",111,79,"Til skurdør", "tHinge", "Stk", "Stk","thinge",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            PartFacade.newPartInitializer("vinkelbeslag 35 ",8,6,"Til montering af løsholter i skur", "AngleFitting", "Stk", "Stk","anglefitting",0,0,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }




    }

//    try {
//        PartFacade.newPartInitializer("4,0x50 mm. beslagskruer 250stk. ",223,159,"Til montering af universalbeslag + hulbånd", "FittingScrew", "Stk", "Stk","fittingscrew",0,0,connectionPool);
//    } catch (DatabaseException e) {
//        e.printStackTrace();
//    }

    @Test
    void partArray(){
        System.out.println(PartFacade.partsLengthFromPartid(3,connectionPool));
    }
}