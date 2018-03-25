/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regatta;

import java.util.HashMap;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author vegar
 */
public class Regatta {
    private HashMap<Integer, Baat> baater;
    private Queue<Baat> resultat;
    private List<Baat> sammenlagt;
    private int lop;
    
    public Regatta(){
        baater = new HashMap<>();
        sammenlagt = new LinkedList<>();
        fyllHashMap();
        lop = 1;
        
    }
    
    private void fyllHashMap(){
        Baat a = new Baat("Flytende Rente", 2);
        baater.put(2, a);
        Baat b = new Baat("Kon Tiki", 12);
        baater.put(12, b);
        Baat c = new Baat("Santa Maria", 14);
        baater.put(14, c);
        Baat d = new Baat("Titanic", 17);
        baater.put(17, d);
        Baat e = new Baat("Fram", 123);
        baater.put(123, e);
        Baat f = new Baat("Tilbake", 321);
        baater.put(321, f);
        Baat g = new Baat("Costa Plenty", 22);
        baater.put(22, g);
        }
    
    public void skrivBaater(){
        Baat baat;
        System.out.format("%18s%15s\n", "Navn", "Seilnr");
        for(Map.Entry<Integer, Baat> b : baater.entrySet()) {
            baat = b.getValue();
            baat.skrivInfo();
    }
    }
    
    public void nySeilas(){
        Seilas seilas = new Seilas(baater, lop);
        resultat = seilas.registrerMaalgang();
        seilas.setPoeng(1);
        System.out.println("--Resultat seilas " + lop + "--");
        seilas.skrivResultat();
        System.out.println();
        oppdaterSammenlagt();
        lop++;
    }
    
    public void oppdaterSammenlagt(){
        if (lop ==1){
            for (int t = 0; t < baater.size(); t++){
            Baat b = resultat.remove();
            b.setPoengsum(b.getPlasseringer(lop));
            sammenlagt.add(b.getPlasseringer(lop-1), b);
            Collections.sort(sammenlagt, new Sorter());
            }
        }
        else if (lop!=1) {
                oppdaterPoengsum();
                Collections.sort(sammenlagt, new Sorter());
            }
    }
    
    public void skrivSammenlagt(){
        System.out.println("                                           --Sammenlagtresultater--");
        System.out.format("%18s%15s%12s%12s%12s%12s%12s%18s\n", "Navn", "Seilnummer", "Seilas 1", "Seilas 2", "Seilas 3", "Seilas 4", "Seilas 5", "Poeng Totalt");
        for (Baat b : sammenlagt){
            System.out.format("%18s%15d%12d%12d%12d%12d%12d%18d\n", b.getNavn(), b.getSeilnr(), b.getPlasseringer(1), b.getPlasseringer(2), b.getPlasseringer(3), b.getPlasseringer(4), b.getPlasseringer(5), b.getPoengsum());
        }
        System.out.println();
        
    }
    
    private void oppdaterPoengsum(){
        boolean tom = resultat.isEmpty();
        while (!tom){
        Baat b = resultat.remove();
        for (Baat a : sammenlagt){
            if (a.equals(b)){
        a.setPoengsum(a.getPoengsum()+b.getPlasseringer(lop));
        tom = resultat.isEmpty();
        }
        }
        }
    }
    }


 class Sorter implements Comparator<Baat>{

    @Override
    public int compare(Baat a, Baat b) {
        if(a.getPoengsum()< b.getPoengsum()){
            return -1;
        } else {
            return 1;
        }
    }

}