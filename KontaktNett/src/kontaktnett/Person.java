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

    public Person(String navn) {
        naboer = new ArrayList<>();
        this.navn = navn;
    }

    public List<Samtale> getNaboer() {
        return naboer;
    }

    public String getNavn() {
        return navn;
    }
}
