/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vegar
 */
public class Node {
    List<Edge> neighbours;
    String navn;
    Node prev;
    int dist; //avstand fra startnoden

    public Node(String navn) {
        neighbours = new ArrayList<>();
        this.navn = navn;
    }
    
    
}
