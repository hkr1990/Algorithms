import java.util.ArrayList;

public class FastCollinearPoints {
    private int numSegments = 0;
    private LineSegment[] arr;
    private ArrayList<Point> gPoints = new ArrayList<Point>();
    private boolean searchPoint(Point p) {
        for(int i = 0; i < gPoints.size(); ++i)
        {
            if(gPoints.get(i).compareTo(p) == 0) {
                return true;
            }
        }

        return false;
    }
    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
        if (points == null) {
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

        LineSegment[] tempArr = new LineSegment[(size * (size - 1) * (size - 2) * (size -3)) / 24];
        
        for (int p = 0; p < points.length - 4; ++p) {
              ArrayList<Point> subsetOfPoints = new ArrayList<Point>();
              // Arrange them in array : O(n)
              for(int q = p + 1; q < points.length; ++q) {
                  subsetOfPoints.add(points[q]);
              }

              // Sort the array. We should know the size of this array : O(nlogn)
              subsetOfPoints.sort(points[p].slopeOrder()); 

              // Now iterate this array to find all elements with same slope. Find min and max
              for (int ref = 0; ref < subsetOfPoints.size() - 2; ++ref) {
                  // Find max point
                  // Find min point
                  Point max = points[p];
                  Point min = points[p];

                  // Mark the points in the pointInUse
                  int ref_offset_1 = ref + 1;
                  int ref_offset_2 = ref + 2;
                  int ref_offset_n = ref + 3;

                  if(points[p].slopeTo(subsetOfPoints.get(ref)) == points[p].slopeTo(subsetOfPoints.get(ref_offset_1)) && points[p].slopeTo(subsetOfPoints.get(ref_offset_2)) == points[p].slopeTo(subsetOfPoints.get(ref))) {
                      if((this.searchPoint(points[p]) == true) || (this.searchPoint(subsetOfPoints.get(ref)) == true) || (this.searchPoint(subsetOfPoints.get(ref_offset_1)) == true) || (this.searchPoint(subsetOfPoints.get(ref_offset_2)) == true)      ) {
                          continue;
                      }
                      // Find the longest line segment from 4 points and add it to the arr[]
                      ArrayList<Point> collinearPoints = new ArrayList<Point>();
                      collinearPoints.add(points[p]);
                      collinearPoints.add(subsetOfPoints.get(ref));
                      collinearPoints.add(subsetOfPoints.get(ref_offset_1));
                      collinearPoints.add(subsetOfPoints.get(ref_offset_2));
                      gPoints.add(points[p]);
                      gPoints.add(subsetOfPoints.get(ref));
                      gPoints.add(subsetOfPoints.get(ref_offset_1));
                      gPoints.add(subsetOfPoints.get(ref_offset_2));
                      
                      while(ref_offset_n < subsetOfPoints.size() && (points[p].slopeTo(subsetOfPoints.get(ref_offset_n)) == points[p].slopeTo(subsetOfPoints.get(ref))) ) {
                          collinearPoints.add(subsetOfPoints.get(ref_offset_n));
                          gPoints.add(subsetOfPoints.get(ref_offset_n));
                          ++ref_offset_n;
                      }
                      
                      // Find min and max and update the line segment
                      for(int i = 0; i < collinearPoints.size(); ++i) {
                          if(collinearPoints.get(i).compareTo(max) > 0) {
                              max = collinearPoints.get(i);
                          }

                          if(collinearPoints.get(i).compareTo(min) < 0) {
                              min = collinearPoints.get(i);
                          }
                          
                      }
                      
                      tempArr[numSegments] = new LineSegment(max, min);
                      ++numSegments;
                      ref = ref_offset_n;
                  }
              }
              
              // empty the subsetOfPoints for next iteration
        }
        
        arr = new LineSegment[numSegments];
        for(int i = 0; i < numSegments; ++i) {
            arr[i] = tempArr[i];
        }
        
    } 
    
    public int numberOfSegments()        // the number of line segments
    {
        return numSegments;
    }
    
    public LineSegment[] segments()     // the line segments
    {
        return arr.clone();
    }
    
    public static void main(String[] args) {
        // null test case
        // array
//        try {
//            FastCollinearPoints obj = new FastCollinearPoints(null);
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
//                FastCollinearPoints obj = new FastCollinearPoints(testArr);
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
//                FastCollinearPoints obj = new FastCollinearPoints(testArr);
//            } catch (IllegalArgumentException e){
//                System.out.println("Test 3 Passed");
//            } catch (Exception e) {
//                System.out.println("Test 3 Failed");
//            }
//        }
//
//        {
//            // 4 collinear points test case
//            Point[] testArr = new Point[10]; 
//            testArr[0] = new Point(1, 1);
//            testArr[1] = new Point(2, 2);
//            testArr[2] = new Point(3, 3);
//            testArr[3] = new Point(4, 4);
//            testArr[4] = new Point(5, 5);
//            testArr[5] = new Point(6, 6);
//            testArr[6] = new Point(0, 6);
//            testArr[7] = new Point(5, 6);
//            testArr[8] = new Point(1, 6);
//            testArr[9] = new Point(9, 6);
//
//            FastCollinearPoints obj = new FastCollinearPoints(testArr);
//            if(obj.numSegments == 1) {
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
