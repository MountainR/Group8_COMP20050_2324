package blackbox;
import java.util.ArrayList;
import java.util.List;

/**
 * RandomlySelect class provides a static randomly select method
 * randomlySelect(ArrayList list, int size).
 * It will randomly select size number of elements in list
 */
public class RandomlySelect<E> {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        // add
        list.add(24);
        list.add(636);
        list.add(241);
        list.add(1);
        list.add(8);
        list.add(28);
        list.add(64);
        list.add(72);
        list.add(9);

        ArrayList<Integer> random = randomlySelect(list, 5);
        System.out.println(random.toString());


    }

    /**
     * Randomly select some elements from an array list
     * @param list select elements from this list
     * @param size number of elements user wants to select
     * @return a list of randomly selected elements
     * @param <E> generic type
     */
    public static<E> ArrayList<Integer> randomlySelect(ArrayList<E> list, int size) {
        // validation check
        if (list.size() < size) throw new IllegalArgumentException("Out of range.");
        // Make an empty array list for elements and an empty array list for index
        ArrayList<E> randomElements = new ArrayList<E>();
        ArrayList<Integer> indexList = new ArrayList<>();

        // Generate 5 random indexes by math.Random
        for (int i=0; i<size; ++i) {
            // Since Math.random() returns a number between 0 and 1,
            // we need to expand it and give a type casting
            int index = (int) (Math.random() * list.size());

            // Check if the index is repeated, if not, add it to the list
            while (indexList.contains(index)) {
                index = (int) (Math.random() * list.size());
            }

            indexList.add(index);
        }

        //return the array list
        return indexList;
    }
}