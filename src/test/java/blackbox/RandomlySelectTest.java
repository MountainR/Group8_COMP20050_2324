package blackbox;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

 public class RandomlySelectTest {

    @Test
    public void testException(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        RandomlySelect<Integer> s = new RandomlySelect<Integer>();

        try{
            RandomlySelect.randomlySelect(list, 5);
        }
        catch(IllegalArgumentException e){

        }
    }

    @Test
    public void testSize(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        RandomlySelect<Integer> s = new RandomlySelect<Integer>();
        Assert.assertEquals(RandomlySelect.randomlySelect(list, 5).size(), 5);
    }

    @Test
    public void testRandom(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        RandomlySelect<Integer> s = new RandomlySelect<Integer>();
        ArrayList<Integer> list1 = RandomlySelect.randomlySelect(list, 5);
        ArrayList<Integer> list2 = RandomlySelect.randomlySelect(list, 5);

        assertNotEquals(list1, list2);
    }

}