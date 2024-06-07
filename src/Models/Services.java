
package Models;

public abstract class Services {
    private int idService;
    private String nomService;
    private String description;
    private double cout;

    public Services(int idService, String nomService, String description, double cout) {
        this.idService = idService;
        this.nomService = nomService;
        this.description = description;
        this.cout = cout;
        
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    
    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    

    public abstract double calculTotalCout();

    public abstract String getServiceDetails();
}
