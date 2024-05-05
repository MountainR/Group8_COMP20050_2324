package blackbox;
/**
 * class that contains two instance variables, direction and hexagon coordinate of the next position of a ray
 * used in RayMovement class
 * defines a compareTo() method
 */
public class MovementMap {
    private RayMovement.directions directions;
    private RayMovement.HexagonCoor nextCoordinates;

    public MovementMap(RayMovement.directions direction, RayMovement.HexagonCoor nextCoor)
    {
        directions = direction;
        nextCoordinates = nextCoor;
    }

    public RayMovement.directions getDirection()
    {
        return directions;
    }

    public RayMovement.HexagonCoor getNextCoordinate()
    {
        return nextCoordinates;
    }

    public boolean compareTo(Object obj) {
        boolean equal = false;
        if (obj instanceof MovementMap) {
            if (this.directions == ((MovementMap) obj).directions
                    && this.nextCoordinates.compareTo(((MovementMap) obj).nextCoordinates)==0)
                equal = true;
        }
        return equal;
    }
}
