/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author vegar
 */
public class Edge {
    Node from;
    Node to;
    int lenght;

    public Edge(Node from, Node to, int lenght) {
        this.from = from;
        this.to = to;
        this.lenght = lenght;
        
        
    }
    
    
}
