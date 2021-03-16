package cn.jasonceng.marsrover;

import cn.jasonceng.marsrover.dic.Direction;
import cn.jasonceng.marsrover.dic.Movement;
import cn.jasonceng.marsrover.dic.Status;
import cn.jasonceng.marsrover.dic.Turn;

import java.util.List;

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

    public Feedback execute(List<Instruction> instructionList) {
        //判空，返回成功
        if(instructionList == null || instructionList.isEmpty()) {
            return new Feedback(Status.SUCCESS,0);
        }

        //不为空，循环执行指令
        int i = 0;
        for(i = 0; i < instructionList.size(); i++) {
            Instruction instruction = instructionList.get(i);

            if(instruction == null) {
                return new Feedback(Status.FAIL,i);
            } else {
                Turn turn = instruction.getTurn();
                Movement movement = instruction.getMovement();
                int step = instruction.getStep();
                int x = this.point.getX();
                int y = this.point.getY();

                int positive = (movement == Movement.FORWARD ? 1 : -1) * step;
                int negative = (movement == Movement.FORWARD ? -1 : 1) * step;

                if(turn == Turn.LEFT) {
                    switch (direction) {
                        case NORTH:
                            this.direction = Direction.WEST;
                            if(x + negative < 0) {
                                this.point.setX(0);
                            } else if(x + negative > this.area.getWidth()) {
                                this.point.setX(this.area.getWidth());
                            } else {
                                this.point.setX(x + negative);
                            }
                            break;
                        case EAST:
                            this.direction = Direction.NORTH;
                            if(y + positive < 0) {
                                this.point.setY(0);
                            } else if(y + positive > this.area.getHeight()) {
                                this.point.setY(this.area.getHeight());
                            } else {
                                this.point.setY(y + positive);
                            }
                            break;
                        case SOUTH:
                            this.direction = Direction.EAST;
                            this.point.setX(x + positive);
                            break;
                        case WEST:
                            this.direction = Direction.SOUTH;
                            this.point.setY(y + negative);
                            break;
                    }
                } else if(turn == Turn.RIGHT) {
                    switch (direction) {
                        case NORTH:
                            this.direction = Direction.EAST;
                            this.point.setX(x + positive);
                            break;
                        case EAST:
                            this.direction = Direction.SOUTH;
                            this.point.setY(y + negative);
                            break;
                        case SOUTH:
                            this.direction = Direction.WEST;
                            this.point.setX(x + negative);
                            break;
                        case WEST:
                            this.direction = Direction.NORTH;
                            this.point.setY(y + positive);
                            break;
                    }
                } else { //Direction.NONE 不转向
                    switch (direction) {
                        case NORTH:
                            this.direction = Direction.NORTH;
                            this.point.setY(y + positive);
                            break;
                        case EAST:
                            this.direction = Direction.EAST;
                            this.point.setX(x + positive);
                            break;
                        case SOUTH:
                            this.direction = Direction.SOUTH;
                            this.point.setY(y + negative);
                            break;
                        case WEST:
                            this.direction = Direction.WEST;
                            this.point.setX(x + negative);
                            break;
                    }
                }

            }
        }
        return new Feedback(Status.SUCCESS,instructionList.size());
    }
}
