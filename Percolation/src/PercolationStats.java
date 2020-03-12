import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int size;
    private double meanThreshold;
    private double stddevThreshold;
    private double confidenceLow;
    private double confidenceHigh;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal arguments.");
        }
        else {
            this.size = n;

            double[] thresholds = new double[trials];
            for (int i = 0; i < trials; i++) {
                thresholds[i] = trial();
            }

            this.meanThreshold = StdStats.mean(thresholds);
            this.stddevThreshold = StdStats.stddev(thresholds);

            double diff = (1.96 * stddevThreshold) / Math.sqrt(trials);
            this.confidenceLow = meanThreshold - diff;
            this.confidenceHigh = meanThreshold + diff;

        }
    }

    private double trial() {
        Percolation trial = new Percolation(size);

        int nOpen = 0;
        while (!trial.percolates()) {
            int row = StdRandom.uniform(size) + 1;
            int col = StdRandom.uniform(size) + 1;

            if (!trial.isOpen(row, col)) {
                trial.open(row, col);
                nOpen++;
            }
        }

        return ((double) nOpen) / (size * size);

    }

    // sample mean of percolation threshold
    public double mean() {
        return this.meanThreshold;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddevThreshold;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHigh;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolations = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + percolations.mean());
        StdOut.println("stddev                  = " + percolations.stddev());
        StdOut.println("95% confidence interval = " + percolations.confidenceLo() + ", " + percolations.confidenceHi());
    }
}