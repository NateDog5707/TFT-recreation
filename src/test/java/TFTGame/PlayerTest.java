package TFTGame;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player;

    @Test
    public void testStartGame(){
        if (player.getBalance() != 0 || player.getLevel() != 1 || player.getExp() != 0 || player.getStreak() != 0 || player.getHp() != 100){
            System.out.println("Player.testStartGame() failed");
            throw new AssertionError();
        }
    }
    @Test
    public void testDefaultName() {
        if (!player.getUsername().equals("John Doe")){
            System.out.println("Player.testDefaultName() failed");
            throw new AssertionError();
        }
    }
    @Test
    public void testCustomName(){
        player = new Player("Hey");
        if (!player.getUsername().equals("Hey")){
            System.out.println("Player.testCustomName() failed");
            throw new AssertionError();
        }
    }
    @Test
    void testCreateNewBench(){
        if (player.getBench() == null){
            System.out.println("Player.testCreateNewBench() failed");
            throw new AssertionError();
        }
    }

    @Test
    void testAddUnitBench(){
        assertNotEquals(player.getBench(), null);
        assertNotEquals(player.getUnitsOnBench(), null);
        Unit unit = new Unit("Test Unit");
        player.addUnitBench(unit);
        assertEquals(player.getUnitsOnBench()[0], unit);

        unit = new Unit("Paul");
        player.addUnitBench(unit);
        assertEquals(player.getUnitsOnBench()[1], unit);


        unit = new Unit("Greg");
        player.addUnitBench(unit);
        assertEquals(player.getUnitsOnBench()[2], unit);
    }

    @Test
    void testRemoveUnitBench(){
        Unit[] bench = player.getUnitsOnBench();
        assertNotEquals(bench, null);
        Unit unit = new Unit("Test Unit");
        player.addUnitBench(unit);
        assertEquals(bench[0], unit);
        player.removeUnitBench(unit);
        assertNull(bench[0]);

        Unit unit2 = new Unit("Unit2");
        player.addUnitBench(unit);
        player.addUnitBench(unit2);
        player.removeUnitBench(unit,0);

        assertNull(bench[0]);
        assertEquals(bench[1], unit2);
        player.removeUnitBench(unit2);
        assertNull(bench[0]);
        assertNull(bench[1]);
    }

    @Test
    void testRemoveUnitField() {
        Unit[][] field = player.getUnitsOnField();
        Unit unit1 = new Unit("Test Unit");
        Unit unit2 = new Unit("Unit2");
        field[0][0] = unit1;
        field[1][0] = unit2;
        assert (field[0][0] == unit1 && field[1][0] == unit2);
        assertEquals(field[0][0], unit1);
        assertEquals(field[1][0], unit2);
        player.removeUnitField(unit1, 0, 0);
        assertNull(field[0][0]);
        assertEquals(field[1][0], unit2);
        player.removeUnitField(unit2, 1, 0);
        assertNull(field[0][0]);
        assertNull(field[1][0]);
    }
    @Test
    void testIsBenchFull(){
        Unit[] bench = player.getUnitsOnBench();
        assertNotNull(bench);
        Unit unit = new Unit("Test Unit");
        assertFalse(player.isBenchFull());
        for (int i =0 ; i < bench.length; i++){
            assertNull(bench[i]);
            player.addUnitBench(unit);
            assertNotNull(bench[i]);
        }
        assertTrue(player.isBenchFull());
        bench[0] = null;
        assertFalse(player.isBenchFull());

    }


    @BeforeEach
    void setup(){
        player = new Player();
    }

}
