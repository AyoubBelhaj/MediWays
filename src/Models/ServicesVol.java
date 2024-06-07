/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

public class ServicesVol extends Services implements ServiceInterface {
    
    private String classeVol;
    private int idvol; 

    public ServicesVol(int idService, String nomService, String description, double cout, int vol , String classeVol) {
        super(idService, nomService, description, cout);
        this.idvol = vol;
        this.classeVol = classeVol;
    }
    
    public String getclasseVol() {
        return classeVol;
    }

    public String getClasseVol() {
        return classeVol;
    }

    public void setClasseVol(String classeVol) {
        this.classeVol = classeVol;
    }
    
    
    
    public void setVol(String Cvol) {
        this.classeVol = Cvol;
    }

    public int getVol() {
        return idvol;
    }

    public void setVol(int vol) {
        this.idvol = vol;
    }

    @Override
    public double calculTotalCout() {
        return getCout();
    }

    @Override
    public String getServiceDetails() {
        return "Vol Details:\n vol id" + idvol + " la classe :"+ classeVol + "\n";
    }
}
