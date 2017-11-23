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

    }
}