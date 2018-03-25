/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regatta;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author vegar
 */
public class Seilas {
    private Queue<Baat> seilas;
    private HashMap<Integer, Baat> baater;
    private int lop;
    private int poeng;
    private static Scanner scanner;
    
    public Seilas(HashMap<Integer, Baat> baater, int lop){
        seilas = new LinkedList<>();
        this.lop = lop;
        poeng = 1;
        scanner  = new Scanner(System.in);
        this.baater = baater;
    }
    
    public boolean lagResultatliste(int seilnr){
                Baat b = baater.get(seilnr);
            if (!seilas.contains(b)){
                seilas.add(b);
                b.setPlassering(lop, poeng);;
                poeng++;
                return true;
                }
            else {
                return false;
                    }
    }
    
    public void skrivResultat(){
        System.out.format("%18s%15s%12s\n", "Navn", "Seilnr", "Plassering");
        for (Baat b : seilas){
            b.skrivInfoPoeng(lop);
        }
    }
    
    public int seilasStr(){
        return seilas.size();
    }   
    
    public int baatListeStr(){
        return baater.size();
    }

    public void setPoeng(int poeng) {
        this.poeng = poeng;
    }
    
    public Queue registrerMaalgang(){
    System.out.println("-------------------");
    System.out.println("--Registrer båter--");
    while(seilasStr() != baatListeStr()){
        int input = Integer.parseInt(scanner.nextLine());
        if (baater.containsKey(input)){
        boolean r = lagResultatliste(input);
        if (!r){
            System.out.println("--Målgang for denne båten er allerede registrert, prøv på nytt--");
        }
        }
        else{
            System.out.println("--Ugyldig seilnr, prøv på nytt--");
        }
    }
    return seilas;
    }
    
    
    
}

