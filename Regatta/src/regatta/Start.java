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
public class Start {
    static Regatta regatta;
    
public static void main(String[] args) {
    regatta = new Regatta();
    for (int i = 0; i < 5; i++){
    System.out.println("--Registerte båter--");
    regatta.skrivBaater();
    regatta.nySeilas();
    regatta.skrivSammenlagt();
    }
}
}