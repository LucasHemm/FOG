package dat.backend.model.persistence;

import dat.backend.model.entities.PartList;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class OrderMapper {

    static void createOrder(int userid, int length, int width, int price, int costPrice, ArrayList<Integer> listOfIDs,  ConnectionPool connectionPool) {
        String sql = "INSERT INTO orders (userid, partlistid) VALUES(?,?)";
        int partListid = createPartList(length, width, price, costPrice, listOfIDs, connectionPool);

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


    private static int createPartList(int length, int width, int price, int costPrice, ArrayList<Integer> listOfIDs, ConnectionPool connectionPool) {
        int partListid = 0;


        String sql = "INSERT INTO partsLists ( length, width, postid, rafterid, beam1id, beam2id, screwid, roofscrewid, roof1id, roof2id, boltid, discid, costprice, totalprice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, length);
                ps.setInt(2, width);
                ps.setInt(3, listOfIDs.get(0));
                ps.setInt(4, listOfIDs.get(1));
                ps.setInt(5, listOfIDs.get(2));
                if (listOfIDs.get(3) != 0) {
                    ps.setInt(6, listOfIDs.get(3));
                }
                ps.setInt(7, listOfIDs.get(4));
                ps.setInt(8, listOfIDs.get(5));
                ps.setInt(9, listOfIDs.get(6));
                if (listOfIDs.get(7) != 0) {
                    ps.setInt(10, listOfIDs.get(7));
                }
                ps.setInt(11, listOfIDs.get(8));
                ps.setInt(12, listOfIDs.get(9));
                ps.setInt(13, costPrice);
                ps.setInt(14, price);

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

}
