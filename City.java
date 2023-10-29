import java.util.ArrayList;
import java.util.List;

class City {
    int x;
    int y;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(City other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}