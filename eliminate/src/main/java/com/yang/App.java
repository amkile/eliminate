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
        long s = System.currentTimeMillis();
        Checkerboard c = new Checkerboard(10, 10);
        c.out();
        System.out.println(c.getEliminateMap());
        long e = System.currentTimeMillis();

        System.out.println(e-s+"ms");

        // Checkerboard c = new Checkerboard();
        // Element[][] elements = new Element[10][10];
        // for(int x=0; x<elements.length; x++) {
        //     for (int y=0; y<elements[x].length; y++) {
        //         elements[x][y] = new Element(x, y, TypeEnum.BLACK);
        //     }
        // }
        // c.setElements(elements);
        // c.reload();
        // c.out();
        // System.out.println(c.getEliminateMap());
    }

}


