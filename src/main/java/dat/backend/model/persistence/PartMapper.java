package dat.backend.model.persistence;

import dat.backend.model.entities.Parts;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class PartMapper {

    static ArrayList<Integer> partsLengthFromPartid(int partid, ConnectionPool connectionPool) {
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

    static int variantidFromLengthAndPartid(int partlength, int partid, ConnectionPool connectionPool) {
        int variantid = 0;

        String sql = "select * from partsvariants where partsid = ? and partlength = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ps.setInt(2, partlength);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    variantid = rs.getInt("idpartsvariants");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return variantid;
    }

    static int variantidFromPartid(int partid, ConnectionPool connectionPool) {
        int variantid = 0;

        String sql = "select * from partsvariants where partsid = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    variantid = rs.getInt("idpartsvariants");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return variantid;
    }

    static double pricePrMeter(double length, int partid, ConnectionPool connectionPool) {

        double price = 0;

        String sql = "select * from parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("price_pr_unit") * (length / 100);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    static double costPricePrMeter(double length, int partid, ConnectionPool connectionPool) {

        double price = 0;

        String sql = "select * from parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("costprice_pr_unit") * (length / 100);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    static double PricePrAmount(int partid, ConnectionPool connectionPool) {

        double price = 0;

        String sql = "select * from parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("price_pr_unit");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    static double costPricePrAmount(int partid, ConnectionPool connectionPool) {

        double price = 0;

        String sql = "select * from parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("costprice_pr_unit");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    static int getLengthFromVariantid(int id, ConnectionPool connectionPool) {

        int length = 0;

        String sql = "select * from partsvariants where idpartsvariants = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    length = rs.getInt("partlength");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return length;
    }


//********************************************************************************************


    //Following methods fully inserts a new part in database

    static void newPartInitializer(String description, int pricePrUnit, int costPricePrUnit, String usage, String type, String unit, String priceUnit, String name, int variantLowerLimit, int variantUpperLimit, ConnectionPool connectionPool) throws DatabaseException {
        int partid = createNewPart(description, pricePrUnit, costPricePrUnit, usage, type, unit, priceUnit, connectionPool);
        createNewPartVariants(partid, variantLowerLimit, variantUpperLimit, connectionPool);
        addPartToPartList(name, connectionPool);
    }

    private static int createNewPart(String description, int pricePrUnit, int costPricePrUnit, String usage, String type, String unit, String priceUnit, ConnectionPool connectionPool) throws DatabaseException {
        int partid = 0;

        String sql = "insert into parts (part_description, price_pr_unit, costprice_pr_unit, part_usage, part_type, unit, priceunit) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, description);
                ps.setInt(2, pricePrUnit);
                ps.setInt(3, costPricePrUnit);
                ps.setString(4, usage);
                ps.setString(5, type);
                ps.setString(6, unit);
                ps.setString(7, priceUnit);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                partid = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert part into database");
        }


        return partid;
    }

    private static void createNewPartVariants(int partid, int variantLowerLimit, int variantUpperLimit, ConnectionPool connectionPool) throws DatabaseException {
        int partlength = variantLowerLimit;
        String sql = "insert into partsvariants ( partsid, partlength ) values (?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                while (partlength <= variantUpperLimit) {
                    ps.setInt(1, partid);
                    ps.setInt(2, partlength);
                    ps.executeUpdate();
                    partlength += 30;
                }

            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert part variant into database");
        }


    }

    private static void addPartToPartList(String name, ConnectionPool connectionPool) throws DatabaseException {

        String columnName = name + "id";
        String thirdToLastColumn = getColumnNameFromOrdinalPosition(connectionPool);

        String sql1 = "SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0";
        String sql2 = "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0";
        String sql3 = "SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'";

        String sql4 = "ALTER TABLE `fog`.`partslists` \n" +
                "ADD COLUMN `" + columnName + "` INT(11) NOT NULL AFTER `"+thirdToLastColumn+"`";

        String sql5 = "ALTER TABLE `fog`.`partslists` \n" +
                "ADD CONSTRAINT `" + columnName + "`\n" +
                "  FOREIGN KEY (`" + columnName + "`)\n" +
                "  REFERENCES `fog`.`partsvariants` (`idpartsvariants`)\n" +
                "  ON DELETE NO ACTION\n" +
                "  ON UPDATE NO ACTION";

        String sql6 = "SET SQL_MODE=@OLD_SQL_MODE";
        String sql7 = "SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS";
        String sql8 = "SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql3)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql4)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql5)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql6)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql7)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql8)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert partid into database");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static int getOrdinalPosition(ConnectionPool connectionPool) throws DatabaseException {
        int ordinalPosition = 0;
        String sql = "SELECT \n" +
                "COLUMN_NAME,\n" +
                "ORDINAL_POSITION\n" +
                "FROM information_schema.COLUMNS \n" +
                "WHERE TABLE_SCHEMA = 'fog'\n" +
                "AND TABLE_NAME ='partslists'\n" +
                "ORDER BY ORDINAL_POSITION DESC \n" +
                "LIMIT 1;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ordinalPosition = rs.getInt("ORDINAL_POSITION")-5;
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not find ordinal position");
        }
        return ordinalPosition;
    }

    private static String getColumnNameFromOrdinalPosition(ConnectionPool connectionPool) throws DatabaseException {
        String columnName = "";
        int ordinalPosition = getOrdinalPosition(connectionPool);
        String sql = "SELECT \n" +
                "COLUMN_NAME\n" +
                "FROM information_schema.COLUMNS \n" +
                "WHERE TABLE_SCHEMA = 'fog'\n" +
                "AND TABLE_NAME ='partslists'\n" +
                "AND ORDINAL_POSITION = "+ordinalPosition;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    columnName = rs.getString("COLUMN_NAME");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not find column name");
        }
        return columnName;
    }
    static ArrayList<Integer> getAllPartIDs(ConnectionPool connectionPool) throws DatabaseException {

        ArrayList<Integer> idList = new ArrayList<>();
        String sql = "SELECT * from parts";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int partID = rs.getInt("idparts");
                    idList.add(partID);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return idList;
    }



    static Map<Integer, Parts> getAllParts(ConnectionPool connectionPool) throws DatabaseException {

        Map<Integer,Parts> partsMap = new HashMap<>();
        String sql = "SELECT * from parts";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int partID = rs.getInt("idparts");
                    String description = rs.getString("part_description");
                    int price = rs.getInt("price_pr_unit");
                    int costPrice = rs. getInt("costprice_pr_unit");
                    String usage = rs.getString("part_usage");
                    String type = rs.getString("part_type");
                    String unit = rs.getString("unit");
                    String priceUnit = rs.getString("priceunit");
                    Parts part = new Parts(description,price,costPrice,usage,type,unit,priceUnit);
                    partsMap.put(partID,part);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "No users were found");
        }
        return partsMap;
    }
    static Parts getPartFromPartID(int partID, ConnectionPool connectionPool) throws DatabaseException {
        Parts part = null;

        String sql ="SELECT * FROM parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, partID);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    String description = rs.getString("part_description");
                    int price = rs.getInt("price_pr_unit");
                    int costPrice = rs. getInt("costprice_pr_unit");
                    String usage = rs.getString("part_usage");
                    String type = rs.getString("part_type");
                    String unit = rs.getString("unit");
                    String priceUnit = rs.getString("priceunit");
                    part = new Parts(description,price,costPrice,usage,type,unit,priceUnit);
                }

            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not get part");
        }
        return part;
    }
    static void updatePart(int partID, String description, int price, int costPrice, String usage, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "update parts set part_description = ?, price_pr_unit = ?, costprice_pr_unit = ?, part_usage = ? where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, description);
                ps.setInt(2, price);
                ps.setInt(3, costPrice);
                ps.setString(4, usage);
                ps.setInt(5,partID);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not update part");
        }
    }


}
