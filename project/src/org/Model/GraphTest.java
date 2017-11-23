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
        assertNotNull(graph1);
        assertNull(graph2);
    }
    @Test
    public void testBuildRandomGraph() throws Exception {

    }
}