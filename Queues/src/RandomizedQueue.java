import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item>  {
    //implements Iterable<Item>

    private Item[] queue;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[1];
        this.n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.n;
    }

    // helper method to resize queue
    private void resize(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument");
        }
        if (size() == queue.length) {
            resize(queue.length * 2);
        }
        if (n == 0) {
            queue[0] = item;
            n += 1;
        }
        else {
            int randomPosition = StdRandom.uniform(n);
            Item temp = queue[randomPosition];
            queue[randomPosition] = item;
            queue[n] = temp;
            n += 1;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is already empty");
        }
        if (size() == queue.length / 4) {
            resize(queue.length / 2);
        }
        int randomPosition = StdRandom.uniform(n);
        Item item = queue[randomPosition];
        n -= 1;
        queue[randomPosition] = queue[n];
        queue[n] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is already empty");
        }
        int randomPosition = StdRandom.uniform(n);
        return queue[randomPosition];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int iterSum;
        private int[] randomIndices;

        public ArrayIterator() {
            this.iterSum = 0;
            randomIndices = new int[n];
            for (int i = 0; i < n; i++) {
                randomIndices[i] = i;
            }

            StdRandom.shuffle(randomIndices);
        }

        public boolean hasNext() {
            return iterSum < size();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element in iterator");
            }
            else {
                int oldIterSum = iterSum;
                iterSum += 1;
                return queue[randomIndices[oldIterSum]];
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported operation");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue randomizedQueue = new RandomizedQueue();

        String item0 = "item0";
        String item1 = "item1";
        String item2 = "item2";
        String item3 = "item3";

        StdOut.println(randomizedQueue.isEmpty());
        StdOut.println(randomizedQueue.size());

        randomizedQueue.enqueue(item0);
        randomizedQueue.enqueue(item1);
        randomizedQueue.enqueue(item2);
        randomizedQueue.enqueue(item3);

        StdOut.print(randomizedQueue.n);

        StdOut.println(randomizedQueue.dequeue());

        StdOut.println(randomizedQueue.sample());

        Iterator iter = randomizedQueue.iterator();

        StdOut.println(iter.hasNext());

        StdOut.println(randomizedQueue.n);

        StdOut.println(iter.next());
        StdOut.println(iter.next());
        StdOut.println(iter.next());
        StdOut.println(iter.hasNext());
    }

}