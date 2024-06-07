
package Models;

public class ServiceAccommodation extends Services implements ServiceInterface {
    private String TypeAccommodation;
    private String nomhotel; 

    public ServiceAccommodation(int idService, String nomService, String description, double cout,String accommodationType, String hotel) {
        super(idService, nomService, description, cout);
        this.TypeAccommodation = accommodationType;
        this.nomhotel = hotel;
    }

    public String getTypeAccommodation() {
        return TypeAccommodation;
    }

    public void setTypeAccommodation(String TypeAccommodation) {
        this.TypeAccommodation = TypeAccommodation;
    }

    

    public String getHotel() {
        return nomhotel;
    }

    public void setHotel(String hotel) {
        this.nomhotel = hotel;
    }

    @Override
    public double calculTotalCout() {
        return getCout();
    }

    @Override
    public String getServiceDetails() {
        return "Type d'Accomodation: " + TypeAccommodation + "\nHotel: " + nomhotel + "\n";
    }
}
