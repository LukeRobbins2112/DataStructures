package pattern_recognition;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
	
	private LineSegment[] lineSegments;
	private int N;
	private final Comparator<Point> BY_SLOPE;

	public FastCollinearPoints(Point[] points) {
		
		this.N = points.length;
		
		ArrayList<LineSegment> segments = new ArrayList<>();
		Point[] copy = Arrays.copyOf(points, this.N);
		Arrays.sort(copy);

		validateInput(copy);
		
		Point p = copy[0];
		this.BY_SLOPE = p.slopeOrder();
		
		int slopeCount = 1;
		
		Arrays.sort(copy, this.BY_SLOPE);
		
		for (int i = 2; i < this.N; i++){
			Point current = copy[i];
			Point prev = copy[i - 1];
			if (p.slopeTo(current) == p.slopeTo(prev)){
				slopeCount++;
				if (slopeCount == 3){
					segments.add(new LineSegment(p, current));
					slopeCount = 0;
				}	
			}
			else
				slopeCount = 0;	
		}
		
		
		this.lineSegments = segments.toArray(new LineSegment[segments.size()]);

	}

	public int numberOfSegments() {
		return this.lineSegments.length;
	}

	/**
	 * The method segments() should include each maximal line segment containing
	 * 4 (or more) points exactly once. For example, if 5 points appear on a
	 * line segment in the order p→q→r→s→t, then do not include the subsegments
	 * p→s or q→t.
	 * 
	 */

	public LineSegment[] segments() {
		return Arrays.copyOf(this.lineSegments, this.lineSegments.length);
	}
	
	
	
	private void validateInput(Point[] pts){
		if (pts == null)
			throw new IllegalArgumentException("Null array");
		
		for (Point p : pts){
			if (p == null)
				throw new IllegalArgumentException("Null Point in list");
		}
		
		for (int i = 0; i < pts.length - 1; i++){
			if (pts[i].compareTo(pts[i+1]) == 0)
				throw new IllegalArgumentException();
		}
	}

}