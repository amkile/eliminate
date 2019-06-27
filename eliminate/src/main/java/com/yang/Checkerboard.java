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

    /**
     * 测试
     */
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

    /**
     * 在元素集合变更后重新组织元素之间的关系
     */
    public void reload() {
        // x表示行，y表示列
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {
                // 相同元素行方向上上一个元素与下一个元素关联
                if(x != 0) {
                    elements[x-1][y].setDown(elements[x][y]);
                    elements[x][y].setUp(elements[x-1][y]);
                }
                // 列方向关联
                if(y != 0) {
                    elements[x][y-1].setRight(elements[x][y]);
                    elements[x][y].setLeft(elements[x][y-1]);
                }
            }
        }
    }
    
    public void out() {
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {

                if(eliminateMap.get(x+","+y)==null) {
                    // eliminateElement(elements[x][y]);
                    eliminateElement(null,this.eliminateMap,elements[x][y]);
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
     * 执行搜索，通过当前节点向四方蔓延，大概算回溯法的一种
     * @param et 消除类型，解空间
     * @param eliminateMap 空间遍历集合,存放符合消除的已经经过遍历的元素
     * @param e 根元素
     */
    private void eliminateElement(        
    EliminateType et, 
    Map<String,Element>eliminateMap, 
    Element e) {
        // 结束条件
        if(e==null) return;
        if(eliminateMap.get(e.getX()+","+e.getY())!=null)return;

        
        
        int rl = rightLevel(e, 0);
        int ll = leftLevel(e, 0);
        int dl = downLevel(e, 0);
        int ul = upLevel(e, 0);
        // 以三消为例，rl+ll = 2
        if ((rl+ll+1) >= ELEMENT_SIZE) {
            Element etemp = e;
            eliminateMap.put(etemp.getX()+","+etemp.getY(),etemp);
            eliminateElement(et,eliminateMap,etemp.getRight());
            eliminateElement(et,eliminateMap,etemp.getLeft());
        }
        
        if ((dl+ul+1) >= ELEMENT_SIZE) {
            Element etemp = e;
            eliminateMap.put(etemp.getX()+","+etemp.getY(),etemp);
            eliminateElement(et,eliminateMap,etemp.getDown());
            eliminateElement(et,eliminateMap,etemp.getUp());
        }

    }



    /**
     * 计算三消，废弃，不容易理解,有bug
     * @param e
     */
    private void eliminateElement(Element e) {
        if(e==null) return;

        // 去除重复计算，否则元素连续超过四个，eliminateElement方法会递归重复添加
        // 例如：[1,2 [1,3 1,4 1,5] 1,4 1,5] 中括号属于同一个栈中的元素
        // 遍历过的子节点，其子节点的子节点一定遍历过
        if(eliminateMap.get(e.getX()+","+e.getY())!=null)return;
        // 节点深度
        int rl = rightLevel(e, 1);
        int dl = downLevel(e, 1);

        // 右遍历
        if(rl >= ELEMENT_SIZE) {
            Element etemp = e;
            // 这里用for循环原因是为了，三消及其以上中最后两个元素添加到集合中
            for(int i=0;i<rl;i++) {
                eliminateMap.put(etemp.getX()+","+etemp.getY(),etemp);
                etemp = etemp.getRight();
                eliminateElement(etemp);
            }
        }

        // 下遍历
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
    private int downLevel(Element e, int level) {
        if(e.getDown() == null) {
            return level;
        }
        return downLevel(e.getDown(), level+1);
    }
    private int leftLevel(Element e, int level) {
        if(e.getLeft() == null) {
            return level;
        }
        return leftLevel(e.getLeft(), level+1);
    }
    private int upLevel(Element e, int level) {
        if(e.getUp() == null) {
            return level;
        }
        return upLevel(e.getUp(), level+1);
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