package com.yang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkerboard {
    private static final int ELEMENT_SIZE = 3;
    private int xSize;
    private int ySize;
    private Element[][] elements;
    private Map<String,Element> eliminateMap;

    public Checkerboard() {
        this.eliminateMap = new HashMap<String,Element>();
    }

    public Checkerboard(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.elements = new Element[xSize][ySize];
        this.eliminateMap = new HashMap<String,Element>(xSize*ySize);
        loading();
    }

    private void loading() {
        // x表示行，y表示列
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {
                elements[x][y] = new Element(x, y);
                // 相同元素行方向上上一个元素与下一个元素关联，类似于链表
                if(x != 0) {
                    elements[x-1][y].setDown(elements[x][y]);
                }
                // 列方向关联
                if(y != 0) {
                    elements[x][y-1].setRight(elements[x][y]);
                }
            }
        }
    }

    public void reload() {
        // x表示行，y表示列
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {
                // 相同元素行方向上上一个元素与下一个元素关联，类似于链表
                if(x != 0) {
                    elements[x-1][y].setDown(elements[x][y]);
                }
                // 列方向关联
                if(y != 0) {
                    elements[x][y-1].setRight(elements[x][y]);
                }
            }
        }
    }
  
    public void out() {
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {

                if(eliminateMap.get(x+","+y)==null) {
                    eliminateElement(elements[x][y]);
                }

                if(eliminateMap.get(x+","+y)!=null){
                    System.out.format("\33[32;3m%s",elements[x][y]);
                    System.out.print("\033[0m\t");
                } else {
                    System.out.print(elements[x][y]+"\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * 计算消除的元素
     */
    private void eliminateElement(Element e) {
        if(e==null) return;
        // 需要重新设计
        // 去除重复计算，否则元素连续超过四个，eliminateElement方法会递归重复添加
        // 例如：[1,2 [1,3 1,4 1,5] 1,4 1,5] 中括号属于同一个栈中的元素
        if(eliminateMap.get(e.getX()+","+e.getY())!=null)return;
        int rl = rightLevel(e, 1);
        int dl = downLevel(e, 1);

        if(rl >= ELEMENT_SIZE) {
            Element etemp = e;
            for(int i=0;i<rl;i++) {
                eliminateMap.put(etemp.getX()+","+etemp.getY(),etemp);
                etemp = etemp.getRight();
                eliminateElement(etemp);
            }
        }

        if(dl >= ELEMENT_SIZE) {
            Element etemp = e;
            for(int i=0;i<dl;i++) {
                eliminateMap.put(etemp.getX()+","+etemp.getY(),etemp);
                etemp = etemp.getDown();
                eliminateElement(etemp);
            }
        }
    }


    /**
     * 右元素层级
     * @param e 元素
     * @param level 层级，初始为1
     * @return 层级
     */
    private int rightLevel(Element e, int level) {
        if(e.getRight() == null) {
            return level;
        }
        
        return rightLevel(e.getRight(), level+1);
    }

    /**
     * 下元素层级
     * @param e 元素
     * @param level 层级，初始为1
     * @return 层级
     */
    private int downLevel(Element e, int level) {
        if(e.getDown() == null) {
            return level;
        }
        
        return downLevel(e.getDown(), level+1);
    }

    /**
     * @return the xSize
     */
    public int getxSize() {
        return xSize;
    }

    /**
     * @return the ySize
     */
    public int getySize() {
        return ySize;
    }

    /**
     * @return the eliminateMap
     */
    public Map<String, Element> getEliminateMap() {
        return eliminateMap;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(Element[][] elements) {
        this.elements = elements;
    }
}