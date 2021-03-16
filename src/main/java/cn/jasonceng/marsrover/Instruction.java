package cn.jasonceng.marsrover;

import cn.jasonceng.marsrover.dic.Movement;
import cn.jasonceng.marsrover.dic.Turn;

/**
 * @ClassName: Instruction
 * @Description:
 * @Author
 * @Date 2021/3/16
 * @Version 1.0
 */
public class Instruction {
    private Turn turn;
    private Movement movement;
    private int step;

    public Instruction(Turn turn, Movement movement, int step) {
        this.turn = turn;
        this.movement = movement;
        this.step = step;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
