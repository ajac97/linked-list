package edu.touro.cs;

import java.util.*;

//public                        // U RO A R
//protected
//package private -  default
//private


public class MyLinkedList<T> implements List<T> {


    private static class Node<T> {
        T data;
        Node<T> prev, next;

        public Node() {
        }

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> head, tail;
    private int size;

    public MyLinkedList() {
        head = tail = new Node<>(); // dummy node
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        // for (Iterator<T> i = this.iterator(); i.hasNext();) - non "syntactic sugar" way of writing for each
        for (Object obj : this) {
            if (obj.equals(o)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    // Principle of Least Privilege - grant as little access as needed
    // static inner class is when inner class has no need to access outer class
    // and is placed in outer class to help organize code

    private class MyLinkedListIterator implements Iterator<T> { // inner class (static inner classes vs nonstatic inner classes)

        private Node<T> prevPtr = head;
        private boolean nextWasCalled, removeWasCalled;

        @Override
        public boolean hasNext() {
            return this.prevPtr.next != null;
        }

        @Override
        public T next() { // returns next data elt, and updates the ptr
            if (!hasNext())
                throw new NoSuchElementException();
            nextWasCalled = true;
            removeWasCalled = false; // allow remove to be called again
            // incr iterator;
            prevPtr = prevPtr.next;
            return prevPtr.data;   // return data elt that was next
        }

        public void remove() {
            if (!nextWasCalled) {
                throw new IllegalStateException("Cannot call remove unless next called first");
            }
            if (removeWasCalled) {
                throw new IllegalStateException("Cannot call remove twice unless next was called first");
            }
            removeWasCalled = true;
            // remove elt last returned by this iterator
            Node<T> beforeCurrent = prevPtr.prev;
            Node<T> afterCurrent = prevPtr.next;

            beforeCurrent.next = afterCurrent;
            afterCurrent.prev = beforeCurrent;
            size--;
        }
    }

    // iterators are necessary when you need to modify i.e. remove elt from list during iteration
    //
    private void checkNull(Collection<?> c) {
        for (Object o : c) {
            if (o == null) {
                throw new NullPointerException("The values in the collection cannot be null");
            }
        }
    }

    private void checkBoundaries(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("The index provided is out of the bounds of this LinkedList");
        }
    }

    @Override//TODO
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        int i = 0;
        for (Object o : this) {
            arr[i++] = o;
        }
        return arr;
    }

    @Override//TODO
    public <T1> T1[] toArray(T1[] a) {
        if (a.length >= size) {
            for (int i = 0; i <= size(); i++) {
                if (i < size) {
                    a[i] = (T1) get(i);
                }
                if (i == size) {
                    a[i] = null;
                }
            }
            return a;
        }
        return (T1[]) toArray();
    }

    @Override//TODO
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t, tail, null);
        tail.next = newNode; // old last node point to new
        tail = newNode; // or tail = newNode // tail point to new Node
        size++;
        return true;
    }

    @Override //
    public boolean remove(Object o) {
        int startingSize = size();
        remove(indexOf(o));
        return (startingSize != size());
    }


    @Override//
    public boolean containsAll(Collection<?> c) {
        checkNull(c);
        if (c.isEmpty()) {
            return true;
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override//
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }
        checkNull(c);
        for (T o : c) {
            add(o);
        }
        return true;
    }

    @Override//
    public boolean addAll(int index, Collection<? extends T> c) {
        checkBoundaries(index);
        checkNull(c);
        if (c.isEmpty()) {
            return false;
        }
        for (T o : c) {
            add(index++, o);
        }
        return true;
    }


    @Override//
    public boolean removeAll(Collection<?> c) {
        checkNull(c);
        if (c.isEmpty()) {
            return false;
        }
        int startingSize = size();
        for (Object o : c) {
            remove(o);
        }
        return (startingSize != size());
    }

    @Override//
    public boolean retainAll(Collection<?> c) {
        checkNull(c);
        if (c.isEmpty()) {
            return false;
        }
        int startingSize = size();
        for (Iterator<T> i = this.iterator(); i.hasNext(); ) {
            T t = i.next();
            if (!c.contains(t)) {
                i.remove();
            }
        }
        return (startingSize != size());
    }

    @Override//TODO
    public void clear() {
        head = tail = new Node<>(); // disconnected nodes will be garbage collected
        size = 0;
    }
// remove
// static inner class
// iterator

    private Node<T> getNode(int index) {
        checkBoundaries(index);
        Node<T> currentNode = head.next;
        for (int counter = 0; counter < index; currentNode = currentNode.next, counter++)
            ; // BLANK
        return currentNode;
    }

    @Override//TODO
    public T get(int index) {
        return getNode(index).data;
    }

    @Override//TODO
    public T set(int index, T t) {
        Node<T> node = getNode(index);
        T oldData = node.data;
        node.data = t;
        return oldData;
    }

    @Override//TODO
    public void add(int index, T element) {
        Node<T> oldNode = getNode(index);
        Node newNode = new Node<>(element, oldNode, oldNode.prev);
        oldNode.prev.next = newNode;
        oldNode.prev = newNode;
        size++;


    }

    @Override//TODO
    public T remove(int index) {
        Node<T> node = getNode(index);
        T oldValue = node.data;
        Node<T> beforeNode = node.prev;
        Node<T> afterNode = node.next;
        beforeNode.next = afterNode;
        if (afterNode == null) {
            tail = beforeNode;
        } else {
            afterNode.prev = beforeNode;
        }
        size--;
        return oldValue;

    }

    @Override //TODO
    public int indexOf(Object o) {
        Node<T> currentNode = head.next;
        for (int counter = 0; counter < size; currentNode = currentNode.next, counter++) {
            if (currentNode.data.equals(o)) {
                return counter;
            }

        }
        return -1;
    }


    @Override//TODO
    public int lastIndexOf(Object o) {
        Node<T> currentNode = tail;
        for (int counter = size() - 1; counter >= 0; currentNode = currentNode.prev, counter--) {
            if (currentNode.data.equals(o)) {
                return counter;
            }
        }
        return -1;
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
