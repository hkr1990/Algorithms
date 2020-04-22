/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if(that == null || this == null) {
            throw new NullPointerException();
        }

        if((this.x - that.x) == 0) {
            if((this.y - that.y) == 0) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        
        return ((double)(that.y - this.y) / (double)(that.x - this.x) + 0.0);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if(this.y > that.y) {
            return 1;
        }
        else if(this.y < that.y) {
            return -1;
        } 
        else { /*this.y == that.y*/
            if(this.x > that.x) {
                return 1; 
             } else if (this.x < that.x) {
                return -1;
             } else {
                return 0;   
             }
        }
    }
    

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if(slopeTo(a) > slopeTo(b)) {
                    return 1;
                } else if (slopeTo(a) < slopeTo(b)) {
                    return -1;
                }
                else return 0;
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point a = new Point(0, 10); 
        Point horizontal_to_a = new Point(5, 10); 
        Point vertical_to_a = new Point(0, 20);
        Point copy_of_a = new Point(0, 10);
        Point smaller_to_a = new Point(0, 5);
        Point largest = new Point(100, 100);
        
        a.draw();
        horizontal_to_a.draw();
        vertical_to_a.draw();
        copy_of_a.draw();
        smaller_to_a.draw();
        largest.draw();
        
        // Slope test
        System.out.println("Slope Test " + a.slopeTo(horizontal_to_a));
        System.out.println("Slope Test " + a.slopeTo(vertical_to_a));
        System.out.println("Slope Test " + a.slopeTo(copy_of_a));
        System.out.println("Slope Test " + a.slopeTo(smaller_to_a));
        System.out.println("Slope Test " + a.slopeTo(largest));
        
        
        // CompareTo Test
        System.out.println(a.compareTo(copy_of_a) == 0);
        System.out.println(a.compareTo(smaller_to_a) < 0);
        System.out.println(a.compareTo(largest) > 0);
        System.out.println(a.compareTo(horizontal_to_a) > 0);
        System.out.println(a.compareTo(vertical_to_a) > 0);
        System.out.println("Compare to Test Over");
        
        // SlopeOrder Test
        Point[] arr = new Point[6];
        
        Point ref = new Point(0, 0);

        arr[0] = new Point(5, 0);
        arr[1] = new Point(3, 3);
        arr[2] = new Point(0, 5);
        arr[3] = new Point(-3, 3);
        arr[4] = new Point(-5, 0);
        
        System.out.println(ref.slopeOrder().compare(arr[1], arr[0]));
        System.out.println((ref.slopeOrder().compare(arr[2], arr[1])));
        System.out.println((ref.slopeOrder().compare(arr[2], arr[3])));
        System.out.println((ref.slopeOrder().compare(arr[3], arr[4])));
        System.out.println("Slope New Order Test Over");
        
        

    }
}
