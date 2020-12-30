package edu.touro.cs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyLinkedListTest {

    private MyLinkedList<String> mll = new MyLinkedList<>();


    @org.junit.jupiter.api.Test
    void size() {
        mll.add("");
        mll.add("");
        mll.add("");
        mll.add("");
        assertEquals(4, mll.size());

    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        mll.clear();
        assertTrue(mll.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void containsTrue() {

        mll.add("A");
        assertEquals(true, mll.contains("A"));
    }

    @org.junit.jupiter.api.Test
    void containsFalse() {
        mll.clear();
        assertEquals(false, mll.contains("A"));
    }

    @org.junit.jupiter.api.Test
    void add1() {
        this.mll.add("A");
        assertEquals(1, mll.size());
    }


    @org.junit.jupiter.api.Test
    void get() {
        mll.add("A");
        mll.add("B");
        //assertEquals("A", mll.get(0));
        assertEquals("B", mll.get(1));
    }

    @org.junit.jupiter.api.Test
    void set() {
        mll.add("A");
        mll.add("B");
        String oldVal = mll.set(0, "Q");
        assertEquals("Q", mll.get(0));
        assertEquals("B", mll.get(1));
        assertEquals("A", oldVal);
    }

    @org.junit.jupiter.api.Test
    void remove() {
        mll.add("A");
        mll.add("B");
        String oldVal = mll.remove(0);
        assertEquals("A", oldVal);
        assertEquals("B", mll.get(0));
    }


    @org.junit.jupiter.api.Test
    void remove2() {
        mll.add("A");
        mll.add("B");
        mll.add("C");

        String oldVal = mll.remove(1);
        assertEquals("B", oldVal);
        assertEquals("C", mll.get(1));
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        mll.add("A");
        mll.add("B");
        mll.add("C");
        Object[] toArr = mll.toArray();
        assertEquals(toArr[0], "A");
        assertEquals(toArr[1], "B");
        assertEquals(toArr[2], "C");
        assertEquals(3, toArr.length);
    }

    @org.junit.jupiter.api.Test
    void toArray2() {
        mll.add("A");
        mll.add("B");
        mll.add("C");
        String[] toArr2 = new String[5];
        mll.toArray(toArr2);
        assertEquals(toArr2[0], "A");
        assertEquals(toArr2[1], "B");
        assertEquals(toArr2[2], "C");
        assertNull(toArr2[3]);
    }

    @org.junit.jupiter.api.Test
    void toArr2NotEnuf() {
        mll.add("A");
        mll.add("B");
        mll.add("C");
        String[] notEnuf = new String[2];
        String[] newArr;
        newArr = mll.toArray(notEnuf);
        assertEquals(newArr[1], "B");
    }

    @org.junit.jupiter.api.Test
    void iteration() {
        this.mll.add("A");
        this.mll.add("Q");

        String concat = "";
        for (String s : mll) {
            concat += s;
        }
        assertEquals("AQ", concat);
    }

    @org.junit.jupiter.api.Test
    void iterationAndRemove() {
        this.mll.add("A");
        this.mll.add("Q");

        Iterator<String> it = mll.iterator();
        it.next(); //A
        it.remove(); // remove A

        String concat = "";
        for (it = mll.iterator(); it.hasNext(); ) {
            String s = it.next();
            concat += s;
        }
        assertEquals("Q", concat);
        assertEquals(1, mll.size());
    }

    @org.junit.jupiter.api.Test
    void generic() {
        MyGenericArrayList<String> mll = new MyGenericArrayList();


    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        mll.add("A");
        mll.add("B");
        mll.add("C");
        assertEquals(0, mll.indexOf("A"));
        assertEquals(1, mll.indexOf("B"));
        assertEquals(2, mll.indexOf("C"));

    }

    @org.junit.jupiter.api.Test
    void lastIndexOf() {
        mll.add("C");
        mll.add("B");
        mll.add("C");
        mll.add("A");
        assertEquals(2, mll.lastIndexOf("C"));

    }

    @org.junit.jupiter.api.Test
    void addAllTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        mll.addAll(strings);
        assertEquals(0, mll.indexOf("A"));
        assertEquals(1, mll.indexOf("B"));
        assertEquals(2, mll.indexOf("C"));

    }

    void addAllIndexTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        mll.add("X");
        mll.add("Y");
        mll.add("Z");
        mll.addAll(1, strings);
        assertEquals("A", mll.get(1));
        assertEquals("B", mll.get(2));
        assertEquals("C", mll.get(3));
        assertEquals("Y", mll.get(4));
        assertEquals("Z", mll.get(5));


    }

    @org.junit.jupiter.api.Test
    void containsAllTrue() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        mll.add("A");
        mll.add("B");
        mll.add("C");
        mll.add("D");
        assertTrue(mll.containsAll(strings));

    }

    @org.junit.jupiter.api.Test
    void containsAllFalse() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        mll.add("A");
        mll.add("B");
        mll.add("D");
        assertFalse(mll.containsAll(strings));
    }

    @org.junit.jupiter.api.Test
    void removeAllTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        mll.add("B");
        mll.add("Z");
        mll.add("V");
        mll.add("C");
        mll.add("A");
        mll.removeAll(strings);
        assertEquals(2, mll.size());

    }

    @org.junit.jupiter.api.Test
    void retainAllTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        mll.add("B");
        mll.add("Z");
        mll.add("V");
        mll.add("C");
        mll.add("A");
        assertTrue(mll.retainAll(strings));
        assertEquals(mll.get(1), "C");
        assertEquals(mll.get(0), "B");
        assertEquals(mll.get(2), "A");
        assertEquals(mll.size(), 3);

    }
}