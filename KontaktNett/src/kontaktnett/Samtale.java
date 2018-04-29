package kontaktnett;

/**
 *
 * @author vegar
 */
public class Samtale {
    private Person fra;
    private Person til;
    private int samtaler;

    public Samtale(Person fra, Person til, int samtaler) {
        this.fra = fra;
        this.til = til;
        this.samtaler = samtaler;
    }
    
    public void leggTilSamtale(){
        samtaler++;
    }

    public Person getTil() {
        return til;
    }

    public int getSamtaler() {
        return samtaler;
    }

    public void setSamtaler(int samtaler) {
        this.samtaler = samtaler;
    }
    
    public void skrivInfo(){
        System.out.println(fra.getNavn()+" "+til.getNavn()+" "+samtaler);
    }
    
    
}
