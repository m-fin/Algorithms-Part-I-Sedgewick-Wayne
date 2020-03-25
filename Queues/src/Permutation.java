import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        double n = 1.0;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (k == 0) {
                return;
            } else if (randomizedQueue.size() < k) {
                randomizedQueue.enqueue(str);
            } else if (StdRandom.uniform() < (k / n)) {
                randomizedQueue.dequeue();
                randomizedQueue.enqueue(str);
            }
            n++;
        }
        while (!randomizedQueue.isEmpty()) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
