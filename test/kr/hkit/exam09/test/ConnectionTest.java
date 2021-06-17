package kr.hkit.exam09.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    @Test
    public void test1(){
        assertNotNull(Query.getConn());
    }
}
