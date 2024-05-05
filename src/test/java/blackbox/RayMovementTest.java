package blackbox;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RayMovementTest {

    @Test
    public void testRayMiss() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray miss in (0, 0, 0) and direction is southeast, next coordinate is 39(1, -1, 0)
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.southeast, nextHexSE);
        assertTrue(move.rayMiss(RayMovement.directions.southeast, startHex).compareTo(nextMovementMapSE));

        // test southwest: if ray miss in (0, 0, 0) and direction is southwest, next coordinate is 38(0, -1, 1)
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(38, 0, -1, 1, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.southwest, nextHexSW);
        assertTrue(move.rayMiss(RayMovement.directions.southwest, startHex).compareTo(nextMovementMapSW));

        // test west: if ray miss in (0, 0, 0) and direction is west, next coordinate is 29(-1, 0, 1)
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.west, nextHexW);
        assertTrue(move.rayMiss(RayMovement.directions.west, startHex).compareTo(nextMovementMapW));

        // test northwest: if ray miss in (0, 0, 0) and direction is northwest, next coordinate is 21(-1, 1, 0)
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(21, -1, 1, 0, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.northwest, nextHexNW);
        assertTrue(move.rayMiss(RayMovement.directions.northwest, startHex).compareTo(nextMovementMapNW));

        // test northeast: if ray miss in (0, 0, 0) and direction is northeast, next coordinate is 22(0, 1, -1)
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.northeast, nextHexNE);
        assertTrue(move.rayMiss(RayMovement.directions.northeast, startHex).compareTo(nextMovementMapNE));

        // test east: if ray miss in (0, 0, 0) and direction is east, next coordinate is 31(1, 0, -1)
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.east, nextHexE);
        assertTrue(move.rayMiss(RayMovement.directions.east, startHex).compareTo(nextMovementMapE));

    }

    @Test
    public void rayReflect() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray reflect in (0, 0, 0) and direction is southeast, next coordinate is 21(-1, 1, 0) with direction northwest
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(21, -1, 1, 0, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.northwest, nextHexSE);
        assertTrue(move.rayReflect(startHex, RayMovement.directions.southeast).compareTo(nextMovementMapSE));

        // test southwest: if ray reflect in (0, 0, 0) and direction is southwest, next coordinate is 22(0, 1, -1), northeast
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.northeast, nextHexSW);
        assertTrue(move.rayReflect(startHex, RayMovement.directions.southwest).compareTo(nextMovementMapSW));

        // test west: if ray reflect in (0, 0, 0) and direction is west, next coordinate is 31(1, 0, -1), east
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.east, nextHexW);
        assertTrue(move.rayReflect(startHex, RayMovement.directions.west).compareTo(nextMovementMapW));

        // test northwest: if ray reflect in (0, 0, 0) and direction is northwest, next coordinate is 39(1, -1, 0), southeast
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.southeast, nextHexNW);
        assertTrue(move.rayReflect(startHex, RayMovement.directions.northwest).compareTo(nextMovementMapNW));

        // test northeast: if ray reflect in (0, 0, 0) and direction is northeast, next coordinate is 38(0, -1, 1), southwest
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(38, 0, -1, 1, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.southwest, nextHexNE);
        assertTrue(move.rayReflect(startHex, RayMovement.directions.northeast).compareTo(nextMovementMapNE));

        // test east: if ray reflect in (0, 0, 0) and direction is east, next coordinate is 29(-1, 0, 1), west
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.west, nextHexE);
        assertTrue(move.rayReflect(startHex, RayMovement.directions.east).compareTo(nextMovementMapE));

    }

    @Test
    public void raySixtyDegreeCaseOne() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray turn 60 degree in (0, 0, 0) and direction is southeast, next coordinate is 20(-1, 0, 1) with direction west
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.west, nextHexSE);
        assertTrue(move.raySixtyDegreeCaseOne(RayMovement.directions.southeast, startHex).compareTo(nextMovementMapSE));

        // test southwest: if ray turn 60 degree in (0, 0, 0) and direction is southwest, next coordinate is 21(-1, 1, 0), northwest
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(21, -1, 1, 0, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.northwest, nextHexSW);
        assertTrue(move.raySixtyDegreeCaseOne(RayMovement.directions.southwest, startHex).compareTo(nextMovementMapSW));

        // test west: if ray turn 60 degree in (0, 0, 0) and direction is west, next coordinate is 22(0, 1, -1), northeast
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.northeast, nextHexW);
        assertTrue(move.raySixtyDegreeCaseOne(RayMovement.directions.west, startHex).compareTo(nextMovementMapW));

        // test northwest: if ray turn 60 degree in (0, 0, 0) and direction is northwest, next coordinate is 31(1, 0, -1), east
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.east, nextHexNW);
        assertTrue(move.raySixtyDegreeCaseOne(RayMovement.directions.northwest, startHex).compareTo(nextMovementMapNW));

        // test northeast: if ray turn 60 degree in (0, 0, 0) and direction is northeast, next coordinate is 39(1, -1, 0), southeast
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.southeast, nextHexNE);
        assertTrue(move.raySixtyDegreeCaseOne(RayMovement.directions.northeast, startHex).compareTo(nextMovementMapNE));

        // test east: if ray turn 60 degree in (0, 0, 0) and direction is east, next coordinate is 38(0, -1, 1), southwest
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(38, 0, -1, 1, null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.southwest, nextHexE);
        assertTrue(move.raySixtyDegreeCaseOne(RayMovement.directions.east, startHex).compareTo(nextMovementMapE));

    }

    @Test
    public void raySixtyDegreeCaseTwo() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray turn 60 degree in (0, 0, 0) and direction is southeast, next coordinate is 22(0, 1, -1), northeast
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.northeast, nextHexSE);
        assertTrue(move.raySixtyDegreeCaseTwo(RayMovement.directions.southeast, startHex).compareTo(nextMovementMapSE));

        // test southwest: if ray turn 60 degree in (0, 0, 0) and direction is southwest, next coordinate is 31(1, 0, -1), east
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.east, nextHexSW);
        assertTrue(move.raySixtyDegreeCaseTwo(RayMovement.directions.southwest, startHex).compareTo(nextMovementMapSW));

        // test west: if ray turn 60 degree in (0, 0, 0) and direction is west, next coordinate is 39(1, -1, 0), southeast
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.southeast, nextHexW);
        assertTrue(move.raySixtyDegreeCaseTwo(RayMovement.directions.west, startHex).compareTo(nextMovementMapW));

        // test northwest: if ray turn 60 degree in (0, 0, 0) and direction is northwest, next coordinate is 38(0, -1, 1), southwest
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(38, 0, -1, 1, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.southwest, nextHexNW);
        assertTrue(move.raySixtyDegreeCaseTwo(RayMovement.directions.northwest, startHex).compareTo(nextMovementMapNW));

        // test northeast: if ray turn 60 degree in (0, 0, 0) and direction is northeast, next coordinate is 29(-1, 0, 1), west
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.west, nextHexNE);
        assertTrue(move.raySixtyDegreeCaseTwo(RayMovement.directions.northeast, startHex).compareTo(nextMovementMapNE));

        // test east: if ray turn 60 degree in (0, 0, 0) and direction is east, next coordinate is 21(-1, 1, 0), northwest
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(21, -1, 1, 0,  null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.northwest, nextHexE);
        assertTrue(move.raySixtyDegreeCaseTwo(RayMovement.directions.east, startHex).compareTo(nextMovementMapE));

    }

    @Test
    public void testRay120degreeCaseOne() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray miss in (0, 0, 0) and direction is southeast, next coordinate is 38(0, -1, 1)
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(38, 0, -1, 1, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.southwest, nextHexSE);
        assertTrue(move.ray120DegreeCaseOne(RayMovement.directions.southeast, startHex).compareTo(nextMovementMapSE));

        // test southwest: if ray miss in (0, 0, 0) and direction is southwest, next coordinate is 29(-1, 0, 1)
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.west, nextHexSW);
        assertTrue(move.ray120DegreeCaseOne(RayMovement.directions.southwest, startHex).compareTo(nextMovementMapSW));

        // test west: if ray miss in (0, 0, 0) and direction is west, next coordinate is 21(-1, 1, 0)
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(21, -1, 1, 0, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.northwest, nextHexW);
        assertTrue(move.ray120DegreeCaseOne(RayMovement.directions.west, startHex).compareTo(nextMovementMapW));

        // test northwest: if ray miss in (0, 0, 0) and direction is northwest, next coordinate is 22(0, 1, -1)
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.northeast, nextHexNW);
        assertTrue(move.ray120DegreeCaseOne(RayMovement.directions.northwest, startHex).compareTo(nextMovementMapNW));

        // test northeast: if ray miss in (0, 0, 0) and direction is northeast, next coordinate is 31(1, 0, -1)
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.east, nextHexNE);
        assertTrue(move.ray120DegreeCaseOne(RayMovement.directions.northeast, startHex).compareTo(nextMovementMapNE));

        // test east: if ray miss in (0, 0, 0) and direction is east, next coordinate is 39(1, -1, 0)
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.southeast, nextHexE);
        assertTrue(move.ray120DegreeCaseOne(RayMovement.directions.east, startHex).compareTo(nextMovementMapE));

    }

    @Test
    public void testRay120degreeCaseTwo() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray miss in (0, 0, 0) and direction is southeast, next coordinate is 31(1, 0, -1)
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.east, nextHexSE);
        assertTrue(move.ray120DegreeCaseTwo(RayMovement.directions.southeast, startHex).compareTo(nextMovementMapSE));

        // test southwest: if ray miss in (0, 0, 0) and direction is southwest, next coordinate is 39(1, -1, 0)
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.southeast, nextHexSW);
        assertTrue(move.ray120DegreeCaseTwo(RayMovement.directions.southwest, startHex).compareTo(nextMovementMapSW));

        // test west: if ray miss in (0, 0, 0) and direction is west, next coordinate is 38(0, -1, 1)
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(38, 0, -1, +1, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.southwest, nextHexW);
        assertTrue(move.ray120DegreeCaseTwo(RayMovement.directions.west, startHex).compareTo(nextMovementMapW));

        // test northwest: if ray miss in (0, 0, 0) and direction is northwest, next coordinate is 29(-1, 0, 1)
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.west, nextHexNW);
        assertTrue(move.ray120DegreeCaseTwo(RayMovement.directions.northwest, startHex).compareTo(nextMovementMapNW));

        // test northeast: if ray miss in (0, 0, 0) and direction is northeast, next coordinate is 21(-1, 1, 0)
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(21, -1, 1, 0, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.northwest, nextHexNE);
        assertTrue(move.ray120DegreeCaseTwo(RayMovement.directions.northeast, startHex).compareTo(nextMovementMapNE));

        // test east: if ray miss in (0, 0, 0) and direction is east, next coordinate is 22(0, 1, -1)
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.northeast, nextHexE);
        assertTrue(move.ray120DegreeCaseTwo(RayMovement.directions.east, startHex).compareTo(nextMovementMapE));

    }

    @Test
    public void testRayHit() {
        RayMovement move = new RayMovement();
        // start form the center hexagon(number 30, (0, 0, 0))
        RayMovement.HexagonCoor startHex = new RayMovement.HexagonCoor(30, 0, 0, 0, null);

        // test southeast: if ray miss in (0, 0, 0) and direction is southeast, next coordinate is 39(1, -1, 0)
        RayMovement.HexagonCoor nextHexSE = new RayMovement.HexagonCoor(39, 1, -1, 0, null);
        MovementMap nextMovementMapSE = new MovementMap(RayMovement.directions.southeast, nextHexSE);
        assertTrue(move.rayHit(RayMovement.directions.southeast, startHex).compareTo(nextMovementMapSE));

        // test southwest: if ray miss in (0, 0, 0) and direction is southwest, next coordinate is 38(0, -1, 1)
        RayMovement.HexagonCoor nextHexSW = new RayMovement.HexagonCoor(38, 0, -1, 1, null);
        MovementMap nextMovementMapSW = new MovementMap(RayMovement.directions.southwest, nextHexSW);
        assertTrue(move.rayHit(RayMovement.directions.southwest, startHex).compareTo(nextMovementMapSW));

        // test west: if ray miss in (0, 0, 0) and direction is west, next coordinate is 29(-1, 0, 1)
        RayMovement.HexagonCoor nextHexW = new RayMovement.HexagonCoor(29, -1, 0, 1, null);
        MovementMap nextMovementMapW = new MovementMap(RayMovement.directions.west, nextHexW);
        assertTrue(move.rayMiss(RayMovement.directions.west, startHex).compareTo(nextMovementMapW));

        // test northwest: if ray miss in (0, 0, 0) and direction is northwest, next coordinate is 21(-1, 1, 0)
        RayMovement.HexagonCoor nextHexNW = new RayMovement.HexagonCoor(21, -1, 1, 0, null);
        MovementMap nextMovementMapNW = new MovementMap(RayMovement.directions.northwest, nextHexNW);
        assertTrue(move.rayHit(RayMovement.directions.northwest, startHex).compareTo(nextMovementMapNW));

        // test northeast: if ray miss in (0, 0, 0) and direction is northeast, next coordinate is 22(0, 1, -1)
        RayMovement.HexagonCoor nextHexNE = new RayMovement.HexagonCoor(22, 0, 1, -1, null);
        MovementMap nextMovementMapNE = new MovementMap(RayMovement.directions.northeast, nextHexNE);
        assertTrue(move.rayHit(RayMovement.directions.northeast, startHex).compareTo(nextMovementMapNE));

        // test east: if ray miss in (0, 0, 0) and direction is east, next coordinate is 31(1, 0, -1)
        RayMovement.HexagonCoor nextHexE = new RayMovement.HexagonCoor(31, 1, 0, -1, null);
        MovementMap nextMovementMapE = new MovementMap(RayMovement.directions.east, nextHexE);
        assertTrue(move.rayHit(RayMovement.directions.east, startHex).compareTo(nextMovementMapE));


    }


}