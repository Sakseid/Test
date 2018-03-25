/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regatta;

/**
 *
 * @author vegar
 */
public class Baat {
    private String navn;
    private int seilnr;
    private int[] plasseringer;
    private int poengsum;
    
    public Baat(String navn, int seilnr){
    this.navn = navn;
    this.seilnr = seilnr;
    plasseringer = new int[10];
    }
    
    public void skrivInfo(){
        System.out.format("%18s%15d\n", navn, seilnr);
    }
    
    public void skrivInfoPoeng(int lop){
        System.out.format("%18s%15d%12d\n", navn, seilnr, plasseringer[lop]);
    }

    public void setPlassering(int lop, int poeng){
        plasseringer[lop] = poeng;
    }
    
    public int getPlasseringer(int lop){
        return plasseringer[lop];
    }

    public int getPoengsum() {
        return poengsum;
    }

    public void setPoengsum(int poengsum) {
        this.poengsum = poengsum;
    }

    public String getNavn() {
        return navn;
    }

    public int getSeilnr() {
        return seilnr;
    }
    
    
}
