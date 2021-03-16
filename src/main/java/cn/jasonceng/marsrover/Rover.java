package cn.jasonceng.marsrover;

/**
 * @ClassName: Rover
 * @Description:
 * @Author
 * @Date 2021/3/15
 * @Version 1.0
 */
public class Rover {
    private Point point;
    private Area area;
    private Direction direction;

    public Rover(Point point, Area area, Direction direction) {
        this.point = point;
        this.area = area;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
