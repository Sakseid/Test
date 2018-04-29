package kontaktnett;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vegar
 */
public class Person {
    private List<Samtale> naboer;
    private String navn;
    private Person forrige;
    private boolean sett;

    public Person(String navn) {
        naboer = new ArrayList<>();
        this.navn = navn;
        sett = false;
    }

    public List<Samtale> getNaboer() {
        return naboer;
    }

    public String getNavn() {
        return navn;
    }

    public Person getForrige() {
        return forrige;
    }

    public void setForrige(Person forrige) {
        this.forrige = forrige;
    }

    public boolean isSett() {
        return sett;
    }
    
    
}
