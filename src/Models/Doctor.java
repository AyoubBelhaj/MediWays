/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Utilisateur {
    private List<RDV> rendezvous;
    private String specialite;

    public Doctor(int ID, String nom, String prenom, String email, String dateNaissance, String numTel, String specialite) {
        super(ID, nom, prenom, email, dateNaissance, numTel);
        this.rendezvous = new ArrayList<>();
        this.specialite = specialite;
    }

    public List<RDV> getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(List<RDV> rendezvous) {
        this.rendezvous = rendezvous;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    // Method to manage the list of rendezvous
    public void manageRendezvous(List<RDV> newRendezvous) {
        rendezvous.addAll(newRendezvous);
    }

    // Inherited abstract methods



}
