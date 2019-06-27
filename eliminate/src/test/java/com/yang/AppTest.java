package com.yang;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        int n = 3;
        int leftnum = n, rightnum = n;
        ArrayList<String> results = new ArrayList<String>();

        parentheses("",results,leftnum,rightnum);


        System.out.println(results);
    }

    public static void parentheses(String sublist,ArrayList<String> results,int leftnum, int rightnum) {
        if(leftnum==0&&rightnum==0)
            results.add(sublist);
        if(rightnum>leftnum)
            parentheses(sublist+")", results, leftnum, rightnum-1);
        if(leftnum>0)
            parentheses(sublist+"(", results, leftnum-1, rightnum); 

    }

}
