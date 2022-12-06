package dat.backend.model.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    ConnectionPool connectionPool = new ConnectionPool();
    @Test
    void parlist(){
        OrderMapper.deletePartlistFromPartlistId(11,connectionPool);
    }

}