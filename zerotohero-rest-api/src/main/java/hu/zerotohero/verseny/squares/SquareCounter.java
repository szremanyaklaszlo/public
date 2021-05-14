package hu.zerotohero.verseny.squares;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SquareCounter {

    public int count(List<Point> points) {
        int result = 0;
        if(points != null) {
            for (Point point : points) {
                for (int distance = 1; distance < Math.min(maxX(points), maxY(points)); distance++) {
                    if (isNormalSquare(point, points, distance)) {
                        result++;
                    }
                    if (isDiagonalSquare(point, points, distance)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private int maxX(List<Point> points) {
        return points.stream().max(Comparator.comparing(Point::getX)).get().getX();
    }

    private int maxY(List<Point> points) {
        return points.stream().max(Comparator.comparing(Point::getY)).get().getY();
    }

    private boolean isNormalSquare(Point point, List<Point> points, int distance) {
        return isCoordinate(new Point(point.getX() + distance, point.getY()), points) &&
                isCoordinate(new Point(point.getX(), point.getY() + distance), points) &&
                isCoordinate(new Point(point.getX() + distance, point.getY() + distance), points);
    }

    private boolean isDiagonalSquare(Point point, List<Point> points, int distance) {
        return isCoordinate(new Point(point.getX() + distance, point.getY() + distance), points) &&
        isCoordinate(new Point(point.getX() + distance*2, point.getY()), points) &&
        isCoordinate(new Point(point.getX() + distance, point.getY() - distance), points);
    }

    private boolean isCoordinate(Point searchedPoint, List<Point> points) {
        return points.stream().anyMatch(point -> point.equals(searchedPoint));
    }

}
