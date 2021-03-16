package cn.jasonceng.marsrover.dic;

public enum Turn {
    LEFT(0,"左转"),RIGHT(1,"右转"),NONE(2,"不转");

    private int index;
    private String des;

    Turn(int index, String des) {
        this.index = index;
        this.des = des;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
