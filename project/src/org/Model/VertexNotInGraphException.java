package org.Model;

public class VertexNotInGraphException extends Exception{
    public VertexNotInGraphException(Vertex vertex){
        System.out.println("vertex : "+vertex+" is not contain in the graph");
    }
}
