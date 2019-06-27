package com.yang;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ){
        // long s = System.currentTimeMillis();
        // Checkerboard c = new Checkerboard(10, 10);
        // c.out();
        // System.out.println(c.getEliminateMap());
        // long e = System.currentTimeMillis();

        // System.out.println(e-s+"ms");

        long s = System.currentTimeMillis();
        Checkerboard c = new Checkerboard();
        Element[][] elements = new Element[10][10];
        for(int x=0; x<elements.length; x++) {
            for (int y=0; y<elements[x].length; y++) {
                elements[x][y] = new Element(x, y);
            }
        }  
        elements[0][3] = new Element(0, 3,TypeEnum.BLUE);
        elements[1][3] = new Element(1, 3,TypeEnum.BLUE);
        elements[2][3] = new Element(2, 3,TypeEnum.BLUE);
        elements[1][2] = new Element(1, 2,TypeEnum.BLUE);
        elements[1][4] = new Element(1, 4,TypeEnum.BLUE);

        // elements[0][0] = new Element(0, 0,TypeEnum.YELLOW);
        // elements[0][1] = new Element(0, 1,TypeEnum.YELLOW);

        c.setElements(elements);
        c.reload();
        long cd = System.currentTimeMillis();
        c.out();
        System.out.println(c.getEliminateMap());
        long e = System.currentTimeMillis();
        System.out.println(cd-s+"ms");
        System.out.println(e-s+"ms");
    }

}


