package battleship;

import java.util.Objects;

public class MyPoint {

    private int x;
    private int y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!(o instanceof MyPoint)) {
            return false;
        }

        return (x == ((MyPoint) o).x && y == ((MyPoint)o).y);
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
