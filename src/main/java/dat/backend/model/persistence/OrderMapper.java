package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.PartList;
import dat.backend.model.entities.Parts;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class OrderMapper {


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

                    PartList partlist = getPartlistFromPartlistId(partlistid, connectionPool);

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
        ArrayList<Parts> partsArrayList = new ArrayList<>();

        String sql = "SELECT * from partslists where idpartslists=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partlistid);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int postid = rs.getInt("postid");
                    Parts post = OrderMapper.getPartFromPartVariantId(postid, connectionPool);
                    partsArrayList.add(post);
                    int rafterid = rs.getInt("rafterid");
                    Parts rafter = OrderMapper.getPartFromPartVariantId(rafterid, connectionPool);
                    partsArrayList.add(rafter);
                    int beam1id = rs.getInt("beam1id");
                    Parts beam1 = OrderMapper.getPartFromPartVariantId(beam1id, connectionPool);
                    partsArrayList.add(beam1);
                    int beam2id = rs.getInt("beam2id");
                    Parts beam2 = OrderMapper.getPartFromPartVariantId(beam2id, connectionPool);
                    if (beam2 != null) {
                        partsArrayList.add(beam2);
                    }
                    int fittingscrewid = rs.getInt("fittingscrewid");
                    Parts fittingscrew = OrderMapper.getPartFromPartVariantId(fittingscrewid, connectionPool);
                    partsArrayList.add(fittingscrew);
                    int roofscrewid = rs.getInt("roofscrewid");
                    Parts roofscrew = OrderMapper.getPartFromPartVariantId(roofscrewid, connectionPool);
                    partsArrayList.add(roofscrew);
                    int roof1id = rs.getInt("roof1id");
                    Parts roof1 = OrderMapper.getPartFromPartVariantId(roof1id, connectionPool);
                    partsArrayList.add(roof1);
                    int roof2id = rs.getInt("roof2id");
                    Parts roof2 = OrderMapper.getPartFromPartVariantId(roof2id, connectionPool);
                    if (roof2 != null) {
                        partsArrayList.add(roof2);
                    }
                    int boltid = rs.getInt("boltid");
                    Parts bolt = OrderMapper.getPartFromPartVariantId(boltid, connectionPool);
                    partsArrayList.add(bolt);
                    int discid = rs.getInt("discid");
                    Parts disc = OrderMapper.getPartFromPartVariantId(discid, connectionPool);
                    partsArrayList.add(disc);
                    int hollowbandid = rs.getInt("hollowbandid");
                    Parts hollowband = OrderMapper.getPartFromPartVariantId(hollowbandid, connectionPool);
                    partsArrayList.add(hollowband);
                    int rightfittingid = rs.getInt("rightfittingid");
                    Parts rightfitting = OrderMapper.getPartFromPartVariantId(rightfittingid, connectionPool);
                    partsArrayList.add(rightfitting);
                    int leftfittingid = rs.getInt("leftfittingid");
                    Parts leftfitting = OrderMapper.getPartFromPartVariantId(leftfittingid, connectionPool);
                    partsArrayList.add(leftfitting);
                    int lathid = rs.getInt("lathid");
                    Parts lath = OrderMapper.getPartFromPartVariantId(lathid, connectionPool);
                    partsArrayList.add(lath);
                    int boardid = rs.getInt("boardid");
                    Parts board = OrderMapper.getPartFromPartVariantId(boardid, connectionPool);
                    partsArrayList.add(board);
                    int gablehollowid = rs.getInt("gablehollowid");
                    Parts gablehollow = OrderMapper.getPartFromPartVariantId(gablehollowid, connectionPool);
                    partsArrayList.add(gablehollow);
                    int sidehollowid = rs.getInt("sidehollowid");
                    Parts sidehollow = OrderMapper.getPartFromPartVariantId(sidehollowid, connectionPool);
                    partsArrayList.add(sidehollow);
                    int screwid = rs.getInt("screwid");
                    Parts screw = OrderMapper.getPartFromPartVariantId(screwid, connectionPool);
                    partsArrayList.add(screw);
                    int doorhandleid = rs.getInt("doorhandleid");
                    Parts doorhandle = OrderMapper.getPartFromPartVariantId(doorhandleid, connectionPool);
                    partsArrayList.add(doorhandle);
                    int thingeid = rs.getInt("thingeid");
                    Parts thinge = OrderMapper.getPartFromPartVariantId(thingeid, connectionPool);
                    partsArrayList.add(thinge);
                    int anglefittingid = rs.getInt("anglefittingid");
                    Parts anglefitting = OrderMapper.getPartFromPartVariantId(anglefittingid, connectionPool);
                    partsArrayList.add(anglefitting);



                    boolean hasShed = rs.getBoolean("hasShed");
                    int shedlength = rs.getInt("shedlength");
                    int shedwidth = rs.getInt("shedwidth");

                    int costprice = rs.getInt("costprice");
                    int totalprice = rs.getInt("totalprice");

                    partList = new PartList(partlistid, length, width, postid, rafterid,
                            beam1id, beam2id, fittingscrewid, roofscrewid, roof1id, roof2id,
                            boltid, discid,hollowbandid,rightfittingid,leftfittingid,lathid,boardid,gablehollowid,sidehollowid,screwid,doorhandleid,thingeid,anglefittingid,hasShed,shedlength,shedwidth, costprice, totalprice, partsArrayList);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return partList;


    }

    private static Parts getPartFromPartVariantId(int partVariantId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Parts part = null;

        String sql = "SELECT * from partsvariants where idpartsvariants=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partVariantId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int length = rs.getInt("partlength");
                    int partid = rs.getInt("partsid");
                    System.out.println(partid + "dette er dit part id");
                    part = OrderMapper.getPartFromPartId(partid, length, connectionPool);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return part;
    }


    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Order> orderList = new ArrayList<>();

        String sql = "SELECT * from orders";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int orderid = rs.getInt("idorders");
                    int userid = rs.getInt("userid");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int partlistid = rs.getInt("partlistid");
                    String status = rs.getString("status");

                    PartList partlist = getPartlistFromPartlistId(partlistid, connectionPool);

                    Order order = new Order(orderid, userid, timestamp, partlist, status);
                    orderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return orderList;

    }

    private static Parts getPartFromPartId(int partid, int length, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Parts part = null;

        String sql = "SELECT * from parts where idparts=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String part_description = rs.getString("part_description");
                    int price_pr_unit = rs.getInt("price_pr_unit");
                    int costprice_pr_unit = rs.getInt("costprice_pr_unit");
                    String part_usage = rs.getString("part_usage");
                    String part_type = rs.getString("part_type");
                    String unit = rs.getString("unit");
                    String priceunit = rs.getString("priceunit");

                    part = new Parts(part_description, price_pr_unit, costprice_pr_unit, part_usage, part_type, unit, priceunit, length);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return part;


    }

    static void createOrder(int userid, int length, int width, int price, int costPrice, ArrayList<Integer> listOfIDs, boolean hasShed, int shedLength, int shedWidth, ConnectionPool connectionPool) {
        String sql = "INSERT INTO orders (userid, partlistid) VALUES(?,?)";
        int partListid = createPartList(length, width, price, costPrice, listOfIDs, hasShed,shedLength, shedWidth,connectionPool);

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userid);
                ps.setInt(2, partListid);
                ps.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private static int createPartList(int length, int width, int price, int costPrice, ArrayList<Integer> listOfIDs, boolean hasShed, int shedLength, int shedWidth, ConnectionPool connectionPool) {
        int partListid = 0;

        String sql = "INSERT INTO partslists ( length, width, postid, rafterid, beam1id, beam2id, fittingscrewid, roofscrewid, roof1id, roof2id, boltid, discid, hollowbandid, rightfittingid, leftfittingid, lathid, boardid, gablehollowid, sidehollowid, screwid, doorhandleid, thingeid, anglefittingid, costprice, totalprice, hasshed, shedlength, shedwidth) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, length);
                ps.setInt(2, width);
                ps.setInt(3, listOfIDs.get(0));
                ps.setInt(4, listOfIDs.get(1));
                ps.setInt(5, listOfIDs.get(2));
                ps.setInt(6, listOfIDs.get(3));
                ps.setInt(7, listOfIDs.get(4));
                ps.setInt(8, listOfIDs.get(5));
                ps.setInt(9, listOfIDs.get(6));
                ps.setInt(10, listOfIDs.get(7));
                ps.setInt(11, listOfIDs.get(8));
                ps.setInt(12, listOfIDs.get(9));
                ps.setInt(13, listOfIDs.get(10));
                ps.setInt(14, listOfIDs.get(11));
                ps.setInt(15, listOfIDs.get(12));
                ps.setInt(16, listOfIDs.get(13));
                ps.setInt(17, listOfIDs.get(14));
                ps.setInt(18, listOfIDs.get(15));
                ps.setInt(19, listOfIDs.get(16));
                ps.setInt(20, listOfIDs.get(17));
                ps.setInt(21, listOfIDs.get(18));
                ps.setInt(22, listOfIDs.get(19));
                ps.setInt(23, listOfIDs.get(20));
                ps.setInt(24, costPrice);
                ps.setInt(25, price);
                ps.setBoolean(26, hasShed);
                ps.setInt(27, shedLength);
                ps.setInt(28, shedWidth);

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                partListid = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return partListid;
    }


    public static Order getOrderFromOrderId(int orderid, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Order order = null;

        String sql = "SELECT * from orders where idorders=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderid);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int userid = rs.getInt("userid");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int partlistid = rs.getInt("partlistid");
                    String status = rs.getString("status");

                    PartList partlist = getPartlistFromPartlistId(partlistid, connectionPool);

                    order = new Order(orderid, userid, timestamp, partlist, status);

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return order;
    }

    public static void deleteOrderFromOrderId(int orderid, ConnectionPool connectionPool) throws DatabaseException {

        int partlistid = getPartListIdFromOrderId(orderid, connectionPool);
        String sql = "delete from orders where idorders = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderid);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("PARTLIST ID " + partlistid);
        deletePartlistFromPartlistId(partlistid, connectionPool);

    }

    public static void deletePartlistFromPartlistId(int partlistid, ConnectionPool connectionPool) {


        String sql = "delete from partslists where idpartslists = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partlistid);
                ps.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static int getPartListIdFromOrderId(int orderid, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        int partlistid = 0;

        String sql = "SELECT * from orders where idorders=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderid);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    partlistid = rs.getInt("partlistid");

                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return partlistid;
    }

    static void changeStatus(int orderid, String status, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "update orders set status = ? where idorders=?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, status);
                ps.setInt(2, orderid);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not add money into database");
        }
    }

    static void updatePrice(int partslistsid, int price, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "update partslists set totalprice = ? where idpartslists=?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, price);
                ps.setInt(2, partslistsid);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not add money into database");
        }
    }
}
