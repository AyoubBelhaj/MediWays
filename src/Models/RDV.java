/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;



public class RDV {
    private int IDRDV;
    private int  id_patient;
    private String description;
    private int  id_doctor;
    private String dateRDV;

    public RDV(int IDRDV, int id_patient,String description, int id_doctor, String dateRDV) {
        this.IDRDV = IDRDV;
        this.id_patient = id_patient;
        this.description = description;
        this.id_doctor = id_doctor;
        this.dateRDV = dateRDV;
    }

    public int getIDRDV() {
        return IDRDV;
    }

    public void setIDRDV(int IDRDV) {
        this.IDRDV = IDRDV;
    }

    public int getPatient() {
        return id_patient;
    }

    public void setPatient(int patient) {
        this.id_patient = patient;
    }

    public int getDoctor() {
        return id_doctor;
    }

    public void setDoctor(int doctor) {
        this.id_doctor = doctor;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
    }
}
