package org.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class VertexTest {
    @Test
    public void getX() throws Exception {
        Vertex vertex1 = new Vertex(10,21, 6);
        Vertex vertex2 = new Vertex(0,0,0);
        Vertex vertex3 = null;

        assertEquals(10,vertex1.getX());
        assertEquals(0,vertex2.getX());

        try {
            vertex3.getX();
            fail("NullPointerException need to be thrown");
        }catch (NullPointerException npe){
            //
        }
    }

    @Test
    public void getY() throws Exception {
        Vertex vertex1 = new Vertex(10,21, 6);
        Vertex vertex2 = new Vertex(0,0,0);
        Vertex vertex3 = null;

        assertEquals(21,vertex1.getY());
        assertEquals(0,vertex2.getY());
        try {
            vertex3.getY();
            fail("NullPointerException need to be thrown");
        }catch (NullPointerException npe){
            //
        }
    }

    @Test
    public void getNbr() throws Exception {
        Vertex vertex1 = new Vertex(10,21, 6);
        Vertex vertex2 = new Vertex(0,0,0);
        Vertex vertex3 = null;

        assertEquals(6,vertex1.getNbr());
        assertEquals(0,vertex2.getNbr());
        try {
            vertex3.getNbr();
            fail("NullPointerException need to be thrown");
        }catch (NullPointerException npe){
            //
        }
    }

    @Test
    public void testAreNeighbors() throws Exception{
        Vertex v1 = new Vertex( 3,3,0);
        Vertex v2 = new Vertex( 3,4,0);
        Vertex v3 = new Vertex( 3,5,0);
        assertTrue("v1 and v2 are neighbors", v1.areNeighbors(v2));
        assertFalse("v1 and v3 aren't neighbors", v1.areNeighbors(v3));
        assertTrue("v2 and v3 are neighbors", v2.areNeighbors(v3));
    }

    @Test
    public void testCompareTo() throws Exception{
        Vertex v1 = new Vertex( 1,4,0);
        Vertex v2 = new Vertex( 1,4,0);
        Vertex v3 = new Vertex( 3,5,0);
        assertTrue("v1 and v2 are comparable", v1.compareTo(v2)==0);
        assertFalse("v1 and v3 aren't comparable", v1.compareTo(v3)==0);
        assertFalse("v2 and v3 aren't comparable", v2.compareTo(v3)==0);
    }

    @Test
    public void testInBorders() throws Exception{
        Vertex v1 = new Vertex( 0,4,0);
        Directions dir1 = Directions.EAST;
        Directions dir2 = Directions.WEST;
        assertTrue("v1 can move to EAST", v1.inBorders(dir1));
        assertFalse("v1 can't move to WEST", v1.inBorders(dir2));
    }

    @Test
    public void testEquals() throws Exception {
        Vertex v1 = new Vertex(1,1,0);
        Vertex v2 = new Vertex(1,1,1);
        Vertex v3 = new Vertex(0,1,1);
        Object o = new Object();

        assertTrue("v1 equals v2", v1.equals(v2));
        assertFalse("v2 not equals to v3, nbr doesn't affect the test", v1.equals(v3));
        assertFalse("o is not a vertex", v1.equals(o));
    }

    @Test
    public void testClone() throws Exception{
        Vertex v1 = new Vertex( 2,5,0);
        Vertex v2 = new Vertex( 3,5,0);
        Vertex vClone = v1.clone();
        assertTrue("vClone is the clone of v1",v1.equals(vClone));
        assertFalse("vClone isn't the clone of v2",v2.equals(vClone));
    }

}
