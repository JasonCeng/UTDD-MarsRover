package cn.jasonceng.marsrover.dic;

public enum Movement {
    FORWARD(0,"向前"),BACK(1,"向后");

    private int index;
    private String des;

    Movement(int index, String des) {
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
