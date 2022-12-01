package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class PartFacade {
    public static ArrayList<Integer> partsLengthFromPartid(int partid, ConnectionPool connectionPool) {
        return PartMapper.partsLengthFromPartid(partid, connectionPool);
    }

    public static int variantidFromLengthAndPartid(int partlength, int partid, ConnectionPool connectionPool) {
        return PartMapper.variantidFromLengthAndPartid(partlength, partid, connectionPool);
    }


    public static int variantidFromPartid(int partid, ConnectionPool connectionPool) {
        return PartMapper.variantidFromPartid(partid, connectionPool);
    }

    public static int pricePrMeter(int length, int partid, ConnectionPool connectionPool) {
        return PartMapper.pricePrMeter(length, partid, connectionPool);
    }

    public static int costPricePrMeter(int length, int partid, ConnectionPool connectionPool) {
        return PartMapper.costPricePrMeter(length, partid, connectionPool);
    }

    public static int PricePrAmount(int partid, ConnectionPool connectionPool) {
        return PartMapper.PricePrAmount(partid, connectionPool);
    }

    public static int costPricePrAmount(int partid, ConnectionPool connectionPool) {
        return PartMapper.costPricePrAmount(partid, connectionPool);
    }

    static void newPartInitializer(String description,  int pricePrUnit, int costPricePrUnit, String usage, String type, String unit, String priceUnit,String name, int variantLowerLimit, int variantUpperLimit ,ConnectionPool connectionPool) throws DatabaseException {
        PartMapper.newPartInitializer(description, pricePrUnit, costPricePrUnit, usage, type, unit, priceUnit, name, variantLowerLimit, variantUpperLimit, connectionPool);
    }


}
