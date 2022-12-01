package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.ArrayList;

public class OrderFacade
{




    public static ArrayList<Order> getOrdersFromUserId(int userid, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.getOrdersFromUserId(userid,connectionPool);
    }
}
