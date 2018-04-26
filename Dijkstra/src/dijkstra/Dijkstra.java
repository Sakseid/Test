/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author vegar
 */
public class Dijkstra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Adding nodes");
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("0"));
        nodes.add(new Node("1"));
        nodes.add(new Node("2"));
        nodes.add(new Node("3"));
        nodes.add(new Node("4"));
        
        System.out.println("Adding edges");
        nodes.get(0).neighbours.add(new Edge(nodes.get(0), nodes.get(1), 10));
        nodes.get(0).neighbours.add(new Edge(nodes.get(0), nodes.get(3), 30));
        nodes.get(0).neighbours.add(new Edge(nodes.get(0), nodes.get(4), 100));
        nodes.get(1).neighbours.add(new Edge(nodes.get(1), nodes.get(2), 50));
        nodes.get(2).neighbours.add(new Edge(nodes.get(2), nodes.get(3), 20));
        nodes.get(2).neighbours.add(new Edge(nodes.get(2), nodes.get(4), 10));
        nodes.get(3).neighbours.add(new Edge(nodes.get(3), nodes.get(4), 60));
        
        nodes.get(1).neighbours.add(new Edge(nodes.get(1), nodes.get(0), 10));
        nodes.get(3).neighbours.add(new Edge(nodes.get(3), nodes.get(0), 30));
        nodes.get(4).neighbours.add(new Edge(nodes.get(4), nodes.get(0), 100));
        nodes.get(2).neighbours.add(new Edge(nodes.get(2), nodes.get(1), 50));
        nodes.get(3).neighbours.add(new Edge(nodes.get(3), nodes.get(2), 20));
        nodes.get(4).neighbours.add(new Edge(nodes.get(4), nodes.get(2), 10));
        nodes.get(4).neighbours.add(new Edge(nodes.get(4), nodes.get(3), 60));
        
        findShortestPath(nodes.get(0), nodes);
    }
    
    public static void findShortestPath(Node n, List<Node> nodes){
        System.out.println("Finding shortest path");
        Comparator <Node> comp = new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2){
                return o1.dist - o2.dist;
            }
        };
        PriorityQueue<Node> pq = new PriorityQueue(comp);
        
        for (int i = 1; i>nodes.size(); i++){
            pq.add(nodes.get(i));
        }
        
        while(!pq.isEmpty()){
            Node u = pq.poll();
            for (Edge e : u.neighbours){
                Node v = e.to;
                if (u.dist + e.lenght < v.dist){
                    v.dist = u.dist+e.lenght;
                    v.prev = u;
                }
            }
            
        }
        
        for (Node tn : nodes){
            System.out.println(tn.navn + " " + tn.dist);
        }
    }
}
