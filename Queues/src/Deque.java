import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n;
    private Node first, last;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    // construct an empty deque
    public Deque() {
        this.n = 0;
        this.first = null;
        this.last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument");
        }

        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;

        // if deque was empty, first is last
        if (oldFirst == null) {
            this.last = first;
        }
        // else first is previous to oldFirst
        else {
            oldFirst.prev = first;
        }

        this.n += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument");
        }

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;

        // if deque was empty, last is first
        if (oldLast == null) {
            this.first = last;
        }
        // else last is next to oldLast
        else {
            oldLast.next = last;
        }

        this.n += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = first.item;

        first = first.next;

        if (size() == 0) {
            last = null;
        }
        else {
            first.prev = null;
        }

        this.n -= 1;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = last.item;

        last = last.prev;

        if (size() == 0) {
            first = null;
        }
        else {
            last.next = null;
        }

        this.n -= 1;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        // Node used for iteration
        private Node current = first;

        public boolean hasNext() {
            return current.next != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element in iterator");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported operation");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 10; i += 2) {
            deque.addFirst(i);
            deque.addLast(i + 1);
        }
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next() + " ");
        }
        StdOut.println("size: " + deque.size());

        for (int i = 0; i < 3; ++i) {
            deque.removeLast();
            deque.removeFirst();
        }
        it = deque.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println("size: " + deque.size());

        for (int i = 0; i < 6; i += 2) {
            deque.addLast(i);
            deque.addFirst(i + 1);
        }
        it = deque.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + deque.size());
    }

}
