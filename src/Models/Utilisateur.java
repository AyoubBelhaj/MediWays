/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
public abstract class Utilisateur {
    private int ID;
    private  String nom;
    private String prenom;
    private String email;
    private String dateNaissance;
    private String numTel;

    public Utilisateur(int ID, String nom, String prenom, String email, String dateNaissance, String numTel) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
    }

    // Getters
    public int getID() {
        return ID;
    }

    public  String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNumTel() {
        return numTel;
    }

    // Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    


}
