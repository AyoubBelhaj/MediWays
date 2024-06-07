/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Utilisateur {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Facture> factures;

    public Admin(int ID, String nom, String prenom, String email, String dateNaissance, String numTel) {
        super(ID, nom, prenom, email, dateNaissance, numTel);
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.factures = new ArrayList<>();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }


    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public void addFacture(Facture facture) {
        factures.add(facture);
    }

    public void removeFacture(Facture facture) {
        factures.remove(facture);
    }




}
