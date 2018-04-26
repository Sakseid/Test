/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontaktnett;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vegar
 */
public class KontaktNett {
    private List<Person> personer;

    public KontaktNett() {
        personer = new ArrayList<>();
        opprettNettverk();
    }
    
    private void opprettNettverk(){
        personer.add(new Person("Per"));
        personer.add(new Person("Espen"));
        personer.add(new Person("Narren"));
        personer.add(new Person("Pål"));
        personer.add(new Person("Prinsessa"));
        personer.add(new Person("Kongen"));
        
        System.out.println("Samtaler");
        personer.get(0).getNaboer().add(new Samtale(personer.get(0), personer.get(1), 9));
        personer.get(0).getNaboer().add(new Samtale(personer.get(0), personer.get(3), 11));
        personer.get(0).getNaboer().add(new Samtale(personer.get(0), personer.get(2), 7));
        personer.get(1).getNaboer().add(new Samtale(personer.get(1), personer.get(4), 115));
        personer.get(2).getNaboer().add(new Samtale(personer.get(2), personer.get(0), 6));
        personer.get(2).getNaboer().add(new Samtale(personer.get(2), personer.get(3), 17));
        personer.get(2).getNaboer().add(new Samtale(personer.get(2), personer.get(4), 19));
        personer.get(2).getNaboer().add(new Samtale(personer.get(2), personer.get(5), 21));
        personer.get(3).getNaboer().add(new Samtale(personer.get(3), personer.get(0), 12));
        personer.get(3).getNaboer().add(new Samtale(personer.get(3), personer.get(2), 15));
        personer.get(4).getNaboer().add(new Samtale(personer.get(4), personer.get(1), 143));
        personer.get(4).getNaboer().add(new Samtale(personer.get(4), personer.get(5), 14));
        personer.get(5).getNaboer().add(new Samtale(personer.get(5), personer.get(4), 3));
    }
    
    public Person getPerson(int p){
        Person person = personer.get(p);
        return person;
    }
    
    /**
     * Skal registrere en samtale mellom to personer, skal enten øke antall 
     * med 1 eller lage en ny kant.
     * @param person1
     * @param person2
     */
    public void registrerSamtale(int person1, int person2){
        Person p1 = personer.get(person1);
        Person p2 = personer.get(person2);
        Person p3 = p1.getNaboer().get(person2).getTil(); //Her ligger feilen!!!!!!!!
        
        for(Samtale s : p1.getNaboer()){
            if (p3==null){
                personer.get(person1).getNaboer().add(new Samtale(personer.get(person1), personer.get(person2), 1));
                System.out.println("Ny kant opprettet");
            }
            else if (p2 == p3){
                s.leggTilSamtale();
                System.out.println("Samtale oppdatert fra "+ (s.getSamtaler()-1) + " til " + s.getSamtaler());
            }
        }
    }
    
    /**
     * Metoden til Benjamin
     * @param from
     * @param to 
     */
    private void regConv(String from, String to) {
        int calls = 1;
        Node fromNode = nodeMap.get(from);
        if (fromNode != null) {
            Node toNode = nodeMap.get(to);
            if (toNode != null) {
                Edge fromToEdge = fromNode.getEdge(to);
                if (fromToEdge != null) {
                    calls += fromToEdge.getCalls();
                }
                fromNode.addEdge(toNode, calls);
                System.out.println("Updated edge " + from + " to " + to + " to " + calls + " calls.");

            } else {
                System.out.println(to + " does not exist in this map.");
            }
        } else {
            System.out.println(from + " does not exist in this map.");
        }
    }
    
    /**
     * Metode som skal finne direkte kontakter, skal returnere alle personer en 
     * mistenkt har hatt flere enn parameteren antall samtaler med
     * @param p
     * @param antall 
     */
    public void finnDirekteKontakter(Person p, int antall){
        for (Samtale s : p.getNaboer()){
            if (s.getSamtaler()>=antall)
                System.out.println(p.getNavn() + " har ringt " + s.getSamtaler() + " ganger til " + s.getTil().getNavn());
        }
    }
    
    /**
     * Skal returnere en liste med personer en mistenkt har 
     * hatt direkte eller indirekte kontakt med, med flere samtaler enn parameteren antall
     * @param p
     * @param antall
     */
    public void badFinnAlleKontakter(Person p, int antall){
        ArrayList<Person> sett = new ArrayList<>();
        LinkedList<Person> l = new LinkedList<>();
        l.addLast(p);
        sett.add(p);
        System.out.println(p.getNavn() + " sine kontakter:");
        while (!l.isEmpty()){
            Person person = l.removeFirst();
            for (Samtale s : person.getNaboer()){
                if (!sett.contains(s.getTil())){
                    if (s.getSamtaler()>=antall){
                        System.out.println(s.getTil().getNavn());
                    }
                    l.addLast(s.getTil());
                    sett.add(s.getTil());
                }
            }
        }
    }
    
    
     
    /**
     * 
     * @param p
     * @param antall 
     */
    public void finnAlleKontakter(Person p, int antall){
        ArrayList<Person> sett = new ArrayList<>();
        LinkedList<Person> l = new LinkedList<>();
    }
     

}
