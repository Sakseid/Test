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
     * 
     * @param person1
     * @param person2
     */
    public void registrerSamtale(int person1, int person2){
        Person p1 = personer.get(person1);
        Person p2 = personer.get(person2);
        List<Samtale> n = p1.getNaboer();
        boolean oppdatert = false;
        for (Samtale s : n){
                if (s.getTil().equals(p2)){
                    s.leggTilSamtale();
                    System.out.println("Samtale oppdatert fra "+ (s.getSamtaler()-1) + " til " + s.getSamtaler());
                    oppdatert = true;
                }
        }
        if (!oppdatert){
            Samtale samtale = new Samtale(p1, p2, 1);
            p1.getNaboer().add(samtale);
            System.out.println("Ny kant opprettet");
            }
    }
        
    /**
     * Metode som skal finne direkte kontakter, skal returnere alle personer en 
     * mistenkt har hatt flere enn parameteren antall samtaler med
     * 
     * Mulig denne metoden er noe omstendelig, men den virker
     * 
     * @param p
     * @param antall 
     */
    public void finnDirekteKontakter(Person p, int antall){
        boolean funnet = false;
        for (Samtale s : p.getNaboer()){
            List<Samtale> l = s.getTil().getNaboer();
            if (s.getSamtaler()>=antall){
                for (Samtale f : l){
                    if (f.getTil().equals(p) && f.getSamtaler()>=antall){
                        System.out.println(p.getNavn() + " har ringt " + s.getSamtaler() + " ganger til " + s.getTil().getNavn());
                        funnet = true;
                    }
                }
            }
        }
        if (!funnet) System.out.println(p.getNavn() + " har ingen direkte kontakter med " + antall + " som grense");
    }
    
    /**
     * Skal returnere en liste med personer en mistenkt har 
     * hatt direkte eller indirekte kontakt med, med flere samtaler enn parameteren antall
     * 
     * Går gjennom grafen så lenge samtaler til og fra personer er flere enn parameteren antall
     * 
     * @param p
     * @param antall
     */
    public void finnAlleKontakter(Person p, int antall){
        ArrayList<Person> sett = new ArrayList<>();
        LinkedList<Person> l = new LinkedList<>();
        boolean funnet = false;
        l.addLast(p);
        sett.add(p);
        System.out.println(p.getNavn() + " sine kontakter:");
        while (!l.isEmpty()){
            Person person = l.removeFirst();
            for (Samtale s : person.getNaboer()){
                List<Samtale> k = s.getTil().getNaboer();
                if (!sett.contains(s.getTil()) && s.getSamtaler()>=antall){
                    for (Samtale f : k){
                        if (f.getTil().equals(person) && f.getSamtaler()>=antall){
                            System.out.println(s.getTil().getNavn());
                            funnet = true;
                            l.addLast(s.getTil());
                            sett.add(s.getTil());
                        }
                    }
                }
            }
        }
        if (!funnet) System.out.println(p.getNavn() + " har ingen kontakter i nettverket med " + antall + " som grense");
    }
}
