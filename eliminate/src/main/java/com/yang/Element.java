package com.yang;


public class Element {
    /**
     * 行
     */
    private int x;
    /**
     * 列
     */
    private int y;
    /**
     * 类型
     */
    private TypeEnum type;

    /**
     * 右方的元素，绑定相同的元素
     */
    private Element right;
    /**
     * 下方元素，绑定相同的元素
     */
    private Element down;

    private Element up;

    private Element left;

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
     * @param left the left to set
     */
    public void setLeft(Element left) {
        if(left.getType() != this.type) return;
        this.left = left;
    }

    /**
     * @param up the up to set
     */
    public void setUp(Element up) {
        if(up.getType() != this.type) return;
        this.up = up;
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

    /**
     * @return the left
     */
    public Element getLeft() {
        return left;
    }

    /**
     * @return the up
     */
    public Element getUp() {
        return up;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}