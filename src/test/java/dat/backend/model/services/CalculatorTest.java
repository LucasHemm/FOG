package dat.backend.model.services;

import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static ConnectionPool connectionPool = new ConnectionPool();

    @Test
    void percentageGainBeforeTax() {
        System.out.println(Calculator.percentageGainBeforeTax(780,600,connectionPool));
    }

    @Test
    void percentageGainAfterTax() {
        System.out.println(Calculator.percentageGainAfterTax(780,600,connectionPool));
    }

    @Test
    void percentageGainAfterChangedPriceBeforeTax() {
        System.out.println(Calculator.percentageGainAfterChangedPriceBeforeTax(25000,780,600,connectionPool));
    }

    @Test
    void percentageGainAfterChangedPriceAfterTax() {
        System.out.println(Calculator.percentageGainAfterChangedPriceBeforeTax(25000,780,600,connectionPool));
    }

    @Test
    void listOfPartAmounts() {
        System.out.println(Calculator.listOfPartAmounts(420,300,connectionPool));
    }
}