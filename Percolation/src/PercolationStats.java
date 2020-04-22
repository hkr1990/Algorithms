import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double [] thresholdArray;
    private int numTrials;
    private double mean;
    private double stddev;
    private double confidencelo;
    private double confidencehi;
    private double sqrtNumTrials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal argument");
        }
        
        thresholdArray = new double[trials];
        numTrials = trials;
        for (int index = 0; index < trials; index++) {
            Percolation percolationObject = new Percolation(n);
            thresholdArray[index] = monteCarloExperiment(n, percolationObject);
        }
        
        // Calculate stats
        sqrtNumTrials = Math.sqrt(numTrials);    
        mean = StdStats.mean(thresholdArray);
        stddev = StdStats.stddev(thresholdArray);
        confidencelo = (mean - (1.96 * stddev)/ sqrtNumTrials);
        confidencehi = (mean + (1.96 * stddev)/ sqrtNumTrials);    
    }
    
    private double monteCarloExperiment(int size, Percolation object) {
        int row = StdRandom.uniform(1, size+1);
        int col = StdRandom.uniform(1, size+1);
        while (!object.percolates()) {
            row = StdRandom.uniform(1, size+1);
            col = StdRandom.uniform(1, size+1);
            if (!object.isOpen(row, col)) {
                object.open(row, col);    
            }
        }
        
        double percolationThreshold = (double) object.numberOfOpenSites() / (double) (size * size);
        return percolationThreshold;
    }
    
    public double mean() {                         // sample mean of percolation threshold
        return mean; 
    }
    
    public double stddev() {                      // sample standard deviation of percolation threshold
        return stddev; 
    }
    public double confidenceLo() {              // low  endpoint of 95% confidence interval
        return confidencelo; 
    }
    
    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        return confidencehi;
    }


    public static void main(String[] args) {
        if (args.length == 2) {
            int gridN = Integer.parseInt(args[0]);
            int numTrials = Integer.parseInt(args[1]);
            PercolationStats obj = new PercolationStats(gridN, numTrials);
            System.out.println("mean                    = " + obj.mean());
            System.out.println("stddev                  = " + obj.stddev());
            System.out.println("95% confidence interval = [" + obj.confidenceLo() + "," + obj.confidenceHi() + "]");
        } 
        else {
            System.out.println("Invalid argument. Help : PercolationStats <gridSize> <numTrials>");
        }
    }

}