package blackbox;

/**
 * Class that calculates the hexagon index and direction given a label, and vice versa.
 */
public class LabelMap {
    private int index;
    protected enum directions {southeast, southwest, west, northwest, northeast, east}

    private int labelNum;
    private RayMovement.directions direction;

    /**
     * Constructs a LabelMap given a hexagon index and a direction
     * @param hexIndex Index of a hexagon on the edge of the board
     * @param dir Direction of the ray
     */
    public LabelMap(int hexIndex, int dir) {
        index = hexIndex;
        direction = intToEnum(dir);

        if (direction == RayMovement.directions.southeast || direction == RayMovement.directions.northwest)
        {
            switch (index)
            {
                case 0 -> labelNum = 1;
                case 1 -> labelNum = 53;
                case 2 -> labelNum = 51;
                case 3 -> labelNum = 49;
                case 4 -> labelNum = 47;
                case 5 -> labelNum = 3;
                case 11 -> labelNum = 5;
                case 18 -> labelNum = 7;
                case 26 -> labelNum = 9;

                case 56 -> labelNum = 20;
                case 57 -> labelNum = 22;
                case 58 -> labelNum = 24;
                case 59 -> labelNum = 26;
                case 60 -> labelNum = 28;
                case 55 -> labelNum = 30;
                case 49 -> labelNum = 32;
                case 42 -> labelNum = 34;
                case 34 -> labelNum = 36;

                default -> throw new IllegalArgumentException("Hexagon is not on edge");
            }
        }
        else if (direction == RayMovement.directions.northeast || direction == RayMovement.directions.southwest)
        {
            switch (index)
            {
                case 0 -> labelNum = 54;
                case 1 -> labelNum = 52;
                case 2 -> labelNum = 50;
                case 3 -> labelNum = 48;
                case 4 -> labelNum = 46;
                case 10 -> labelNum = 44;
                case 17 -> labelNum = 42;
                case 25 -> labelNum = 40;
                case 34 -> labelNum = 38;

                case 26 -> labelNum = 11;
                case 35 -> labelNum = 13;
                case 43 -> labelNum = 15;
                case 50 -> labelNum = 17;
                case 56 -> labelNum = 19;
                case 57 -> labelNum = 21;
                case 58 -> labelNum = 23;
                case 59 -> labelNum = 25;
                case 60 -> labelNum = 27;
                default -> throw new IllegalArgumentException("Hexagon is not on edge");
            }
        }
        else if (direction == RayMovement.directions.east || direction == RayMovement.directions.west)
        {
            switch (index)
            {
                case 0 -> labelNum = 2;
                case 5 -> labelNum = 4;
                case 11 -> labelNum = 6;
                case 18 -> labelNum = 8;
                case 26 -> labelNum = 10;
                case 35 -> labelNum = 12;
                case 43 -> labelNum = 14;
                case 50 -> labelNum = 16;
                case 56 -> labelNum = 18;

                case 60 -> labelNum = 29;
                case 55 -> labelNum = 31;
                case 49 -> labelNum = 33;
                case 42 -> labelNum = 35;
                case 34 -> labelNum = 37;
                case 4 -> labelNum = 45;
                case 10 -> labelNum = 43;
                case 17 -> labelNum = 41;
                case 25 -> labelNum = 39;
                default -> throw new IllegalArgumentException("Hexagon is not on edge");
            }
        }

    }

    /**
     * Constructs a LabelMap given a label
     * @param label Number of an edge
     */
    public LabelMap(int label) {
        if (!(label >= 1 && label <= 54))
            throw new IllegalArgumentException("Edge number must be between 1 and 54");

        labelNum = label;
        //for top left edge
        if (labelNum <= 10) {
            if (labelNum % 2 == 1) {
                direction = RayMovement.directions.southeast;
            }
            else if (labelNum % 2 == 0) {
                direction = RayMovement.directions.east;
            }

            switch (labelNum) {
                case 1, 2 -> index = 0;
                case 3, 4 -> index = 5;
                case 5, 6 -> index = 11;
                case 7, 8 -> index = 18;
                case 9, 10 -> index = 26;
            }
        }

        //bottom left edge
        else if (labelNum <= 19) {
            if (labelNum % 2 == 1) { direction = RayMovement.directions.northeast; }
            else { direction = RayMovement.directions.east; }

            switch (labelNum)
            {
                case 11 -> index = 26;

                case 12,13 -> index = 35;

                case 14,15 -> index = 43;

                case 16,17 -> index = 50;

                case 18,19 -> index = 56;
            }
        }

        //bottom edge
        else if (labelNum <= 28)
        {
            if (labelNum % 2 == 1) { direction = RayMovement.directions.northeast; }
            else { direction = RayMovement.directions.northwest; }

            switch (labelNum)
            {
                case 20 -> index = 56;
                case 21,22 -> index = 57;
                case 23,24 -> index = 58;
                case 25, 26 -> index = 59;
                case 27, 28 -> index = 60;
            }
        }

        //bottom right edge
        else if (labelNum <= 37)
        {

            if (labelNum % 2 == 1) { direction = RayMovement.directions.west; }
            else { direction = RayMovement.directions.northwest; }

            switch (labelNum)
            {
                case 29 -> index = 60;
                case 30,31 -> index = 55;
                case 32, 33 -> index = 49;
                case 34, 35 -> index = 42;
                case 36,37 -> index = 34;
            }
        }

        //top right edge
        else if (labelNum <= 46) {
            if (labelNum % 2 == 1) { direction = RayMovement.directions.west; }
            else { direction = RayMovement.directions.southwest; }

            switch (labelNum)
            {
                case 38 -> index = 34;
                case 39,40 -> index = 25;
                case 41,42 -> index = 17;
                case 43,44 -> index = 10;
                case 45,46 -> index = 4;
            }
        }

        //top edge
        else if (labelNum <= 54) {
            if (labelNum % 2 == 1) { direction = RayMovement.directions.southeast; }
            else { direction = RayMovement.directions.southwest; }

            switch (labelNum)
            {
                case 47 -> index = 4;
                case 48,49 -> index = 3;
                case 50,51 -> index = 2;
                case 52,53 -> index = 1;
                case 54 -> index = 0;
            }
        }

    }

    public RayMovement.directions intToEnum(int direct) {
        RayMovement.directions dir;
        switch (direct)
        {
            case 0 -> dir = RayMovement.directions.southeast;
            case 1 -> dir = RayMovement.directions.southwest;
            case 2 -> dir = RayMovement.directions.west;
            case 3 -> dir = RayMovement.directions.northwest;
            case 4 -> dir = RayMovement.directions.northeast;
            case 5 -> dir = RayMovement.directions.east;
            default -> throw new IllegalArgumentException("Direction must be between 0 and 5");
        }

        return dir;
    }

    public int enumToInt(directions direct)
    {
        int n;
        switch (direct)
        {
            case southeast -> n = 0;
            case southwest -> n = 1;
            case west -> n = 2;
            case northwest -> n = 3;
            case northeast -> n = 4;
            case east -> n = 5;
            default -> throw new IllegalArgumentException("Invalid direction");
        }

        return n;
    }

    public int getIndex() {
        return index;
    }

    public int getLabelNum() {
        return labelNum;
    }

    public RayMovement.directions getDirection() {
        return direction;
    }
}
