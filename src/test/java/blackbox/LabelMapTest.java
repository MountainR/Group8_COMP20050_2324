package blackbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelMapTest {

    @Test
    void intToEnum() {
        LabelMap map = new LabelMap(20);
        assertEquals(map.intToEnum(0), RayMovement.directions.southeast);
        assertEquals(map.intToEnum(1), RayMovement.directions.southwest);
        assertEquals(map.intToEnum(2), RayMovement.directions.west);
        assertEquals(map.intToEnum(3), RayMovement.directions.northwest);
        assertEquals(map.intToEnum(4), RayMovement.directions.northeast);
        assertEquals(map.intToEnum(5), RayMovement.directions.east);

        try{
            map.intToEnum(-1);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void enumToInt() {
        LabelMap map = new LabelMap(20);
        assertEquals(map.enumToInt(LabelMap.directions.southeast), 0);
        assertEquals(map.enumToInt(LabelMap.directions.southwest), 1);
        assertEquals(map.enumToInt(LabelMap.directions.west), 2);
        assertEquals(map.enumToInt(LabelMap.directions.northwest), 3);
        assertEquals(map.enumToInt(LabelMap.directions.northeast), 4);
        assertEquals(map.enumToInt(LabelMap.directions.east), 5);


    }

    @Test
    void getIndex() {
        LabelMap map = new LabelMap(20);    //
        assertEquals(56, map.getIndex());
    }

    @Test
    void getLabelNum() {
        LabelMap map = new LabelMap(20);    //
        assertEquals(20, map.getLabelNum());

        try {
            LabelMap map1 = new LabelMap(100);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    void getDirection() {
        LabelMap map = new LabelMap(20);    //
        assertEquals(RayMovement.directions.northwest, map.getDirection());
    }
}