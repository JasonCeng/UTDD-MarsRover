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
    private String message;

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
            this.message = "无效指令序列";
            return new Feedback(Status.SUCCESS,0, this.message);
        }

        //不为空，循环执行指令
        int i = 0;
        for(i = 0; i < instructionList.size(); i++) {
            Instruction instruction = instructionList.get(i);

            if(instruction == null) {
                this.message = "存在空指令";
                return new Feedback(Status.FAIL,i,this.message);
            } else {
                Feedback feedback;
                Boolean isOverBorder;
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
//                            this.point.setX(x + negative);
                            isOverBorder = this.executeX(x + negative);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case EAST:
                            this.direction = Direction.NORTH;
//                            this.point.setY(y + positive);
                            isOverBorder = this.executeY(y + positive);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case SOUTH:
                            this.direction = Direction.EAST;
//                            this.point.setX(x + positive);
                            isOverBorder = this.executeX(x + positive);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case WEST:
                            this.direction = Direction.SOUTH;
//                            this.point.setY(y + negative);
                            isOverBorder = this.executeY(y + negative);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                    }
                } else if(turn == Turn.RIGHT) {
                    switch (direction) {
                        case NORTH:
                            this.direction = Direction.EAST;
//                            this.point.setX(x + positive);
                            isOverBorder = this.executeX(x + positive);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case EAST:
                            this.direction = Direction.SOUTH;
//                            this.point.setY(y + negative);
                            isOverBorder = this.executeY(y + negative);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case SOUTH:
                            this.direction = Direction.WEST;
//                            this.point.setX(x + negative);
                            isOverBorder = this.executeX(x + negative);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case WEST:
                            this.direction = Direction.NORTH;
//                            this.point.setY(y + positive);
                            isOverBorder = this.executeY(y + positive);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                    }
                } else { //Direction.NONE 不转向
                    switch (direction) {
                        case NORTH:
                            this.direction = Direction.NORTH;
//                            this.point.setY(y + positive);
                            isOverBorder = this.executeY(y + positive);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case EAST:
                            this.direction = Direction.EAST;
//                            this.point.setX(x + positive);
                            isOverBorder = this.executeX(x + positive);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case SOUTH:
                            this.direction = Direction.SOUTH;
//                            this.point.setY(y + negative);
                            isOverBorder = this.executeY(y + negative);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                        case WEST:
                            this.direction = Direction.WEST;
//                            this.point.setX(x + negative);
                            isOverBorder = this.executeX(x + negative);
                            if(isOverBorder) {
                                return new Feedback(Status.FAIL,i,this.message);
                            }
                            break;
                    }
                }
            }
        }
        this.message = "指令执行成功";
        return new Feedback(Status.SUCCESS,instructionList.size(),this.message);
    }

    private boolean executeX(int afterX) {
        Boolean isOverBorder;
        if(afterX < 0) {
            this.point.setX(0);
            this.message = "指令执行中超出边界";
            isOverBorder = true;
        } else if(afterX > this.area.getWidth()) {
            this.point.setX(this.area.getWidth());
            this.message = "指令执行中超出边界";
            isOverBorder = true;
        } else {
            this.point.setX(afterX);
            isOverBorder = false;
        }
        return isOverBorder;
    }

    private boolean executeY(int afterY) {
        Boolean isOverBorder;
        if(afterY < 0) {
            this.point.setY(0);
            this.message = "指令执行中超出边界";
            isOverBorder = true;
        } else if(afterY > this.area.getHeight()) {
            this.point.setY(this.area.getHeight());
            this.message = "指令执行中超出边界";
            isOverBorder = true;
        } else {
            this.point.setY(afterY);
            isOverBorder = false;
        }
        return isOverBorder;
    }
}
