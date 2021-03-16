package cn.jasonceng.marsrover;

public enum Direction {
    NORTH(0,"北"),EAST(1,"东"),SOUTH(2,"南"),WEST(3,"西");
    private int index;
    private String des;
    Direction(int index, String des) {
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
