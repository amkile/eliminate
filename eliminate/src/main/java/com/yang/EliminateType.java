package com.yang;

/**
 * 1.根据元素上和左相邻元素来判定元素的连续性 
 */
public class EliminateType {

    private Element[] elements;

    /**
     * 0为特殊十字消除
     * 大于等于三的消除为为普通消除
     */
    private int type;

    public EliminateType(Element[] elements, int type) {
        this.elements = elements;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public Element[] gElements() {
        return elements;
    }

}