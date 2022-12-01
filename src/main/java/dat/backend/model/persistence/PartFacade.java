package dat.backend.model.persistence;

import java.util.ArrayList;

public class PartFacade
{
    public static ArrayList<Integer> partsLengthFromPartid(int partid, ConnectionPool connectionPool){
        return PartMapper.partsLengthFromPartid(partid,connectionPool);
    }

    public static int variantidFromLengthAndPartid(int partlength, int partid, ConnectionPool connectionPool){
        return PartMapper.variantidFromLengthAndPartid(partlength,partid,connectionPool);
    }


    public static int variantidFromPartid(int partid, ConnectionPool connectionPool){
        return PartMapper.variantidFromPartid(partid,connectionPool);
    }


}
