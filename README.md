# UTDD-MarsRover

基于UTDD(Unit Test Driven Development，单元测试驱动开发)的火星车程序Java实现。

## 需求
假设你再某次火星探测任务中负责编写火星车的控制程序，火星车在指定区域内移动，火星车有初始的降落坐标和朝向，每次接受一串指令序列后向地球反馈信息（指令序列执行为任务生命周期，不考虑与地球的持续交互行为）。

注意：

①可移动范围为矩形区域，坐标为整数坐标

②火星车可朝向东、南、西、北四个方向

③火星车可向前或者向后移动整数步长，可左转或者右转90°

④每一个指令序列执行结束后反馈执行结果、坐标、朝向和指令执行条数

⑤若执行指令过程中达到边界，中断当前指令以及后续指令的执行，返回结果

## 任务分解
TDD任务分解原则：

通过任务分解获得的子任务（或子问题）需要满足：
- 任何子任务均可通过测试来验收
- 所有子任务的集合恰好等价于原问题域
- 子任务之间无交集

分解结果：

①初始化区域信息

②初始化火星车状态

③判断指令执行可行性

④执行指令并改变火星车状态

⑤反馈指令序列执行结果

## 领域建模
MarsRover用户故事领域建模：
- Rover - 火星车
- Area - 区域
- Instruction - 指令
- Point - 坐标
- Feedback - 命令执行反馈

枚举类
- Direction -  方向：东、南、西、北
- Movement - 移动：向前、向后
- Stautus - 命令执行结果：成功、失败
- Turn - 转向：左转、右转、不转

## 任务一：初始化区域信息
- 区域为任务承担者
- 以左下角为原点（0,0）建立区域的平面直角坐标系

1、测试区域信息初始化后的长度和宽度（should_return_initial_width_and_height())

传入长度和宽度，验证区域对象的长宽

## 任务二：初始化火星车状态
- 火星车为任务承担者
- 火星车拥有坐标、朝向以及区域信息

1、测试火星车初始化后的初始化坐标（should_return_initial_position）

x、y坐标值与预期相同视为坐标正确

2、测试火星车初始化后的区域信息（should_return_initial_area）

区域长、宽与预期相同视为区域信息正确

3、测试火星车初始化后的朝向（should_return_initial_direction）

东、南、西、北朝向，朝向与预期相同视为正确

## 任务三：判断指令执行可行性
- 火星车为任务承担者，返回Feedback类型对象
- 在本案例中，不考虑指令本身内容是否有问题，只测试指令为空时的执行合法性（可行性）

1、测试指令序列合法性（test_rover_status_when_given_null_instruction_sequence()）

空指令序对象、空指令序列，验证火星车执行指令序列成功

参数化测试：getNullInstructionSequence() {null,new ArrayList<Instruction>()}

2、测试空指令的判断（test_rover_status_when_given_include_null_instruction_sequence()）

如果指令序列中的某条指令为空，停止执行当前空指令以及后续指令，验证火星车执行指令序列失败和指令执行条数(失败指令的执行不计入指令执行条数)。

## 任务四：执行指令并改变火星车状态
### 任务4.1 转向
- 火星车为任务承担者
- 单条指令的执行属于核心任务，也是最复杂的，划分为**转向**和**移动**两步操作

转向操作：

测试转向执行的结果（test_rover_status_when_given_turns()）

（1）验证右转、左转、不转的三种情况的转向结果
```JAVA
getTurns() {
  {Turn.RIGHT, Direction.EAST},
  {Turn.LEFT, Direction.WEST},
  {Turn.NONE, Direction.NORTH}
}
```
（2）优化实现代码

### 任务4.2 移动
- 火星车为任务承担者
- 单条指令的执行属于核心任务，也是最复杂的，划分为**转向**和**移动**两步操作

移动操作：

1、测试指令序列执行成功的结果（test_rover_status_when_not_over_border()）

验证火星车方向和坐标状态是否符合预期更新结果```getInstructionsThatExecuteSuccessfully()```

2、验证指令序列执行超出边界而失败的结果（test_rover_status_when_over_border()）

验证火星车方向和坐标状态因超出边界导致的非预期结果```getInstructionsThatOverBorder()```