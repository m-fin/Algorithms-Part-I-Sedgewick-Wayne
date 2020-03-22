import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    boolean[][] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // checks for corner case
        if (n <= 0) {
            throw new IllegalArgumentException("n cannot be less than or equal to 0");
        }

        // create empty grid
        this.grid = new boolean[n][n];

        // set each site in grid to full (false)
        for (int row = 1; row < size; row++) {
            for (int col = 1; col < size; col++) {
                grid[row][col] = false;
            }
        }

        this.size = n;

        this.weightedQuickUnionUF = new WeightedQuickUnionUF(size * size);
    }

    private int convertTo1D(int row, int col) {
        return (row - 1) * (size) + (col - 1);
    }

    private boolean checkInGrid(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            return false;
        }

        return true;
    }

    // opens the site (row, col) if it is not open already
    // then connects site to neighboring open sites if available
    public void open(int row, int col) {
        this.grid[row][col] = true;

        if (checkInGrid(row - 1, col)) {
            if (this.grid[row - 1][col]) {
                this.weightedQuickUnionUF.union(convertTo1D(row, col), convertTo1D(row - 1, col));
            }
        }

        if (checkInGrid(row + 1, col)) {
            if (this.grid[row + 1][col]) {
                this.weightedQuickUnionUF.union(convertTo1D(row, col), convertTo1D(row + 1, col));
            }
        }

        if (checkInGrid(row, col - 1)) {
            if (this.grid[row][col - 1]) {
                this.weightedQuickUnionUF.union(convertTo1D(row, col), convertTo1D(row, col - 1));
            }
        }

        if (checkInGrid(row, col + 1)) {
            if (this.grid[row][col + 1]) {
                this.weightedQuickUnionUF.union(convertTo1D(row, col), convertTo1D(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // if (checkInGrid(row, col)) {
        //     if (this.grid[row][col]) {
        //         return true;
        //     }
        // }

        return false;
        // if (!checkInGrid(row, col)) {
        //     throw new IllegalArgumentException("Illegal Argument!");
        // }
        //
        // return !grid[row][col];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numOfOpenSites = 0;

        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[row].length; col++) {
                if (isOpen(row, col)) {
                    numOfOpenSites++;
                }
            }
        }

        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("test");
    }
}
