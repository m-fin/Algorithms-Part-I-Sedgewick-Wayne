import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final int size;
    private int nOpen;
    private boolean percolated;

    private int virtualTopIndex;
    private int virtualBottomIndex;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // checks for corner case
        if (n <= 0) {
            throw new IllegalArgumentException("n cannot be less than or equal to 0");
        }

        this.size = n;
        this.nOpen = 0;
        this.percolated = false;

        this.virtualTopIndex = -1;
        this.virtualBottomIndex = -1;

        // create empty grid
        this.grid = new boolean[n+1][n+1];

        // set each site in grid to full (false)
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                this.grid[row][col] = false;
            }
        }

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
        if (!checkInGrid(row, col)) {
            throw new IllegalArgumentException("Illegal");
        }

        if (!isOpen(row, col)) {
            // open site
            this.grid[row][col] = true;
            this.nOpen++;

            // check surrounding sites
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

            // virtual sites
            if (row == 1) {
                if (virtualTopIndex == -1) {
                    virtualTopIndex = convertTo1D(row, col);
                }
                else {
                    weightedQuickUnionUF.union(convertTo1D(row, col), virtualTopIndex);
                }
            }
            if (row == size) {
                if (virtualBottomIndex == -1) {
                    virtualBottomIndex = convertTo1D(row, col);
                }
                else {
                    weightedQuickUnionUF.union(convertTo1D(row, col), virtualBottomIndex);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!checkInGrid(row, col)) {
            throw new IllegalArgumentException("Illegal");
        }

        if (this.grid[row][col]) {
            return true;
        }

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!checkInGrid(row, col)) {
            throw new IllegalArgumentException("Illegal");
        }

        if (isOpen(row, col) && virtualTopIndex != -1) {
            int siteIndexGroup = this.weightedQuickUnionUF.find(convertTo1D(row, col));
            int virtualTopIndexGroup = this.weightedQuickUnionUF.find(virtualTopIndex);

            if (siteIndexGroup == virtualTopIndexGroup) {
                return true;
            }
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.nOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (virtualTopIndex != -1 && virtualBottomIndex != -1 && !percolated) {
            if (this.weightedQuickUnionUF.find(virtualTopIndex) == this.weightedQuickUnionUF.find(virtualBottomIndex)) {
                percolated = true;
            }
        }

        return percolated;
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("test");
    }
}
