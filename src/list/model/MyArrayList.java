package list.model;



import list.interfaces.IList;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements IList<E> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
//        elements = new Object[10];
        this(10);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity = " + initialCapacity);
        }
        elements = new Object[initialCapacity];
    }

    //O(1)

    /**
     * The function returns the size of a data structure.
     *
     * @return The size of the object.
     */
    @Override
    public int size() {
        return size;
    }
    //O(n)

    /**
     * The clear() function sets all elements in the elements array to null and updates the size to 0.
     */
    @Override
    public void clear() {
        if (size != 0) {
            for (Object o : elements) {
                o = null;
            }
            size = 0;
        }
    }

    //O(1)

    /**
     * The add() function adds an element to an array, ensuring that there is enough capacity.
     *
     * @param element The "element" parameter represents the element that you want to add to the collection.
     * @return The method is returning a boolean value of true.
     */
    @Override
    public boolean add(E element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }
    //O(1)

    /**
     * The function ensures that the capacity of the array is sufficient to accommodate new elements, and increases the
     * capacity if necessary.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            if (size == Integer.MAX_VALUE) {
                throw new OutOfMemoryError();
            }
            int newCapacity = elements.length + elements.length / 2;
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
    //O(n)

    /**
     * The add method inserts an element at a specified index in an array, shifting all subsequent elements to the right.
     *
     * @param index   The index parameter represents the position at which the element should be added in the list. It is an
     *                integer value that specifies the index position in the list where the element should be inserted.
     * @param element The element to be added at the specified index in the list.
     * @return The method is returning a boolean value, specifically `true`.
     */
    @Override
    public boolean add(int index, E element) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
        elements[index] = element;
        size++;
        return true;
    }

    //O(1)

    /**
     * The function returns the element at the specified index in an array.
     *
     * @param index The index parameter is an integer that represents the position of the element to be retrieved from the
     *              list.
     * @return The element at the specified index is being returned.
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * The function checks if an index is within the valid range.
     *
     * @param index The "index" parameter is an integer value representing the index position being checked.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    //O(n)
    // The `indexOf` method is used to find the index of the first occurrence of a specified element in the array.
    @Override
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o == elements[i]) {
                    return i;
                }
            }
        }

        return -1;
    }

    //O(n)
    // The `lastIndexOf` method is used to find the index of the last occurrence of a specified element in the array.
    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            for (int i = size; i >= 0; i--) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size; i >= 0; i--) {
                if (o == elements[i]) {
                    return i;
                }
            }
        }

        return -1;
    }
    //O(n)

    /**
     * The remove() function removes an element at a specified index from an array and returns the removed element.
     *
     * @param index The "index" parameter represents the position of the element that you want to remove from the list.
     * @return The method is returning the element that was removed from the list.
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E e = (E) elements[index];
//        elements[index] = null;
//        for (int i = 0, j = 0; i > size; i++) {
//            if (i == index) {
//                elements[i] = null;
//            } else {
//                elements[j] = elements[i];
//                j++;
//            }
//        }
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        size--;
        return e;
    }
    //O(1)

    /**
     * The function sets the element at the specified index in the list and returns the previous element at that index.
     *
     * @param index   The index parameter represents the position in the list where the element needs to be set. It is an
     *                integer value that specifies the index of the element to be replaced.
     * @param element The element that will replace the existing element at the specified index.
     * @return The method is returning the element that was previously at the specified index.
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E e = (E) elements[index];
        elements[index] = element;
        return e;
    }
    //O(1)

    /**
     * The function returns an iterator that allows iterating over the elements of a collection.
     *
     * @return The method is returning an iterator of type E.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int curPos = 0;

            @Override
            public boolean hasNext() {
                return size > curPos;
            }

            @Override
            public E next() {
                E e = (E) elements[curPos];
                curPos++;
                return e;
            }
        };
    }
}
