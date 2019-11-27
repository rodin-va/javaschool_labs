package org.javaschool.lab4;

import java.util.*;

public class CustomLinkedList<E extends Object & Comparable<? super E>> implements List<E> {

    private class ListItem<E extends Object & Comparable<? super E>> implements Comparable<E> {
        E value;
        ListItem<E> nextItem;
        ListItem<E> previousItem;

        public ListItem(E value, ListItem<E> nextItem, ListItem<E> previousItem) {
            this.value = value;
            this.nextItem = nextItem;
            this.previousItem = previousItem;
        }

        public ListItem(E value) {
            this(value, null, null);
        }

        public ListItem<E> getPreviousItem() {
            return previousItem;
        }

        public void setPreviousItem(ListItem<E> previousItem) {
            this.previousItem = previousItem;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public ListItem<E> getNextItem() {
            return nextItem;
        }

        public void setNextItem(ListItem<E> nextItem) {
            this.nextItem = nextItem;
        }

        @Override
        public int compareTo(E o) {
            return value.compareTo(o);
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }
    }

    private ListItem<E> firstItem;
    private ListItem<E> lastItem;
    private Integer listSize;

    @Override
    public int size() {
        return this.listSize;
    }

    @Override
    public boolean isEmpty() {
        return this.listSize > 0;
    }

    @Override
    public boolean contains(Object o) {
        return firstItem.equals(o);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        ListItem<E> newItem = new ListItem<E>(e, this.lastItem, null);
        if(lastItem != null) {
            this.firstItem = newItem;
        } else {
            this.lastItem.nextItem = newItem;
        }
        this.lastItem = newItem;
        this.listSize++;
        return true;
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
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public E get(int index) throws NoSuchElementException {
        return getItem(index).getValue();
    }

    private ListItem<E> getItem(int index) throws NoSuchElementException {
        if(index < 0 || index >= this.listSize) {
            throw new NoSuchElementException();
        }

        ListItem<E> currentItem;
        if(index < this.listSize / 2.0) {
            currentItem = this.firstItem;
            for (int i = 1; i < index; i++) {
                currentItem = currentItem.nextItem;
            }
        } else {
            currentItem = this.lastItem;
            for (int i = 1; i < this.listSize - index - 1; i++) {
                currentItem = currentItem.previousItem;
            }
        }

        return currentItem;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        ListItem<E> currentItem = getItem(index);
        currentItem.setValue(element);
        return currentItem.getValue();
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if(index == this.listSize) {
            this.add(element);
        } else {
            ListItem<E> currentItem = getItem(index);
            ListItem<E> newItem = new ListItem<E>(element, currentItem.previousItem, currentItem);
            //currentItem.previousItem = ;
            //currentItem.setValue(element);
        }

    }

    @Override
    public E remove(int index) {
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
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public CustomLinkedList() {
        this.firstItem = null;
        this.lastItem = null;
        this.listSize = 0;
    }

    public static void main(String[] args) {
        System.out.println(2 < 5 / 2.0);
        System.out.println(2 < 6 / 2.0);
    }

}
