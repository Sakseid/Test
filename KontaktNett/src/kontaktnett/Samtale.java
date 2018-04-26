/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
    
    
}
