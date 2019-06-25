package com.yang;

import java.util.List;

public class Checkerboard {
    private int xSize;
    private int ySize;
    private Element[][] elements;

    public Checkerboard(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.elements = new Element[xSize][ySize];
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
  
    public List<EliminateType> out() {
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {
                int rl = rightLevel(elements[x][y], 1);
                int dl = downLevel(elements[x][y], 1);
                System.out.print(rl+","+dl+"\t");

            }
            System.out.println();
        }
        return null;
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
}