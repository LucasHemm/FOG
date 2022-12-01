package dat.backend.model.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class PartMapper
{

    static ArrayList<Integer> partsLengthFromPartid(int partid, ConnectionPool connectionPool){
        ArrayList<Integer> lengthList = new ArrayList<>();


        String sql = "select * from partsvariants where partsid = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lengthList.add(rs.getInt("partlength"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lengthList;
    }



    static int variantidFromLengthAndPartid(int partlength, int partid, ConnectionPool connectionPool){
        int variantid = 0;

        String sql = "select * from partsvariants where partsid = ? and partlength = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ps.setInt(2, partlength);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    variantid= rs.getInt("idpartsvariants");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return variantid;
    }



    static int variantidFromPartid(int partid, ConnectionPool connectionPool){
    int variantid = 0;

    String sql = "select * from partsvariants where partsid = ?";

    try (Connection connection = connectionPool.getConnection()) {


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, partid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                variantid= rs.getInt("idpartsvariants");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return variantid;
}



}
