/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontaktnett;

import java.util.Scanner;

/**
 *
 * @author vegar
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean ferdig = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Personer:");
        System.out.println("0: Per");
        System.out.println("1: Espen");
        System.out.println("2: Narren");
        System.out.println("3: Pål");
        System.out.println("4: Prinsessa");
        System.out.println("5: Kongen");
        KontaktNett kn = new KontaktNett();
        while (!ferdig){
            System.out.println("");
            System.out.println("-Meny-");
            System.out.println("1: Registrer samtale");
            System.out.println("2: Finn direkte kontakter");
            System.out.println("3: Finn alle kontakter");
            System.out.println("0: Avslutt");
            System.out.println("---------------------------");
            int input = Integer.parseInt(sc.nextLine());
            if (input == 0){
                ferdig = true;
            }
            else if (input == 1){
                System.out.println("Samtale fra");
                int fra = Integer.parseInt(sc.nextLine());
                System.out.println("Samtale til");
                int til = Integer.parseInt(sc.nextLine());
                kn.registrerSamtale(fra, til);
            }
            else if (input == 2){
                System.out.println("Finn direkte kontakter fra");
                int fra = Integer.parseInt(sc.nextLine());
                Person person = kn.getPerson(fra);//hent personobjekt fra kn
                System.out.println("Grense for samtaler (10)");
                int antall = Integer.parseInt(sc.nextLine());
                kn.finnDirekteKontakter(person, antall);
            }
            else if (input == 3){
                System.out.println("Finn alle kontakter fra");
                int fra = Integer.parseInt(sc.nextLine());
                Person person = kn.getPerson(fra);//hent personobjekt fra kn
                System.out.println("Grense for samtaler (10)");
                int antall = Integer.parseInt(sc.nextLine());
                kn.finnAlleKontakter(person, antall);
            }
            else if (input > 3 || input < 0){
                System.out.println("Ugyldig kommando");
            }
        }
        
    }
       
    }
    

