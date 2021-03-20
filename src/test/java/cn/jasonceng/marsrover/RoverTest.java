package cn.jasonceng.marsrover;

import cn.jasonceng.marsrover.dic.Direction;
import cn.jasonceng.marsrover.dic.Movement;
import cn.jasonceng.marsrover.dic.Status;
import cn.jasonceng.marsrover.dic.Turn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RoverTest
 * @Description: 火星车类测试
 * @Author
 * @Date 2021/3/15
 * @Version 1.0
 */
public class RoverTest {
    private Rover rover;

    @BeforeEach
    private void initRover() {
        rover = new Rover(new Point(5, 5), new Area(10,12), Direction.NORTH);
    }

    /**
     * 任务二：测试火星车初始化后的初始化坐标
     * x、y坐标值与预期相同视为坐标正确
     */
    @Test
    public void should_return_initial_position() {
        //given
//        Rover rover = new Rover(new Point(5, 5), new Area(10, 12), Direction.NORTH);

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
//        Rover rover = new Rover(new Point(5, 5), new Area(10,12), Direction.NORTH);

        //when

        //then
        Assertions.assertEquals(10, rover.getArea().getWidth());
        Assertions.assertEquals(12, rover.getArea().getHeight());
    }

    /**
     * 任务二：测试火星车初始化后的朝向
     * 东、南、西、北朝向，朝向与预期相同视为正确
     */
    @ParameterizedTest
    @MethodSource("getDirections")
    public void should_return_initial_direction(Direction initDirection, Direction expectDirection) {
        //given
        Rover rover = new Rover(new Point(5, 5), new Area(10,12),initDirection);

        //when

        //then
        Assertions.assertEquals(expectDirection, rover.getDirection());
    }

    private static Object[][] getDirections() {
        return new Object[][] {
                {Direction.NORTH, Direction.NORTH},
                {Direction.EAST, Direction.EAST},
                {Direction.SOUTH, Direction.SOUTH},
                {Direction.WEST, Direction.WEST}
        };
    }

    /**
     * 任务三：测试指令序列合法性
     * 参数化测试空指令序对象、空指令序列，验证火星车执行指令序列成功
     */
    @ParameterizedTest
    @MethodSource("getNullInstructionSequence")
    public void test_rover_status_when_given_null_instruction_sequence(List<Instruction> instructionList) {
        //given
//        Rover rover = new Rover(new Point(5, 5), new Area(10,12), Direction.NORTH);

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(Status.SUCCESS, feedback.getStatus());
    }

    private static Object[] getNullInstructionSequence() {
        return new Object[] {
            null,
            new ArrayList<>()
        };
    }

    /**
     * 任务三：测试空指令的判断
     * 参数化测试指令序列，若某条指令为空，停止执行当前空指令以及后续指令，验证火星车执行指令序列失败和指令执行条数(失败指令的执行不计入指令执行条数)
     */
    @ParameterizedTest
    @MethodSource("getNullInstruction")
    public void test_rover_status_when_given_include_null_instruction_sequence(List<Instruction> instructionList) {
        //given
//        Rover rover = new Rover(new Point(5, 5), new Area(10,12), Direction.NORTH);

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(Status.FAIL, feedback.getStatus());
        Assertions.assertEquals(1,feedback.getExeInstructionSize());
    }

    private static Object[] getNullInstruction() {
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(Turn.NONE, Movement.FORWARD, 0));
        instructionList.add(null);
        instructionList.add(new Instruction(Turn.LEFT, Movement.FORWARD, 0));
        return new Object[] {
            instructionList
        };
    }

    /**
     * 任务四：执行指令并改变火星车状态 -- 转向
     * 测试转向执行的结果：验证右转、左转、不转的三种情况的转向结果
     */
    @ParameterizedTest
    @MethodSource("getTurns")
    public void test_rover_status_when_given_turns(Turn turn, Direction expectDirection) {
        //given
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(turn, Movement.FORWARD, 0));

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(expectDirection,rover.getDirection());
    }
    private static Object[][] getTurns() {
        return new Object[][] {
            {Turn.RIGHT, Direction.EAST},
            {Turn.LEFT, Direction.WEST},
            {Turn.NONE, Direction.NORTH}
        };
    }

    /**
     * 任务四：执行指令并改变火星车状态 -- 移动
     * 测试指令序列执行成功的结果：验证火星车[方向]和[坐标]状态是否符合预期更新结果
     */
    @ParameterizedTest
    @MethodSource("getInstructionsThatExecuteSuccessfully")
    public void test_rover_status_when_not_over_border(Turn turn, Movement movement, int step, Direction expectDirection, Point expectPoint) {
        //given
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(turn,movement,step));

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(expectDirection,rover.getDirection());
        Assertions.assertEquals(expectPoint.getX(), rover.getPoint().getX());
        Assertions.assertEquals(expectPoint.getY(), rover.getPoint().getY());
    }
    private static Object[][] getInstructionsThatExecuteSuccessfully() {
        return new Object[][] {
                {Turn.NONE, Movement.FORWARD, 1, Direction.NORTH, new Point(5,6)},
                {Turn.LEFT, Movement.FORWARD, 1, Direction.WEST, new Point(4,5)},
                {Turn.RIGHT, Movement.FORWARD, 1, Direction.EAST, new Point(6,5)},
                {Turn.NONE, Movement.BACK, 1, Direction.NORTH, new Point(5,4)},
                {Turn.LEFT, Movement.BACK, 1, Direction.WEST, new Point(6,5)},
                {Turn.RIGHT, Movement.BACK, 1, Direction.EAST, new Point(4,5)}
        };
    }

    /**
     * 任务四：执行指令并改变火星车状态 -- 移动
     * 验证指令序列执行超出边界而失败的结果：验证火星车[方向]和[坐标]状态因超出边界导致的非预期结果
     */
    @ParameterizedTest
    @MethodSource("getInstructionsThatOverBorder")
    public void test_rover_status_when_over_border(Turn turn, Movement movement, int step, Direction expectDirection, Point expectPoint) {
        //given
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(turn,movement,step));

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(expectDirection,rover.getDirection());
        Assertions.assertEquals(expectPoint.getX(), rover.getPoint().getX());
        Assertions.assertEquals(expectPoint.getY(), rover.getPoint().getY());
    }
    private static Object[][] getInstructionsThatOverBorder() {
        return new Object[][] { //初始化坐标：(x:5,y5) width：10，height：12
                {Turn.NONE, Movement.FORWARD, 20, Direction.NORTH, new Point(5,12)},
                {Turn.LEFT, Movement.FORWARD, 20, Direction.WEST, new Point(0,5)},
                {Turn.RIGHT, Movement.FORWARD, 20, Direction.EAST, new Point(10,5)},
                {Turn.NONE, Movement.BACK, 20, Direction.NORTH, new Point(5,0)},
                {Turn.LEFT, Movement.BACK, 20, Direction.WEST, new Point(10,5)},
                {Turn.RIGHT, Movement.BACK, 20, Direction.EAST, new Point(0,5)}
        };
    }

    /**
     * 任务五：反馈指令执行结果
     * 验证空指令序列、空指令序列对象执行成功结果
     */
    @ParameterizedTest
    @MethodSource("getNullInstructionSequence")
    public void should_return_success_and_warning_when_given_null_instruction_sequence(List<Instruction> instructionList) {
        //given

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(Status.SUCCESS, feedback.getStatus());
        Assertions.assertEquals(0,feedback.getExeInstructionSize());
        Assertions.assertEquals("无效指令序列",feedback.getMessage());
    }

    /**
     * 任务五：反馈指令执行结果
     * 验证指令为空的返回结果
     */
    @Test
    public void should_return_fail_when_exist_null_instruction() {
        //given
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(Turn.NONE, Movement.FORWARD, 1));
        instructionList.add(null);
        instructionList.add(new Instruction(Turn.LEFT, Movement.FORWARD, 2));

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(Status.FAIL, feedback.getStatus());
        Assertions.assertEquals(1,feedback.getExeInstructionSize());
        Assertions.assertEquals("存在空指令",feedback.getMessage());
    }

    /**
     * 任务五：反馈指令执行结果
     * 验证指令正常执行的成功结果
     */
    @Test
    public void should_return_success_when_not_over_border() {
        //given
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(Turn.NONE, Movement.FORWARD, 1));
        instructionList.add(new Instruction(Turn.RIGHT, Movement.FORWARD, 1));
        instructionList.add(new Instruction(Turn.LEFT, Movement.FORWARD, 2));

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(Status.SUCCESS, feedback.getStatus());
        Assertions.assertEquals(3,feedback.getExeInstructionSize());
        Assertions.assertEquals("指令执行成功",feedback.getMessage());
    }

    /**
     * 任务五：反馈指令执行结果
     * 验证指令因超出边界的失败结果
     */
    @Test
    public void should_return_fail_when_over_border() {
        //given
        List<Instruction> instructionList = new ArrayList<>();
        instructionList.add(new Instruction(Turn.NONE, Movement.FORWARD, 20));
        instructionList.add(new Instruction(Turn.RIGHT, Movement.FORWARD, 1));
        instructionList.add(new Instruction(Turn.LEFT, Movement.FORWARD, 2));

        //when
        Feedback feedback = rover.execute(instructionList);

        //then
        Assertions.assertEquals(Status.FAIL, feedback.getStatus());
        Assertions.assertEquals(0,feedback.getExeInstructionSize());
        Assertions.assertEquals("指令执行中超出边界",feedback.getMessage());
    }

}
