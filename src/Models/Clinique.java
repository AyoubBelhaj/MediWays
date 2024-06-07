
package Models;


import java.util.ArrayList;
import java.util.List;


public class Clinique {
    private int idClinique;
    private String nomClinique;
    private String adresse;
    private String specialite;
    //private List<Record> ServicesMedicals;

    public Clinique(int idClinique, String nomClinique, String adresse, String specialite) {
        this.idClinique = idClinique;
        this.nomClinique = nomClinique;
        this.adresse = adresse;
        this.specialite = specialite;
        //this.ServicesMedicals = new ArrayList<>();
    }


    public int getIdClinique() {
        return idClinique;
    }

    public void setIdClinique(int idClinique) {
        this.idClinique = idClinique;
    }

    public String getNomClinique() {
        return nomClinique;
    }

    public void setNomClinique(String nomClinique) {
        this.nomClinique = nomClinique;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    //public void ajouterMedicalProcedure(Record procedure) {
    //    ServicesMedicals.add(procedure);
    //}
    


    //public void supprimerMedicalProcedure(Record procedure) {
    //    ServicesMedicals.remove(procedure);
    //

    //public List<Record> getMedicalProcedures() {
     //   return ServicesMedicals;
    //}

    @Override
    public String toString() {
        return "Clinique Details:\nID: " + idClinique + "\nName: " + nomClinique
             + "\nAddress: " + adresse + "\nSpecialty: " + specialite;
    }
}

