package cn.jasonceng.marsrover.dic;

public enum Status {
    SUCCESS(0,"成功"),FAIL(1,"失败");

    private int index;
    private String des;

    Status(int index, String des) {
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
