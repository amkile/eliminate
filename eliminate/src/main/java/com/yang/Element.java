package com.yang;


public class Element {
    private int x;
    private int y;
    private TypeEnum type;

    /**
     * 右方的元素，绑定相同的元素
     */
    private Element right;
    /**
     * 下方元素
     */
    private Element down;

    public Element(int x,int y, TypeEnum type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Element(int x,int y) {
        this(x, y, TypeEnum.values()[(int)(Math.random()*TypeEnum.values().length)]);
    }


    /**
     * @param right the right to set
     */
    public void setRight(Element right) {
        if(right.getType() != this.type) return;
        this.right = right;
    }

    /**
     * @param down the down to set
     */
    public void setDown(Element down) {
        if(down.getType() != this.type) return;
        this.down = down;
    }

    /**
     * @return the down
     */
    public Element getDown() {
        return down;
    }

    /**
     * @return the right
     */
    public Element getRight() {
        return right;
    }

    /**
     * @return the type
     */
    public TypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return "["+type+","+right+","+down+"]";
    }

}