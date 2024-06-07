
package Models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private int id;
    private int Idpatient;
    private String nomPatient;
    private String date;
    private double cout;
    private List<Services> servicesList;
    
    private boolean estConfirmee;

    public Reservation(int id, int Idpatient, String nomPatient, String date, double cout, boolean estConfirmee) {
        this.id = id;
        this.Idpatient = Idpatient;
        this.nomPatient = nomPatient;
        this.date = date;
        this.cout = cout;
        this.servicesList = new ArrayList();
        this.estConfirmee = estConfirmee;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdpatient() {
        return Idpatient;
    }

    public boolean isEstConfirmee() {
        return estConfirmee;
    }

    public void setIdpatient(int Idpatient) {
        this.Idpatient = Idpatient;
    }

    public void setEstConfirmee(boolean estConfirmee) {
        this.estConfirmee = estConfirmee;
    }
    
    

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

   

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

   

     public List<Services> getServicesList() {
        return servicesList;
    }
     
     

    // Setter pour servicesList
    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }
    
    public double getTotalCostOfServices() {
        return servicesList.stream()
            .mapToDouble(Services::calculTotalCout)
            .sum();
    }

    public void displayServiceDetails() {
        servicesList.forEach(service -> System.out.println(service.getServiceDetails()));
    }
    
     // CRUD pour servicesList

    // Create
    public void addService(Services service) {
        servicesList.add(service);
    }

    // Read
    public Services getService(int index) {
        if (index >= 0 && index < servicesList.size()) {
            return servicesList.get(index);
        } else {
            return null; 
        }
    }

    // Update
    public void updateService(int index, Services updatedService) {
        if (index >= 0 && index < servicesList.size()) {
            servicesList.set(index, updatedService);
        } else {
            // Gérer l'erreur d'index invalide ici
            System.out.println("Index out of bounds.");
        }
    }

    // Delete
    public void deleteService(int index) {
        if (index >= 0 && index < servicesList.size()) {
            servicesList.remove(index);
        } else {
            
            System.out.println("Index invalid.");
        }
    }
    
    public boolean isConfirmed() {
        return estConfirmee;
    }

    public void confirm() {
        if (!estConfirmee) {
            estConfirmee = true;
            System.out.println("Reservation confirmée pour le patient d'id" + Idpatient + " on " + date);
        } else {
            System.out.println("Reservation is deja confirmée.");
        }
    }

    public void annulerReservation() {
        if (!estConfirmee) {
            System.out.println("Reservation pour le patient d'id " + Idpatient + " on " + date + " has been canceled.");
        } else {
            System.out.println("Cannot cancel a confirmed reservation.");
        }
    }
    
    
}
