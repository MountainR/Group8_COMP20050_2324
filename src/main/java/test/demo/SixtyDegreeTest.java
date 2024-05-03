/**
*    This class will calculate the ray path and return the result like hit, miss etc
    */
package test.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class SixtyDegreeTest {
    // direction
    public enum directions {southeast, southwest, west, northwest, northeast, east}

    ArrayList<Line> lines = new ArrayList<>();

    public SixtyDegreeTest() {
        addCors();
    }

    public ArrayList<Line> getLine() {
        return lines;
    }
    public static class HexagonCoor {
        private final int x, y, z, index;
        private final List<directions> exit;

        public HexagonCoor(int index, int x, int y, int z, List<directions> exit) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
            this.exit = exit;
        }

//        public void addExit(directions exit) {
//            this.exit.add(exit);
//        }


        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }
        public int getIndex() {
            return index;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
        public List<directions> getExit() {
            return exit;
        }

        public int compareTo(HexagonCoor coor2) {
            if (this.getX() == coor2.getX() &&
                    this.getY() == coor2.getY() &&
                    this.getZ() == coor2.getZ()
            )
            {
                return 0;
            }
            return -1;
        }

    }
    private ArrayList<HexagonCoor> coorList = new ArrayList<>();

    public int returnIndex(int x, int y, int z) {
        for(HexagonCoor coor : coorList) {
            if (coor.getX() == x && coor.getY() == y && coor.getZ() == z) {
                return coor.getIndex();
            }
        }
        return -1;
    }

    public ArrayList<HexagonCoor> getCoorList() {
        return coorList;
    }

    public void addCors() {
        coorList.add(new HexagonCoor(0, -4, 4, 0, Arrays.asList(directions.west, directions.northwest, directions.northeast))); // 0
        coorList.add(new HexagonCoor(1, -3, 4, -1, Arrays.asList(directions.northwest, directions.northeast))); // 1
        coorList.add(new HexagonCoor(2, -2, 4, -2, Arrays.asList(directions.northwest, directions.northeast))); // 2
        coorList.add(new HexagonCoor(3, -1, 4, -3, Arrays.asList(directions.northwest, directions.northeast))); // 3
        coorList.add(new HexagonCoor(4, 0, 4, -4, Arrays.asList(directions.northwest, directions.northeast, directions.east))); // 4

        coorList.add(new HexagonCoor(5, -4, 3, 1, Arrays.asList(directions.west, directions.northwest))); // 5
        coorList.add(new HexagonCoor(6, -3, 3, 0, null)); // 6
        coorList.add(new HexagonCoor(7, -2, 3, -1, null)); // 7
        coorList.add(new HexagonCoor(8, -1, 3, -2, null)); // 8
        coorList.add(new HexagonCoor(9, 0, 3, -3, null)); // 9
        coorList.add(new HexagonCoor(10, 1, 3, -4, Arrays.asList(directions.northeast, directions.east))); // 10

        coorList.add(new HexagonCoor(11, -4, 2, 2, Arrays.asList(directions.west, directions.northwest))); // 11
        coorList.add(new HexagonCoor(12, -3, 2, 1, null)); // 12
        coorList.add(new HexagonCoor(13, -2, 2, 0, null)); // 13
        coorList.add(new HexagonCoor(14, -1, 2, -1, null)); // 14
        coorList.add(new HexagonCoor(15, 0, 2, -2, null)); // 15
        coorList.add(new HexagonCoor(16, 1, 2, -3, null)); // 16
        coorList.add(new HexagonCoor(17, 2, 2, -4, Arrays.asList(directions.northeast, directions.east))); // 17

        coorList.add(new HexagonCoor(18, -4, 1, 3, Arrays.asList(directions.west, directions.northwest))); // 18
        coorList.add(new HexagonCoor(19, -3, 1, 2, null)); // 19
        coorList.add(new HexagonCoor(20, -2, 1, 1, null)); // 20
        coorList.add(new HexagonCoor(21, -1, 1, 0, null)); // 21
        coorList.add(new HexagonCoor(22, 0, 1, -1, null)); // 22
        coorList.add(new HexagonCoor(23, 1, 1, -2, null)); // 23
        coorList.add(new HexagonCoor(24, 2, 1, -3, null)); // 24
        coorList.add(new HexagonCoor(25, 3, 1, -4, Arrays.asList(directions.northeast, directions.east))); // 25

        coorList.add(new HexagonCoor(26, -4, 0, 4, Arrays.asList(directions.southwest, directions.west, directions.northwest))); // 26
        coorList.add(new HexagonCoor(27, -3, 0, 3, null)); // 27
        coorList.add(new HexagonCoor(28, -2, 0, 2, null)); // 28
        coorList.add(new HexagonCoor(29, -1, 0, 1, null)); // 29
        coorList.add(new HexagonCoor(30, 0, 0, 0, null)); // 30
        coorList.add(new HexagonCoor(31, 1, 0, -1, null)); // 31
        coorList.add(new HexagonCoor(32, 2, 0, -2, null)); // 32
        coorList.add(new HexagonCoor(33, 3, 0, -3, null)); // 33
        coorList.add(new HexagonCoor(34, 4, 0, -4, Arrays.asList(directions.northeast, directions.east, directions.southeast))); // 34

        coorList.add(new HexagonCoor(35, -3, -1, 4, Arrays.asList(directions.southwest, directions.west))); // 35
        coorList.add(new HexagonCoor(36, -2, -1, 3, null)); // 36
        coorList.add(new HexagonCoor(37, -1, -1, 2, null)); // 37
        coorList.add(new HexagonCoor(38, 0, -1, 1, null)); // 38
        coorList.add(new HexagonCoor(39, 1, -1, 0, null)); // 39
        coorList.add(new HexagonCoor(40, 2, -1, -1, null)); // 40
        coorList.add(new HexagonCoor(41, 3, -1, -2, null)); // 41
        coorList.add(new HexagonCoor(42, 4, -1, -3, Arrays.asList(directions.east, directions.southeast))); // 42

        coorList.add(new HexagonCoor(43, -2, -2, 4, Arrays.asList(directions.southwest, directions.west))); // 43
        coorList.add(new HexagonCoor(44, -1, -2, 3, null)); // 44
        coorList.add(new HexagonCoor(45, 0, -2, 2, null)); // 45
        coorList.add(new HexagonCoor(46, 1, -2, 1, null)); // 46
        coorList.add(new HexagonCoor(47, 2, -2, 0, null)); // 47
        coorList.add(new HexagonCoor(48, 3, -2, -1, null)); // 48
        coorList.add(new HexagonCoor(49, 4, -2, -2, Arrays.asList(directions.east, directions.southeast))); // 49

        coorList.add(new HexagonCoor(50, -1, -3, 4, Arrays.asList(directions.southwest, directions.west))); // 50
        coorList.add(new HexagonCoor(51, 0, -3, 3, null)); // 51
        coorList.add(new HexagonCoor(52, 1, -3, 2, null)); // 52
        coorList.add(new HexagonCoor(53, 2, -3, 1, null)); // 53
        coorList.add(new HexagonCoor(54, 3, -3, 0, null)); // 54
        coorList.add(new HexagonCoor(55, 4, -3, -1, Arrays.asList(directions.east, directions.southeast))); // 55

        coorList.add(new HexagonCoor(56, 0, -4, 4, Arrays.asList(directions.southeast, directions.southwest, directions.west))); // 56
        coorList.add(new HexagonCoor(57, 1, -4, 3, Arrays.asList(directions.southeast, directions.southwest))); // 57
        coorList.add(new HexagonCoor(58, 2, -4, 2, Arrays.asList(directions.southeast, directions.southwest))); // 58
        coorList.add(new HexagonCoor(59, 3, -4, 1, Arrays.asList(directions.southeast, directions.southwest))); // 59
        coorList.add(new HexagonCoor(60, 4, -4, 0, Arrays.asList(directions.east, directions.southeast, directions.southwest))); // 60














    }

    public boolean checkLabelPlusOne(LabelMap startLabel, ArrayList<HexagonCoor> atoms)
    {
        LabelMap checkLabel = new LabelMap(startLabel.getLabelNum() + 1);
        HexagonCoor checkHexagon = getCoorList().get(checkLabel.getIndex());

        if (containsCoor(checkHexagon, atoms))
        {
            //System.out.println("Ray was reflected");
            return true;
        }
        else return false;
    }

    public boolean checkLabelMinusOne(LabelMap startLabel, ArrayList<HexagonCoor> atoms)
    {
        LabelMap checkLabel = new LabelMap(startLabel.getLabelNum() - 1);
        HexagonCoor checkHexagon = getCoorList().get(checkLabel.getIndex());

        if (containsCoor(checkHexagon, atoms))
        {
            //System.out.println("Ray was reflected");
            return true;
        }
        else return false;
    }

    int rayDepth = 0;
    public LabelMap moveRay(HexagonCoor start, ArrayList<HexagonCoor> atoms, directions direction) {
        HexagonCoor nextCoor = start;
        int nextIndex;
        HexagonCoor position1=null, position2=null, position3=null;
        int p1Index=-1, p2Index=-1, p3Index=-1;

        if (rayDepth == 0) {
            LabelMap startLabel = new LabelMap(nextCoor.index, direction.ordinal());
            //check if hexagon at start label contains an atom
            if (containsCoor(start, atoms)) {
                //System.out.println("Meet at P1 and hit");
                return null;
            } else
            {
                if (startLabel.getLabelNum() > 1 && startLabel.getLabelNum() < 10) //if ray is entering labels 1-10
                {
                    if (startLabel.getLabelNum() % 2 == 0) //if even
                    {
                        if (checkLabelPlusOne(startLabel, atoms)) { return startLabel; } //check label number + 1 for atom
                    }
                    else if (startLabel.getLabelNum() % 2 == 1) //if odd
                    {
                        if (checkLabelMinusOne(startLabel, atoms)) { return startLabel; } //check label number - 1 for atom
                    }

                }
                else if (startLabel.getLabelNum() > 10 && startLabel.getLabelNum() < 19)
                {
                    if (startLabel.getLabelNum() % 2 == 0) //if even
                    {
                        if (checkLabelMinusOne(startLabel, atoms)) { return startLabel; } //check label number + 1 for atom
                    }
                    else if (startLabel.getLabelNum() % 2 == 1) //if odd
                    {
                        if (checkLabelPlusOne(startLabel, atoms)) { return startLabel; } //check label number - 1 for atom
                    }
                }
                else if (startLabel.getLabelNum() > 19 && startLabel.getLabelNum() < 28)
                {
                    if (startLabel.getLabelNum() % 2 == 0) //if even
                    {
                        if (checkLabelPlusOne(startLabel, atoms)) { return startLabel; } //check label number + 1 for atom
                    }
                    else if (startLabel.getLabelNum() % 2 == 1) //if odd
                    {
                        if (checkLabelMinusOne(startLabel, atoms)) { return startLabel; } //check label number - 1 for atom
                    }
                }
                else if (startLabel.getLabelNum() > 28 && startLabel.getLabelNum() < 37)
                {
                    if (startLabel.getLabelNum() % 2 == 0) //if even
                    {
                        if (checkLabelMinusOne(startLabel, atoms)) { return startLabel; } //check label number + 1 for atom
                    }
                    else if (startLabel.getLabelNum() % 2 == 1) //if odd
                    {
                        if (checkLabelPlusOne(startLabel, atoms)) { return startLabel; } //check label number - 1 for atom
                    }
                }
                else if (startLabel.getLabelNum() > 37 && startLabel.getLabelNum() < 46)
                {
                    if (startLabel.getLabelNum() % 2 == 0) //if even
                    {
                        if (checkLabelPlusOne(startLabel, atoms)) { return startLabel; } //check label number + 1 for atom
                    }
                    else if (startLabel.getLabelNum() % 2 == 1) //if odd
                    {
                        if (checkLabelMinusOne(startLabel, atoms)) { return startLabel; } //check label number - 1 for atom
                    }
                }
                else if (startLabel.getLabelNum() > 46 && startLabel.getLabelNum() <= 54)
                {
                    if (startLabel.getLabelNum() % 2 == 0) //if even
                    {
                        if (checkLabelMinusOne(startLabel, atoms)) { return startLabel; } //check label number + 1 for atom
                    }
                    else if (startLabel.getLabelNum() % 2 == 1) //if odd
                    {
                        if (checkLabelPlusOne(startLabel, atoms)) { return startLabel; } //check label number - 1 for atom
                    }
                }
            }


        }

        switch(direction) {
            case southeast:
                p1Index = returnIndex(start.getX() + 1, start.getY() - 1, start.z);
                p2Index = returnIndex(start.getX() + 1, start.getY(), start.z - 1);
                p3Index = returnIndex(start.getX(), start.getY() - 1, start.z + 1);
                break;
            case southwest:
                p1Index = returnIndex(start.getX(), start.getY() - 1, start.z+1);
                p2Index = returnIndex(start.getX() + 1, start.getY() - 1, start.z);
                p3Index = returnIndex(start.getX()-1, start.getY(), start.z + 1);
                break;
            case east:
                p1Index = returnIndex(start.getX() + 1, start.getY(), start.getZ() - 1);
                p2Index = returnIndex(start.getX(), start.getY() + 1, start.getZ() - 1);
                p3Index = returnIndex(start.getX() + 1, start.getY() - 1, start.getZ());
                break;
            case northwest:
                p1Index = returnIndex(start.getX() - 1, start.getY() + 1, start.getZ());
                p2Index = returnIndex(start.getX() - 1, start.getY(), start.getZ() + 1);
                p3Index = returnIndex(start.getX(), start.getY() + 1, start.getZ() - 1);
                break;
            case west:
                p1Index = returnIndex(start.getX() - 1, start.getY() , start.getZ() + 1);
                p2Index = returnIndex(start.getX(), start.getY() - 1, start.getZ() + 1);
                p3Index = returnIndex(start.getX() - 1, start.getY() + 1, start.getZ());
                break;
            case northeast:
                p1Index = returnIndex(start.getX(), start.getY() + 1, start.getZ() - 1);
                p2Index = returnIndex(start.getX() - 1, start.getY() + 1, start.getZ());
                p3Index = returnIndex(start.getX() + 1, start.getY() , start.getZ() - 1);
                break;
        }


        if (p1Index != -1) {
            position1 = coorList.get(p1Index);
        }
        if (p2Index != -1) {
            position2 = coorList.get(p2Index);
        }
        if (p3Index != -1) {
            position3 = coorList.get(p3Index);
        }

        // get nextCoor by its direction. Miss case
        switch (direction) {
            case southeast:
                nextIndex = returnIndex(start.getX() + 1, start.getY() - 1, start.getZ());
                nextCoor = coorList.get(nextIndex);
                break;
            case southwest:
                nextIndex = returnIndex(start.getX(), start.getY() - 1, start.getZ()+1);
                nextCoor = coorList.get(nextIndex);
                break;
            case west:
                nextIndex = returnIndex(start.getX() - 1, start.getY(), start.getZ()+1);
                nextCoor = coorList.get(nextIndex);
                break;
            case northwest:
                nextIndex = returnIndex(start.getX() - 1, start.getY()+1, start.getZ());
                nextCoor = coorList.get(nextIndex);
                break;
            case northeast:
                nextIndex = returnIndex(start.getX(), start.getY()+1, start.getZ()-1);
                nextCoor = coorList.get(nextIndex);
                break;
            case east:
                nextIndex = returnIndex(start.getX() + 1, start.getY(), start.getZ() - 1);
                nextCoor = coorList.get(nextIndex);
                break;

        }

        // System.out.println("P1: " + position1.toString() + " P2: " + position2.toString());

        // --------------------- if position1 had atom and position 2 has atom ----------------
        // System.out.println(containsCoor(position1, atoms));
        // System.out.println(containsCoor(position2, atoms));
        //reflect
        if (position2 != null && position3 != null && containsCoor(position2,atoms) && containsCoor(position3,atoms) ||
                position1 != null && position2 != null && position3 != null && containsCoor(position1, atoms) && containsCoor(position2,atoms) && containsCoor(position3,atoms))
        {
            switch (direction)
            {
                case southeast:
                    nextIndex = returnIndex(start.getX() - 1, start.getY() + 1, start.z);
                    direction = directions.northwest;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case southwest:
                    nextIndex = returnIndex(start.getX(), start.getY() + 1, start.z - 1);
                    direction = directions.northeast;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case west:
                    nextIndex = returnIndex(start.getX() + 1, start.getY(), start.z - 1);
                    direction = directions.east;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northwest:
                    nextIndex = returnIndex(start.getX() + 1, start.getY() - 1, start.z);
                    direction = directions.southeast;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northeast:
                    nextIndex = returnIndex(start.getX(), start.getY() - 1, start.z + 1);
                    direction = directions.southwest;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case east:
                    nextIndex = returnIndex(start.getX() - 1, start.getY(), start.z + 1);
                    direction = directions.west;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
            }
            //System.out.println("Ray was reflected");
        }
        else if (position1 != null && position2 != null && containsCoor(position1, atoms) && containsCoor(position2, atoms)) {
            // next position change to (x-1, y, z+1)
            //System.out.println("Meet atoms at P1 and P2, turn 60 degrees.");
            switch (direction) {
                case southeast:
                    nextIndex = returnIndex(start.getX() - 1, start.getY(), start.getZ() + 1);
                    direction = directions.west;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case southwest:
                    nextIndex = returnIndex(start.getX()-1, start.getY()+1, start.getZ());
                    direction = directions.northwest;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case east:
                    nextIndex = returnIndex(start.getX(), start.getY() - 1, start.getZ() + 1);
                    direction = directions.southwest;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northwest:
                    nextIndex = returnIndex(start.getX() + 1, start.getY(), start.getZ() - 1);
                    direction = directions.east;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case west:
                    nextIndex = returnIndex(start.getX(), start.getY() + 1, start.getZ() - 1);
                    direction = directions.northeast;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northeast:
                    nextIndex = returnIndex(start.getX() + 1, start.getY() - 1, start.getZ());
                    direction = directions.southeast;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
            }

        }

        else if (position1 != null && position3 != null && containsCoor(position1, atoms) && containsCoor(position3, atoms))
        {
            //System.out.println("Meet atoms at P1 and P3, turn 60 degrees.");
            switch (direction)
            {
                case southeast:
                    nextIndex = returnIndex(start.getX(), start.getY()+1, start.getZ()-1);
                    direction = directions.northeast;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case southwest:
                    nextIndex = returnIndex(start.getX()+1, start.getY(), start.getZ()-1);
                    direction = directions.east;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case east:
                    nextIndex = returnIndex(start.getX() - 1, start.getY() + 1, start.getZ());
                    direction = directions.northwest;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northwest:
                    nextIndex = returnIndex(start.getX(), start.getY() - 1, start.getZ() + 1);
                    direction = directions.southwest;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case west:
                    nextIndex = returnIndex(start.getX() + 1, start.getY() - 1, start.getZ());
                    direction = directions.southeast;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northeast:
                    nextIndex = returnIndex(start.getX() - 1, start.getY(), start.getZ() + 1);
                    direction = directions.west;
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
            }
        }

        else if (position1 != null && containsCoor(position1, atoms)){
            switch (direction) {
                case southeast:
                    nextIndex = returnIndex(start.getX() + 1, start.getY() - 1, start.z);
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case southwest:
                    nextIndex = returnIndex(start.getX(), start.getY() - 1, start.z+1);
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case east:
                    nextIndex = returnIndex(start.getX() + 1, start.getY(), start.getZ() - 1);
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northwest:
                    nextIndex = returnIndex(start.getX() - 1, start.getY() + 1, start.getZ());
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case west:
                    nextIndex = returnIndex(start.getX() - 1, start.getY() , start.getZ() + 1);
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
                case northeast:
                    nextIndex = returnIndex(start.getX(), start.getY() + 1, start.getZ() - 1);
                    if (onEdge(start.getIndex()) && start.getExit().contains(direction)) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    break;
            }

            //System.out.println("Meet at P1 and hit");
            //System.out.println("Next coordinate: " + nextCoor.toString());
            return null;
        }

        else if (position2 != null && containsCoor(position2, atoms))
        {
            switch (direction)
            {
                case southeast:
                    nextIndex = returnIndex(start.getX(), start.getY()-1, start.getZ()+1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.southwest;
                    break;
                case southwest:
                    nextIndex = returnIndex(start.getX()-1, start.getY(), start.getZ()+1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.west;
                    break;
                case east:
                    nextIndex = returnIndex(start.getX() + 1, start.getY() - 1, start.getZ());
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.southeast;
                    break;
                case northwest:
                    nextIndex = returnIndex(start.getX(), start.getY() + 1, start.getZ() - 1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.northeast;
                    break;
                case west:
                    nextIndex = returnIndex(start.getX() - 1, start.getY() + 1, start.getZ());
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.northwest;
                    break;
                case northeast:
                    nextIndex = returnIndex(start.getX() + 1, start.getY(), start.getZ() - 1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.east;
                    break;
            }
            //System.out.println("120 degree deflection due to P2");
            //System.out.println("Next coordinate: " + nextCoor.toString());
        }

        //120 degree for P3
        else if (position3 != null && containsCoor(position3, atoms))
        {
            switch (direction)
            {
                case southeast:
                    nextIndex = returnIndex(start.getX() + 1, start.getY(), start.getZ()-1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.east;
                    break;
                case southwest:
                    nextIndex = returnIndex(start.getX()+1, start.getY()-1, start.getZ());
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.southeast;
                    break;
                case east:
                    nextIndex = returnIndex(start.getX() , start.getY() + 1, start.getZ()-1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.northeast;
                    break;
                case northwest:
                    nextIndex = returnIndex(start.getX()-1, start.getY(), start.getZ() + 1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.west;
                    break;
                case west:
                    nextIndex = returnIndex(start.getX() , start.getY() - 1, start.getZ()+1);
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.southwest;
                    break;
                case northeast:
                    nextIndex = returnIndex(start.getX() - 1, start.getY()+1, start.getZ());
                    if (onEdge(start.getIndex())) {nextCoor = start;}
                    else {nextCoor = coorList.get(nextIndex);}
                    direction = directions.northwest;
                    break;
            }
            //System.out.println("120 degree deflection due to P3");
            //System.out.println("Next coordinate: " + nextCoor.toString());
        }

        // Now we get start index and next index, let's draw lines!
        // first let's get the coor
        double startX = HelloController.realCoors[start.index][0];
        double startY = HelloController.realCoors[start.index][1];
        double endX = HelloController.realCoors[nextCoor.index][0];
        double endY = HelloController.realCoors[nextCoor.index][1];

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.CYAN);// change color of width
        line.setStrokeWidth(3); // make ray path thicker
        line.setVisible(false);
        lines.add(line);

        // add the line to the pane
//        HelloController.addLine(line);


        // not at edge
        // System.out.println("abs(nextCoor.getX()): "+abs(nextCoor.getX()));
        if (abs(nextCoor.getX()) + abs(nextCoor.getY()) + abs(nextCoor.getZ()) != 8
                || !nextCoor.getExit().contains(direction)) {
            //System.out.println("Next coordinate: " + nextCoor.toString());
            rayDepth++;
            return moveRay(nextCoor, atoms, direction);
        }
        else {
            //System.out.println("Reach an edge: " + nextCoor.toString());
            //System.out.println("Direction: " + direction.ordinal());
            LabelMap label = new LabelMap(nextCoor.index, direction.ordinal());
            //System.out.println("Label: " + label.getLabelNum() + "\nDirection: " + label.getDirection());
            rayDepth = 0;
            return label;
        }
    }

    public boolean onEdge(int currIndex) {
        List<Integer> edgeNums = Arrays.asList(0, 1, 2, 3, 4, 5, 11, 18, 26, 35, 43, 50, 56, 57, 58, 59, 60, 55, 49, 42, 34, 25,
                17, 10, 4);
        return edgeNums.contains(currIndex);
    }

    public boolean containsCoor(HexagonCoor coor, ArrayList<HexagonCoor> list) {
        for (HexagonCoor item : list) {
            if (coor.compareTo(item) == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SixtyDegreeTest test = new SixtyDegreeTest();
        int atom1 = 45;
        int atom2 = 37;
        int atom3 = 43;

        // generate atom array
        ArrayList<HexagonCoor> atoms = new ArrayList<>();
        // place 2 atoms at 13, 7
        atoms.add(test.coorList.get(atom1));
        atoms.add(test.coorList.get(atom2));
        atoms.add(test.coorList.get(atom3));
        //System.out.println("Atoms: " + atoms);

        // get a label
        int startHexagon = 57;
        directions direction = directions.northwest;


        // System.out.println(test.coorList.toString());

        // 2.Convert the label to a coordinate of hexagon (x, y, z), print it
        HexagonCoor startCoor = test.coorList.get(startHexagon);
        //System.out.println("Start: " + startCoor.getIndex());
        //System.out.println("Direction: " + direction);
        //System.out.println();

        // 3. Get the next hexagon coordinate, print it
        // since we move southwest, x+1, y-1, z


        test.moveRay(startCoor, atoms, direction);     // miss situation

    }
}
