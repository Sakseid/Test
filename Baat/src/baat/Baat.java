import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baat;

/**
 *
 * @author vegar
 */
public class Baat 
{
    private String navn;
    private String merke;
    private int lengde;
    private int dybde;
    private String motor;
    private ArrayList<Person> personListe;

    public Baat(String navn, String merke, int lengde, int dybde, String motor) 
    {
        this.navn = navn;
        this.merke = merke;
        this.lengde = lengde;
        this.dybde = dybde;
        this.motor = motor;
        personListe = new ArrayList<Person>();
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }

    public int getLengde() {
        return lengde;
    }

    public void setLengde(int lengde) {
        this.lengde = lengde;
    }

    public int getDybde() {
        return dybde;
    }

    public void setDybde(int dybde) {
        this.dybde = dybde;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }
    
    /**
     * Opprette en person og legge til i lista
     */
    public void nyttMannskap(String navn, int rolle)
    {
    Person ny;
    ny = new Person(navn, rolle);
    personliste.add(ny);
    }
      
}

