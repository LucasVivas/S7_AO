package org.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.Model.Vertex;

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
            fail("NullPointerException need to be throw");
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
            fail("NullPointerException need to be throw");
        }catch (NullPointerException npe){
            //
        }
    }

    @Test
    public void setX() throws Exception {
    }

    @Test
    public void setY() throws Exception {
    }

    @Test
    public void getNbr() throws Exception {
    }

    @Test
    public void getPos() throws Exception {
    }

    @Test
    public void areNeighbor() throws Exception {
    }

    @Test
    public void compareTo() throws Exception {
    }

    @Test
    public void inBorders() throws Exception {
    }

}

