import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size = 0;
    private int numOpenSites = 0;
    private int virtualTopSite = 0;
    private int virtualBottomSite = 0;
    private WeightedQuickUnionUF dataObject;

    public Percolation(int n) {        
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal argument");
        }
        
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false; // Blocked
            }
        }
        
        size = n;
        dataObject = new WeightedQuickUnionUF((n * n) + 2); // 2 virtual cells 
        virtualTopSite = n * n;
        virtualBottomSite = virtualTopSite + 1;
    }
    
    // Note: Internal Function, So we expect that row and col are already proper
    private int array2DtoUnionIndex(int row, int col) {
        if (row > size - 1  ||  row < 0 ||  col > size - 1 ||  col < 0) {
            throw new IllegalArgumentException("Conversion Error:Invalid Row or Column Id");
        }
        
        return (row * size + col);
    }
    
    public void open(int row, int col) {
        if (row > size ||  row < 1 ||  col > size ||  col < 1) {
            throw new IllegalArgumentException("Open:Invalid Row or Column Id");
        }
        
        if (grid[row - 1][col - 1]) {
            return;
        }
        
        grid[row - 1][col - 1] = true;  // Open
        numOpenSites++;

        // if site is top row, connect it to virtual top row
        if (row == 1) {
            dataObject.union(array2DtoUnionIndex(row - 1, col - 1), virtualTopSite);    
        }
        
        // if site is bottom row, connect it to bottom virtual row
        if (row == size) { 
            dataObject.union(array2DtoUnionIndex(row - 1, col - 1), virtualBottomSite);    
        }
        
        // If any site is open on top, bottom, right and left. Then connect it to that
        int currentRow   = row - 1;
        int currentCol   = col - 1;
        int adjTopRow  = currentRow - 1; 
        int adjTopCol  = currentCol; 
        int adjBottomRow  = currentRow + 1; 
        int adjBottomCol  = currentCol; 
        int adjRightRow  = currentRow; 
        int adjRightCol  = currentCol + 1; 
        int adjLeftRow  = currentRow; 
        int adjLeftCol  = currentCol - 1; 
        
        try {
            if (isOpen(adjTopRow + 1, adjTopCol + 1)) {
                dataObject.union(array2DtoUnionIndex(currentRow, currentCol), array2DtoUnionIndex(adjTopRow, adjTopCol));    
            }
        } 
        catch (IllegalArgumentException e) {
            // Do nothing    
        }

        try {
            if (isOpen(adjBottomRow + 1, adjBottomCol + 1)) {
                dataObject.union(array2DtoUnionIndex(currentRow, currentCol), array2DtoUnionIndex(adjBottomRow, adjBottomCol));    
            }
        } 
        catch (IllegalArgumentException e) {
          // Do nothing    
        }

        try {
            if (isOpen(adjRightRow + 1, adjRightCol + 1)) {
                dataObject.union(array2DtoUnionIndex(currentRow, currentCol), array2DtoUnionIndex(adjRightRow, adjRightCol));    
            }
        } 
        catch (IllegalArgumentException e) {
          // Do nothing    
        }

        try {
            if (isOpen(adjLeftRow + 1, adjLeftCol + 1)) {
                dataObject.union(array2DtoUnionIndex(currentRow, currentCol), array2DtoUnionIndex(adjLeftRow, adjLeftCol));    
            }
        } 
        catch (IllegalArgumentException e) {
          // Do nothing    
        }
    }

    public boolean isOpen(int row, int col) {
        if (row > size ||  row < 1 ||  col > size ||  col < 1) {
            throw new IllegalArgumentException("isOpen:Invalid Row or Column Id");
        }

        return grid[row - 1][col - 1];
    }
    
    public boolean isFull(int row, int col) {
        if (row > size ||  row < 1 ||  col > size ||  col < 1) {
            throw new IllegalArgumentException("isFull:Invalid Row or Column Id");
        }

        // Check if site is connected to virtual top site
        if (isOpen(row, col) && dataObject.connected(array2DtoUnionIndex(row - 1, col - 1), virtualTopSite)) {
            return true;
        }

        return false;
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        // Check if site is connected to virtual top site and bottom sites are connected
        if (dataObject.connected(virtualBottomSite, virtualTopSite)) {
            return true;
        }
        return false;
    }
    
}
