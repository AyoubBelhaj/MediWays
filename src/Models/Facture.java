/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

public class Facture {
    private int IDFacture;
    private double montant;
    private int IDpatient;


    public Facture(int IDFacture, double montant, int IDpatient) {
        this.IDFacture = IDFacture;
        this.montant = montant;
        this.IDpatient = IDpatient;

    }

    public int getIDFacture() {
        return IDFacture;
    }

    public void setIDFacture(int IDFacture) {
        this.IDFacture = IDFacture;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIDPatient() {
        return IDpatient;
    }

    public void setIDPatient(int IDpatient) {
        this.IDpatient = IDpatient;
    }






}