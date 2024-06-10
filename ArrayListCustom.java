package org.ositlar.collections;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListCustom<E> extends AbstractList<E> {
    private final int DEFAULT_CAPACITY = 10;
    private final int THRESHOLD = 256;
    private int size;
    private int capacity;
    private int lastInsertedIndex = 0;
//    private final Object[] arrayWithDefaultCapacity = new Object[DEFAULT_CAPACITY];
    private Object[] elements = null;

    public ArrayListCustom() {
        elements = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }
    public ArrayListCustom(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
            capacity = initialCapacity;
        } else if (initialCapacity == 0) {
            elements = new Object[]{};
            capacity = 0;
        } else {
            throw new IllegalArgumentException("Initial capacity less than 0: " + initialCapacity);
        }
    }

    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return (E) elements[index];
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        if (index == capacity) {
            ensureCapacity();
        }
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
        System.out.println(size + " " + capacity);
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index + ", Capacity " + capacity);
        }
        Object item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (E) item;
    }

    private void ensureCapacity() {
        int newSize;
        if (elements.length < THRESHOLD) {
            newSize = 2 * elements.length;
        } else {
            newSize = (int)(elements.length * 1.25);
        }
        capacity = newSize;
        elements = Arrays.copyOf(elements, newSize);
    }
}

class Main {
    public static void main(String[] args) {
        ArrayListCustom<String> a = new ArrayListCustom<>(10);
        for (int i = 0; i < 1000; i++) {
            a.add(i, String.valueOf(i));

        }
    }
}
