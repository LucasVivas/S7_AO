package org.Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lulu on 19/11/17.
 */
public class GraphTest {
    @Test
    public void testGetInstance() throws Exception {
        Graph graph1 = Graph.getInstance();
        Graph graph2 = Graph.getInstance();
        assertNotNull("First object need to get the instance",graph1);
        assertNull("Second object need to be null",graph2);
    }
    @Test
    public void testBuildRandomGraph() throws Exception {
        Graph graphBuild = Graph.getInstance();
        Vertex source = new Vertex();
        graphBuild.buildRandomPath(source);
        assertTrue("This graph need to have more than one vertex", graphBuild.getNbVertices() > 1);
        assertTrue("This graph need to have at least one edge", graphBuild.getNbEdges() > 0);
    }

    @Test
    public void testDoesntExist() throws Exception{
        Graph graphExist = Graph.getInstance();

        Vertex v1 = new Vertex( 1,1,0);
        Vertex v2 = new Vertex( 1,2,0);
        Vertex v3 = null;

        Directions dir1 = Directions.NORTH;
        Directions dir2 = Directions.SOUTH;
        Directions dir3 = null;

        graphExist.addVertex(v1);
        graphExist.addVertex(v2);


        assertTrue("vertex (1,0) doesn't exist in this graph", graphExist.doesntExist(v1, dir1));
        assertFalse("vertex (1,2) exists in this graph", graphExist.doesntExist(v1, dir2));

        try{
            graphExist.doesntExist(v3,dir1);
            fail("NullPointerException must be thrown");
        }catch (NullPointerException npe){
        }

        try{
            graphExist.doesntExist(v1,dir3);
            fail("NullPointerException must be thrown");
        }catch (NullPointerException npe){
        }
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
    public void testClone() throws Exception{
        Vertex v1 = new Vertex( 2,5,0);
        Vertex v2 = new Vertex( 3,5,0);
        Vertex vClone = v1.clone();
        assertTrue("vClone is the clone of v1",v1.equals(vClone));
        assertFalse("vClone isn't the clone of v2",v2.equals(vClone));
    }
}