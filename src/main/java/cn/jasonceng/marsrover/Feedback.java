package cn.jasonceng.marsrover;

import cn.jasonceng.marsrover.dic.Status;

/**
 * @ClassName: Feedback
 * @Description:
 * @Author
 * @Date 2021/3/16
 * @Version 1.0
 */
public class Feedback {
    private Status status;
    private int exeInstructionSize;
    private String message;

    public Feedback(Status status, int exeInstructionSize, String message) {
        this.status = status;
        this.exeInstructionSize = exeInstructionSize;
        this.message = message;
    }

    public Feedback() {

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getExeInstructionSize() {
        return exeInstructionSize;
    }

    public void setExeInstructionSize(int exeInstructionSize) {
        this.exeInstructionSize = exeInstructionSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
