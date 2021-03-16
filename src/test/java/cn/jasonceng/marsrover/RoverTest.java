package cn.jasonceng.marsrover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: RoverTest
 * @Description: 火星车类测试
 * @Author
 * @Date 2021/3/15
 * @Version 1.0
 */
public class RoverTest {
    /**
     * 任务二：测试火星车初始化后的初始化坐标
     * x、y坐标值与预期相同视为坐标正确
     */
    @Test
    public void should_return_initial_position() {
        //given
        Rover rover = new Rover(new Point(5, 5), new Area(10, 12), Direction.NORTH);

        //when

        //then
        Assertions.assertEquals(5, rover.getPoint().getX());
        Assertions.assertEquals(5, rover.getPoint().getY());
    }

    /**
     * 任务二：测试火星车初始化后的区域信息
     * 区域长、宽与预期相同视为区域信息正确
     */
    @Test
    public void should_return_initial_area() {
        //given
        Rover rover = new Rover(new Point(5, 5), new Area(10,12), Direction.NORTH);

        //when

        //then
        Assertions.assertEquals(10, rover.getArea().getWidth());
        Assertions.assertEquals(12, rover.getArea().getHeight());
    }

    /**
     * 任务二：测试火星车初始化后的朝向
     * 东、南、西、北朝向，朝向与预期相同视为正确
     */
    @Test
    public void should_return_initial_direction() {
        //given
        Rover rover = new Rover(new Point(5, 5), new Area(10,12),Direction.NORTH);

        //when

        //then
//        Assertions.assertEquals("东", rover.getArea().getWidth());
//        Assertions.assertEquals("西", rover.getArea().getHeight());
//        Assertions.assertEquals("南", rover.getArea().getHeight());
        Assertions.assertEquals(Direction.NORTH, rover.getDirection());
    }
}
