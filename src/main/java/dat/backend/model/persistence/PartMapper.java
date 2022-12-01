package dat.backend.model.persistence;

import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;

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

    static int pricePrMeter(int length, int partid, ConnectionPool connectionPool){

        int price = 0;

        String sql = "select * from parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("price_pr_unit") * (length/100);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    static int costPricePrMeter(int length, int partid, ConnectionPool connectionPool){

        int price = 0;

        String sql = "select * from parts where idparts = ?";

        try (Connection connection = connectionPool.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("costprice_pr_unit") * (length/100);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    static int PricePrAmount(int partid, ConnectionPool connectionPool){

        int price = 0;

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


    static int costPricePrAmount(int partid, ConnectionPool connectionPool) {

        int price = 0;

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




    //Following methods fully inserts a new part in database

    static void newPartInitializer(String description,  int pricePrUnit, int costPricePrUnit, String usage, String type, String unit, String priceUnit,String name, int variantLowerLimit, int variantUpperLimit ,ConnectionPool connectionPool) throws DatabaseException {
        int partid = createNewPart(description, pricePrUnit, costPricePrUnit, usage, type, unit, priceUnit, connectionPool);
        createNewPartVariants(partid,variantLowerLimit, variantUpperLimit,connectionPool);
        addPartToPartList(name, connectionPool);
    }


    private static int createNewPart( String description,  int pricePrUnit, int costPricePrUnit, String usage, String type, String unit, String priceUnit, ConnectionPool connectionPool) throws DatabaseException {
        int partid = 0;

        String sql = "insert into parts (part_description, price_pr_unit, costprice_pr_unit, part_usage, part_type, unit, priceunit) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
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
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert part into database");
        }


        return partid;
    }

    private static void createNewPartVariants(int partid,int variantLowerLimit, int variantUpperLimit, ConnectionPool connectionPool ) throws DatabaseException {
        int partlength = variantLowerLimit;
        String sql = "insert into partsvariants ( partsid, partlength ) values (?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                while (partlength <= variantUpperLimit) {
                    ps.setInt(1, partid);
                    ps.setInt(2, partlength);
                    ps.executeUpdate();
                    partlength += 30;
                }

            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert part variant into database");
        }


    }

    private static void addPartToPartList(String name, ConnectionPool connectionPool) throws DatabaseException {

        String columnName = name+"id";

        String sql1 ="SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0";
        String sql2 ="SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0";
        String sql3 ="SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'";

        String sql4 ="ALTER TABLE `fog`.`partslists` \n" +
                "ADD COLUMN `"+columnName+"` INT(11) NOT NULL AFTER `totalprice`,\n" +
                "ADD INDEX `fk_partslists_partsvariants5_idx` (`"+columnName+"` ASC) VISIBLE";

        String sql5 ="ALTER TABLE `fog`.`partslists` \n" +
                "ADD CONSTRAINT `fk_partslists_partsvariants5`\n" +
                "  FOREIGN KEY (`"+columnName+"`)\n" +
                "  REFERENCES `fog`.`partsvariants` (`idpartsvariants`)\n" +
                "  ON DELETE NO ACTION\n" +
                "  ON UPDATE NO ACTION";

        String sql6 ="SET SQL_MODE=@OLD_SQL_MODE";
        String sql7 ="SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS";
        String sql8 ="SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS";

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

}
