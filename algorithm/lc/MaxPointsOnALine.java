package algorithm.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPointsOnALine {

  static class Point {
    int x;
    int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }

  public class Solution {

    class Line {
      boolean isVertical;
      double slope; // if is vertical, slope is 0
      double intercept; // if is vertical, intercept is x

      public Line(boolean isVertical, double slope, double intercept) {
        this.isVertical = isVertical;
        this.slope = slope;
        this.intercept = intercept;
      }

      public boolean equals(Object o) {
        if (o instanceof Line) {
          Line other = (Line) o;
          return this.isVertical == other.isVertical
              && this.slope == other.slope && this.intercept == other.intercept;
        }
        return false;
      }

      public int hashCode() {
        return (int) (this.slope * 107 + this.intercept * 57);
      }

    }
    
    /**
     * Since pointer has no equals and hashCode method, this wrapper is necessary.
     *
     */
    class PointWrapper {
      Point point;

      public PointWrapper(Point p) {
        this.point = p;
      }

      public int hashCode() {
        return point.x * 107 + point.y * 17;
      }

      public boolean equals(Object o) {
        if (o instanceof PointWrapper) {
          PointWrapper other = (PointWrapper) o;
          return this.point.x == other.point.x && this.point.y == other.point.y;
        }
        return false;
      }

    }
    
    class Pair {
      Point point;
      int count;

      public Pair(Point point, int count) {
        this.point = point;
        this.count = count;
      }
    }

    public int maxPoints(Point[] points) {
      // IMPORTANT: Please reset any member data you declared, as
      // the same Solution instance will be reused for each test case.
      if (points.length == 0) {
        return 0;
      }
      
      // aggregate the points
      Map<PointWrapper, Integer> aggregatedPoint = new HashMap<PointWrapper, Integer>();
      for (Point p : points) {
        PointWrapper wrapper = new PointWrapper(p);
        Integer count = aggregatedPoint.get(wrapper);
        if (count == null) {
          count = 0;
        }
        count += 1;
        aggregatedPoint.put(wrapper, count);
      }
      
      List<Pair> pointList = new ArrayList<Pair>();
      for (Map.Entry<PointWrapper, Integer> entry : aggregatedPoint.entrySet()) {
        pointList.add(new Pair(entry.getKey().point, entry.getValue()));
      }
      
      if (pointList.size() == 1) {
        return pointList.get(0).count;
      }

      // get all the possible lines
      Map<Line, Integer> index = new HashMap<Line, Integer>(100);
      
      for (int i = 0; i < pointList.size() - 1; ++i) {
        Point first = pointList.get(i).point;
        for (int j = i + 1; j < pointList.size(); ++j) {
          Point second = pointList.get(j).point;
          
          double diffX = first.x - second.x;
          Line line = null;
          if (diffX != 0) {
            double slope = (first.y - second.y) / diffX;
            double intercept = first.y - slope * first.x;
            line = new Line(false, slope, intercept);
          } else { // vertical line
            double slope = 0;
            double intercept = first.x;
            line = new Line(true, slope, intercept);
          }
          
          index.put(line, 0);
        }
      }
      
      // check the belongness of each point
      for (Pair pair : pointList) {
        Point p = pair.point;
        int numPoints = pair.count;
        for (Map.Entry<Line, Integer> entry : index.entrySet()) {
          Line line = entry.getKey();
          int count = entry.getValue();
          if (line.isVertical) {
            if (p.x == line.intercept) {
              index.put(line, count + numPoints);
            }
          }
          else if (Math.abs(line.slope * p.x + line.intercept - p.y) < 0.001){
            index.put(line, count + numPoints);
          }
        }
      }
      
      int max = 0;
      for (Map.Entry<Line, Integer> entry : index.entrySet()) {
        max = Math.max(entry.getValue(), max);
      }

      return max;
    }
  }

}
