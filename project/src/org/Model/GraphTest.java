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
    public void testAddVertex() throws Exception {

    }

    @Test
    public void testDoesntExist() throws Exception{
        Graph graphExist = Graph.getInstance();

        Vertex v1 = new Vertex( 1,1,0);
        Vertex v2 = new Vertex( 1,2,1);
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
            fail("NullPointerException must be throw");
        }catch (NullPointerException npe){
        }

        try{
            graphExist.doesntExist(v1,dir3);
            fail("NullPointerException must be throw");
        }catch (NullPointerException npe){
        }
    }
}