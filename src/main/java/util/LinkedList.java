package util;

import java.util.NoSuchElementException;

public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(T value) {
        if (head == null) {
            addFirst(value);
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(value);
        size++;
    }

    public T removeFirst() {
        ensureNotEmpty();
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    public T removeLast() {
        ensureNotEmpty();

        if (head.next == null) {
            T value = head.value;
            head = null;
            size--;
            return value;
        } else {
            Node<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            T value = current.next.value;
            current.next = null;
            size--;
            return value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureNotEmpty() {
        if (head == null) {
            throw new NoSuchElementException("A lista esta vazia");
        }
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
