package dat.backend.model.services;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static ConnectionPool connectionPool = new ConnectionPool();

    @Test
    void totalprice(){
        System.out.println(Calculator.totalPriceBeforeTax(780, 600, connectionPool));
    }

    @Test
    void percentageGainBeforeTax() {
        System.out.println(Calculator.percentageGainBeforeTax(780,600,connectionPool)+"% ***************");
    }

    @Test
    void percentageGainAfterTax() {
        System.out.println(Calculator.percentageGainAfterTax(780,600,connectionPool) +"% ***************");
    }


    @Test
    void percentageGainAfterChangedPriceBeforeTax() {
        System.out.println(Calculator.percentageGainAfterChangedPriceBeforeTax(2500,780,600,connectionPool)+"% ***************");
    }

    @Test
    void percentageGainAfterChangedPriceAfterTax() {
        System.out.println(Calculator.percentageGainAfterChangedPriceBeforeTax(2500,780,600,connectionPool)+"% ***************");
    }

    @Test
    void listOfPartAmounts() {
        System.out.println(Calculator.listOfPartAmounts(780,600,connectionPool));
    }


    @Test
    void listOfIDs(){
        System.out.println(Calculator.listOfIDs(300,300,connectionPool));
    }

}