package TFTGame;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    void testNamedUnit(){
        Unit unit = new Unit("Trevor");
        assertEquals(unit.getName(), "Trevor");
        assertEquals(unit.getCost(), 1);
        assertEquals(unit.getHP(), 0);
        assertEquals(unit.getMaxHP(), 0);
        assertEquals(unit.getMana(), 0);
        assertEquals(unit.getMaxMana(), 0);
        assertEquals(unit.getATK(), 0);
    }


    @Test
    void testFullUnit(){
        Unit unit = new Unit("Nicholas",1, 1, 300, 0, 60, 40);
    }
}
