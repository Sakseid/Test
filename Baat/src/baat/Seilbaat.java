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
public class Seilbaat extends Baat
{
private int seilnr;
private String kjoltype;

    public Seilbaat(String navn, String merke, int lengde, int dybde, String motor, int seilnr, String kjoltype) 
    {
        super(navn, merke, lengde, dybde, motor);
        this.seilnr = seilnr;
        this.kjoltype = kjoltype;
    }

}