package dat.backend.model.persistence;

import dat.backend.model.entities.Parts;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.Map;

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

    public static double pricePrMeter(int length, int partid, ConnectionPool connectionPool) {
        return PartMapper.pricePrMeter(length, partid, connectionPool);
    }

    public static double costPricePrMeter(int length, int partid, ConnectionPool connectionPool) {
        return PartMapper.costPricePrMeter(length, partid, connectionPool);
    }

    public static double PricePrAmount(int partid, ConnectionPool connectionPool) {
        return PartMapper.PricePrAmount(partid, connectionPool);
    }

    public static double costPricePrAmount(int partid, ConnectionPool connectionPool) {
        return PartMapper.costPricePrAmount(partid, connectionPool);
    }

    public static void newPartInitializer(String description,  int pricePrUnit, int costPricePrUnit, String usage, String type, String unit, String priceUnit,String name, int variantLowerLimit, int variantUpperLimit ,ConnectionPool connectionPool) throws DatabaseException {
        PartMapper.newPartInitializer(description, pricePrUnit, costPricePrUnit, usage, type, unit, priceUnit, name, variantLowerLimit, variantUpperLimit, connectionPool);
    }

    public static int getLengthFromVariantid(int id, ConnectionPool connectionPool) {
        return PartMapper.getLengthFromVariantid(id, connectionPool);
    }
    public static ArrayList<Integer> getAllPartIDs(ConnectionPool connectionPool) throws DatabaseException {
        return PartMapper.getAllPartIDs(connectionPool);
    }

    public static Map<Integer, Parts> getAllParts(ConnectionPool connectionPool) throws DatabaseException {
        return PartMapper.getAllParts(connectionPool);
    }
    public static Parts getPartFromPartID(int partID, ConnectionPool connectionPool) throws DatabaseException {
        return  PartMapper.getPartFromPartID(partID,connectionPool);
    }
    public static void updatePart(int partID, String description, int price, int costPrice, String usage, ConnectionPool connectionPool) throws DatabaseException {
        PartMapper.updatePart(partID,description,price,costPrice,usage,connectionPool);
    }

}
