package Models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Sejour {
    private int idSejour;
    private Date dateDebut;
    private Date dateFin;
    private String typeHebergement;
    private double coutTotal;
    private Set<Patient> patients;
    private Hotel hotel;
    private Clinique clinique;

    public Sejour(int idSejour, Date dateDebut, Date dateFin, String typeHebergement, double coutTotal, Set<Patient> patients, Hotel hotel, Clinique clinique) {
        this.idSejour = idSejour;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeHebergement = typeHebergement;
        this.coutTotal = coutTotal;
        this.patients = patients;
        this.hotel = hotel;
        this.clinique = clinique;
    }

    public int getIdSejour() {
        return idSejour;
    }

    public void setIdSejour(int idSejour) {
        this.idSejour = idSejour;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getTypeHebergement() {
        return typeHebergement;
    }

    public void setTypeHebergement(String typeHebergement) {
        this.typeHebergement = typeHebergement;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Clinique getClinique() {
        return clinique;
    }

    public void setClinique(Clinique clinique) {
        this.clinique = clinique;
    }

    public int calculateStayDurationInDays() {
        long durationInMilliseconds = dateFin.getTime() - dateDebut.getTime();
        return (int) (durationInMilliseconds / (1000 * 60 * 60 * 24)); // Milliseconds to days
    }

    public double calculTotalCoutAvecPromotion(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("percentage de promotion doit etre entre 0 et 100.");
        }
        double discount = discountPercentage / 100.0;
        return coutTotal * (1 - discount);
    }
}
