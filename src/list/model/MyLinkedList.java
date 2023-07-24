package list.model;

import list.interfaces.IList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements IList<E> {
    private Node<E> first;
    private Node<E> last;

    private int size;

    // Time Complexity: O(1)
    @Override
    public int size() {
        return this.size;
    }

    // Time Complexity: O(1)
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    // Time Complexity: O(1)
    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(last, element, null);
        if (last != null) {
            last.next = newNode;
        } else {
            first = newNode;
        }
        last = newNode;
        size++;
        return true;
    }

    // Time Complexity: O(n)
    @Override
    public boolean add(int index, E element) {
        if (index == size) {
            return add(element);
        }
        Node<E> node = getNodeByIndex(index);
        Node<E> newNode = new Node<>(node.prev, element, node);
        node.prev = newNode;
        newNode.prev.next = newNode;
        size++;
        return true;
    }

    // Time Complexity: O(n)
    @Override
    public E get(int index) {
        Node<E> node = getNodeByIndex(index);
        return node.data;
    }

    // Time Complexity: O(1)
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    //Time Complexity: O(n)
    private Node<E> getNodeByIndex(int index) {
        checkIndex(index);
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    // Time Complexity: O(n)
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o != null) {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (o.equals(node.data)) {
                    return index;
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (o == node.data) {
                    return index;
                }
            }
        }
        return -1;
    }

    //Time Complexity: O(n)
    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o != null) {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (o.equals(node.data)) {
                    return index;
                }
            }
        } else {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (node.data == o) {
                    return index;
                }
            }
        }
        return -1;
    }

    //Time Complexity: O(n)
    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            E removeDatta = first.data;
            first = first.next;
            size--;
            return removeDatta;
        }
        Node<E> prevNode = getNodeByIndex(index - 1);
        E removeData = prevNode.next.data;
        prevNode.next = prevNode.next.next;
        size--;
        return removeData;
    }

    //Time Complexity: O(n)
    @Override
    public E set(int index, E element) {
        Node<E> node = getNodeByIndex(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;

            // Time Complexity: O(1)
            @Override
            public boolean hasNext() {
                return current != null;
            }

            // Time Complexity: O(1)
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
