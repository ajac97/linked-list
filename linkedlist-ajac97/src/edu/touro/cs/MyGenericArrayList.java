package edu.touro.cs;

import java.util.*;


// generics - create flexible data structures for many types that are TYPE SAFE
public class MyGenericArrayList<T> implements List<T> // T is called a type parameter... a placeholder,
        // for the type argument specified when instantiate a MyGenericArrayList<String>
        // String is the type argument
{
    private T[] backingStore;
    private int insertionPoint = 0;

    public MyGenericArrayList(){
        this(10);
    }

    public MyGenericArrayList(int initialCapacity)
    {
        backingStore = (T[]) new Object[initialCapacity];
    }

    @Override
    public int size() {
        return insertionPoint;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (o == null && backingStore[i] == null ||
                    o != null && o.equals(backingStore[i]))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (insertionPoint >= backingStore.length) {
            growBackingStore(); // O(n) - linear time
        }
        backingStore[insertionPoint++] = t; // O(1) - constant time
        return true;
    }

    private void growBackingStore() // new array will be twice size old array
    {
        Object[] newBackingStore = new Object[backingStore.length * 2 + 1];
        System.arraycopy(backingStore, 0, newBackingStore, 0, backingStore.length);
        backingStore = (T[]) newBackingStore;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return backingStore[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
