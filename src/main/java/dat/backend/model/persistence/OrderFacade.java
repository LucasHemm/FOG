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
}
