package com.example.javafx_0216;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.javafx_0216.RandomlySelect.randomlySelect;
import static org.junit.jupiter.api.Assertions.*;

class RandomlySelectTest {
    ArrayList<Integer> list = new ArrayList<>();
    @BeforeEach
    public void init() {
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
    }

    /**
     * Test if the number of element we want is greater than the length of array
     */
    @Test
    void testOutOfRange() {
        try {
            ArrayList<Integer> random = randomlySelect(list, 100);
        }catch (IllegalArgumentException ex) {

        }
    }
    /**
     * Test whether the elements we select is repeated
     */
    @Test
    void testRepeated() {
        ArrayList<Integer> repeatedList = new ArrayList<>();
        repeatedList.add(1);
        repeatedList.add(1);
        repeatedList.add(1);
        repeatedList.add(1);

    }
}