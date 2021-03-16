package cn.jasonceng.marsrover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: AreaTest
 * @Description: 区域类测试
 * @Author
 * @Date 2021/3/15
 * @Version 1.0
 */
public class AreaTest {
    /**
     * 任务一：初始化区域信息
     * 测试区域信息初始化后的长度和宽度
     */
    @Test
    public void should_return_initial_width_and_height() {
        //given
        Area area = new Area(10, 12);
        //when

        //then
        Assertions.assertEquals(10, area.getWidth());
        Assertions.assertEquals(12,area.getHeight());
    }
}
