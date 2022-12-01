package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.PartList;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class OrderMapper
{


    public static ArrayList<Order> getOrdersFromUserId(int userid, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Order> orderList = new ArrayList<>();

        String sql = "SELECT * from orders where userid=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userid);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int orderid = rs.getInt("idorders");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int partlistid = rs.getInt("partlistid");
                    String status = rs.getString("status");

                    PartList partlist = getPartlistFromPartlistId(partlistid,connectionPool);

                    Order order = new Order(orderid, userid, timestamp, partlist, status);
                    orderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return orderList;
    }

    private static PartList getPartlistFromPartlistId(int partlistid, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        PartList partList = null;

        String sql = "SELECT * from partslists where idpartslists=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partlistid);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int postid = rs.getInt("postid");
                    int rafterid = rs.getInt("rafterid");
                    int beam1id = rs.getInt("beam1id");
                    int beam2id = rs.getInt("beam2id");
                    int screwid = rs.getInt("screwid");
                    int roofscrewid = rs.getInt("roofscrewid");
                    int roof1id = rs.getInt("roof1id");
                    int roof2id = rs.getInt("roof2id");
                    int boltid = rs.getInt("boltid");
                    int discid = rs.getInt("discid");
                    int costprice = rs.getInt("costprice");
                    int totalprice = rs.getInt("totalprice");
                    partList = new PartList(partlistid,length,width,postid,rafterid,
                                            beam1id,beam2id,screwid,roofscrewid,roof1id,roof2id,
                                             boltid, discid,costprice,totalprice);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return partList;


    }

}
