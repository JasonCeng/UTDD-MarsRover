package cn.jasonceng.marsrover;

/**
 * @ClassName: Area
 * @Description:
 * @Author
 * @Date 2021/3/15
 * @Version 1.0
 */
public class Area {
    private int width;
    private int height;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
