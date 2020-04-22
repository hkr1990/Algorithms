import java.lang.IllegalArgumentException;

public class BruteCollinearPoints {
    private int numSegments = 0;
    private LineSegment[] arr;
    
    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if(points == null) {
            throw new IllegalArgumentException();
        }

        int size = points.length;
        for(int i = 0; i < size; ++i) {
            if(points[i] == null) {
                throw new IllegalArgumentException();
            }

            for(int j = i + 1; j < size; ++j) {
                if(points[j] == null) {
                    throw new IllegalArgumentException();
                }
                if(points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }

            }
        }

        LineSegment[] tempArr = new LineSegment[((size) * (size - 1) * (size - 2) * (size -3)) / 24];
        
        for(int p = 0; p < points.length - 3; ++p) {
            for(int q = p + 1; q < points.length - 2; ++q) {
                for(int r = q + 1; r < points.length - 1; ++r) {
                    for(int s = r + 1; s < points.length; ++s) {
                        if(points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                           // Find the longest line segment from 4 points and add it to the arr[]
                            
                            Point max = points[p];
                            // Check q
                            if(points[q].compareTo(max) > 0) 
                                max = points[q];
                            // Check r
                            if(points[r].compareTo(max) > 0) 
                                max = points[r];
                            // Check s
                            if(points[s].compareTo(max) > 0) 
                                max = points[s];

                            Point min = points[p];
                            if(points[q].compareTo(min) < 0) 
                                min = points[q];
                            if(points[r].compareTo(min) < 0) 
                                min = points[r];
                            if(points[s].compareTo(min) < 0) 
                                min = points[s];
                            
                            tempArr[numSegments] = new LineSegment(max, min);
                            
                            numSegments++;
                        }
                    }
                }
            }
        } // end of for
        
        arr = new LineSegment[numSegments];
        for(int i = 0; i < numSegments; ++i) {
            arr[i] = tempArr[i];
        }

    }

    public int numberOfSegments() {       // the number of line segments
        return numSegments;
    }

    public LineSegment[] segments() {     // the line segments
        return arr.clone();
    }
    
    /**
     * Unit tests the Brute Collinear Point.
     */
    public static void main(String[] args) {
        
//        // null test case
//        // array
//        try {
//            BruteCollinearPoints obj = new BruteCollinearPoints(null);
//        } catch (IllegalArgumentException e){
//            System.out.println("Test 1 Passed");
//        } catch (Exception e) {
//            System.out.println("Test 1 Failed");
//        }
//
//        {
//            // a point in the array
//            Point[] testArr = new Point[5]; 
//            testArr[0] = new Point(4, 4);
//            testArr[2] = new Point(4, 4);
//            testArr[3] = new Point(4, 4);
//            testArr[4] = new Point(4, 4);
//            try {
//                BruteCollinearPoints obj = new BruteCollinearPoints(testArr);
//            } catch (IllegalArgumentException e){
//                System.out.println("Test 2 Passed");
//            } catch (Exception e) {
//                System.out.println("Test 2 Failed");
//            }
//        }
//        
//        
//        {
//            // duplicates test case
//            Point[] testArr = new Point[5]; 
//            testArr[0] = new Point(4, 4);
//            testArr[1] = new Point(4, 4);
//            testArr[2] = new Point(4, 4);
//            testArr[3] = new Point(4, 4);
//            testArr[4] = new Point(4, 4);
//            try {
//                BruteCollinearPoints obj = new BruteCollinearPoints(testArr);
//            } catch (IllegalArgumentException e){
//                System.out.println("Test 3 Passed");
//            } catch (Exception e) {
//                System.out.println("Test 3 Failed");
//            }
//        }
//        
//         {
//            // 4 collinear points test case
//            Point[] testArr = new Point[2]; 
//            testArr[0] = new Point(1, 1);
//            testArr[1] = new Point(1, 1);
//
//            BruteCollinearPoints obj = new BruteCollinearPoints(testArr);
//            if(obj.numSegments == 2) {
//                System.out.println("Test 4.1 Passed");
//            } else {
//                System.out.println("Test 4.1 Failed");
//            }
//
//            LineSegment[] segments = obj.segments();
//            for(int i = 0; i < segments.length; ++i) {
//                System.out.println(segments[i].toString());
//            }
//        }
    }
}
