/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Models;


public class Patient extends Utilisateur {
    private String nationality;

   


    public Patient(int ID, String nom, String prenom, String email, String dateNaissance, String numTel, String nationality) {
        super(ID, nom, prenom, email, dateNaissance, numTel);
        this.nationality = nationality;

      

    }

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
 

}
