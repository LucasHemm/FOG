package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderFacade
{



    public static void createOrder(int userid, int length, int width, int price, int costPrice, ArrayList<Integer> listOfIDs, ConnectionPool connectionPool) {
        OrderMapper.createOrder(userid, length, width, price, costPrice, listOfIDs, connectionPool);
    }

    public static ArrayList<Order> getOrdersFromUserId(int userid, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrdersFromUserId(userid,connectionPool);
    }

    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getAllOrders(connectionPool);
    }
    public static Order getOrderFromOrderId(int orderid,ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrderFromOrderId(orderid,connectionPool);
    }


    public static void deleteOrderFromOrderId(int orderid, ConnectionPool connectionPool) throws DatabaseException{

        OrderMapper.deleteOrderFromOrderId(orderid,connectionPool);

    }

    public static void changeStatus(int orderid,String status, ConnectionPool connectionPool) throws DatabaseException{

        OrderMapper.changeStatus(orderid,status,connectionPool);

    }

    public static void updatePrice(int partlistid, int newprice, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.updatePrice(partlistid,newprice,connectionPool);
    }
}
