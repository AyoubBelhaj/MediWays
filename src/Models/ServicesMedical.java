
package Models;

public class ServicesMedical extends Services implements ServiceInterface {
    private String Typemed;
    private DM Record;
    private String nomclinique;

    public ServicesMedical(int idService, String nomService, String description, double cout, String Typemed, DM dossierMedical, String clinique) {
        super(idService, nomService, description, cout);
        this.Typemed = Typemed;
        this.Record = dossierMedical;
        this.nomclinique = clinique;
    }

    
    public String getTypemed() {
        return Typemed;
    }

    public void setTypemed(String Typemed) {
        this.Typemed = Typemed;
    }
    
    public DM getDossierMedical() {
        return Record;
    }

    public void setDossierMedical(DM dossierMedical) {
        this.Record = dossierMedical;
    }
    
    public String getClinique() {
        return nomclinique;
    }

    public void setClinique(String clinique) {
        this.nomclinique = clinique;
    }

    @Override
    public double calculTotalCout() {
        return getCout();
    }

   
   
    @Override
    public String getServiceDetails() {
     return "Type medical: " + Typemed + "\nClinique: " + nomclinique + "\n";
    }
}
