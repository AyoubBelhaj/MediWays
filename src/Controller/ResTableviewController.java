/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Models.DM;
import Models.Reservation;
import Models.ServiceAccommodation;
import Models.Services;
import Models.ServicesMedical;
import Models.ServicesVol;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ResTableviewController implements Initializable {

    ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, Integer> colidp;
    @FXML
    private TableColumn<Reservation, String> colnomper;
    @FXML
    private TableColumn<Reservation, String> coldate;
    @FXML
    private TableColumn<Reservation, Double> colcout;
    @FXML
    private TableColumn<Reservation, Boolean> colconf;
    @FXML
    private TableColumn<Reservation, Integer> colidm;
    @FXML
    private TableColumn<Reservation, Integer> colida;
    @FXML
    private TableColumn<Reservation, Integer> colidv;
    @FXML
    private BorderPane bp;
    @FXML
    private TextField id;
    @FXML
    private TextField idp;
    @FXML
    private TextField nomp;
    @FXML
    private TextField dat;
    @FXML
    private TextField cc;
    @FXML
    private TextField estc;
    @FXML
    private TextField idm;
    @FXML
    private TextField ida;
    @FXML
    private TextField idv;
    @FXML
    private TableView<Reservation> tvmeds;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadFromFile();

        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colidp.setCellValueFactory(new PropertyValueFactory<>("Idpatient"));
        colnomper.setCellValueFactory(new PropertyValueFactory<>("nomPatient"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colcout.setCellValueFactory(new PropertyValueFactory<>("cout"));
        colconf.setCellValueFactory(new PropertyValueFactory<>("estConfirmee"));
        colidm.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getServicesList().get(0).getIdService()).asObject());
        colidv.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getServicesList().get(1).getIdService()).asObject());
        colida.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getServicesList().get(2).getIdService()).asObject());

        // Assuming you have an observable list of reservations called 'reservationList'
        tvmeds.setItems(reservationList);
        tvmeds.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Reservation selectedReservation = tvmeds.getSelectionModel().getSelectedItem();
                if (selectedReservation != null) {
                    displayReservationDetails(selectedReservation);
                }
            }
        });

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        bp.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Resserv.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("ResServ Menu");
        resserv.setScene(scene);
        resserv.show();
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        
        bp.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("MediWays");
        resserv.setScene(scene);
        resserv.show();
        
    }

    @FXML
    private void ajouterres(ActionEvent event) throws IOException {
        // Retrieve input values from text fields
        int idd = Integer.parseInt(id.getText());
        int idpp = Integer.parseInt(idp.getText());
        String datt = dat.getText();
        String nompers = nomp.getText();
        Double cot = Double.parseDouble(cc.getText());
        Boolean etc = Boolean.parseBoolean(estc.getText());
        int idmm = Integer.parseInt(idm.getText());
        int idaa = Integer.parseInt(ida.getText());
        int idVol = Integer.parseInt(idv.getText());

        // Find the ServicesVol by ID
        ServicesMedical foundServiceMed = findServicesMedicalById(idmm);
        ServiceAccommodation foundServiceacc = findServiceAccommodationById(idaa);
        ServicesVol foundServiceVol = findServiceVolById(idVol);
        DM dd = new DM(1, "rr");
        ServicesMedical newService = new ServicesMedical(idmm, "gg", "gggggg", 1000, "rrrr", dd, "ttttt");
        ServiceAccommodation acc = new ServiceAccommodation(idaa, "hh", "hehrhhehe", 5000.55, "tt", "4toilees");

        if (foundServiceMed != null && foundServiceacc != null && foundServiceVol != null) {
            // Create a new Reservation object and add the found ServiceVol

            Reservation res = new Reservation(idd, idpp, nompers, datt, cot, false);
            res.getServicesList().add(foundServiceMed);
            res.getServicesList().add(foundServiceacc);
            res.getServicesList().add(foundServiceVol);

            // Add the Reservation to the observable list
            reservationList.add(res);

            // Set the items directly to TableView without using setAll
            tvmeds.setItems(reservationList);
            saveToFile();
        } else {
            // Handle case when the ServicesVol is not found
            System.out.println("ServiceVol not found for ID: " + idVol);
            // You can display an alert or handle the situation accordingly
        }
    }

    @FXML
    private void deleteres(ActionEvent event) {

        Reservation selectedReservation = tvmeds.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {

            reservationList.remove(selectedReservation);

            tvmeds.setItems(reservationList);
            saveToFile();
        } else {

            System.out.println("Selcetionner unereservation ");
        }

    }

    @FXML
    private void modifierres(ActionEvent event) {
        Reservation selectedReservation = tvmeds.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            updateReservationDetails(selectedReservation);
            saveToFile();
        } else {
            System.out.println("Please select a reservation to modify.");
        }
    }

    private void updateReservationDetails(Reservation reservation) {
        // Mettre à jour les attributs de la réservation avec les nouvelles valeurs des champs texte
        reservation.setId(Integer.parseInt(id.getText()));
        reservation.setIdpatient(Integer.parseInt(idp.getText()));
        reservation.setDate(dat.getText());
        reservation.setNomPatient(nomp.getText());
        reservation.setCout(Double.parseDouble(cc.getText()));
        reservation.setEstConfirmee(Boolean.parseBoolean(estc.getText()));

        if (!reservation.getServicesList().isEmpty()) {
            reservation.getServicesList().get(0).setIdService(Integer.parseInt(idm.getText()));
            reservation.getServicesList().get(1).setIdService(Integer.parseInt(ida.getText()));
            reservation.getServicesList().get(2).setIdService(Integer.parseInt(idv.getText()));
        }

        // Rafraîchir le TableView après la modification
        tvmeds.refresh();
    }

    private void displayReservationDetails(Reservation reservation) {
        id.setText(String.valueOf(reservation.getId()));
        idp.setText(String.valueOf(reservation.getIdpatient()));
        dat.setText(reservation.getDate());
        nomp.setText(reservation.getNomPatient());
        cc.setText(String.valueOf(reservation.getCout()));
        estc.setText(String.valueOf(reservation.isEstConfirmee()));

        if (!reservation.getServicesList().isEmpty()) {
            idm.setText(String.valueOf(reservation.getServicesList().get(0).getIdService()));
            ida.setText(String.valueOf(reservation.getServicesList().get(1).getIdService()));
            idv.setText(String.valueOf(reservation.getServicesList().get(2).getIdService()));
        }
    }

    private boolean isIdVExists(int idv) {
        boolean exists = false;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesVol.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int idFromService = Integer.parseInt(data[0].trim());

                if (idFromService == idv) {
                    exists = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private ServicesVol findServiceVolById(int id) {
        ServicesVol foundService = null;

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesVol.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int serviceId = Integer.parseInt(data[0]);

                if (serviceId == id) {

                    foundService = new ServicesVol(serviceId, data[1], data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]), data[5]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return foundService;
    }

    private ServicesMedical findServicesMedicalById(int id) {
        ServicesMedical foundServiceMedical = null;

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesMedical.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int serviceId = Integer.parseInt(data[0]);

                if (serviceId == id) {
                    // Assuming the structure of your ServicesMedical data:
                    // serviceId,field1,field2,field3...
                    foundServiceMedical = new ServicesMedical(
                            serviceId,
                            data[1], // Replace with actual field names
                            data[2],
                            Double.parseDouble(data[3]), data[4], new DM(Integer.parseInt(data[5]), data[6]), data[7]
                    // Continue with other fields...
                    );
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundServiceMedical;
    }

    private ServiceAccommodation findServiceAccommodationById(int id) {
        ServiceAccommodation foundServiceAccommodation = null;

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesAccommodation.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int serviceId = Integer.parseInt(data[0]);

                if (serviceId == id) {
                    // Assuming the structure of your ServicesAccommodation data:
                    // serviceId,field1,field2,field3...
                    foundServiceAccommodation = new ServiceAccommodation(
                            serviceId,
                            data[1], // Replace with actual field names
                            data[2],
                            Double.parseDouble(data[3]),
                            data[4],
                            data[5]
                    // Continue with other fields...
                    );
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundServiceAccommodation;
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\Reservations.txt"))) {
            for (Reservation reservation : reservationList) {
                StringBuilder servicesIds = new StringBuilder();
                for (Services service : reservation.getServicesList()) {
                    servicesIds.append(service.getIdService()).append(",");
                }
                servicesIds.deleteCharAt(servicesIds.length() - 1); // Remove the trailing comma

                String line = String.format("%d,%d,%s,%s,%.2f,%b,%s",
                        reservation.getId(), reservation.getIdpatient(), reservation.getNomPatient(),
                        reservation.getDate(), reservation.getCout(), reservation.isEstConfirmee(),
                        servicesIds.toString());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\Reservations.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                int idpatient = Integer.parseInt(data[1]);
                String nomPatient = data[2];
                String date = data[3];
                double cout = Double.parseDouble(data[4]);
                boolean estConfirmee = Boolean.parseBoolean(data[5]);

                List<Services> servicesList = new ArrayList<>();

                for (int i = 6; i < data.length; i++) {
                    int serviceId = Integer.parseInt(data[i]);

                    Services service = null;
                    ServicesMedical serviceMed = findServicesMedicalById(serviceId);
                    ServiceAccommodation serviceAcc = findServiceAccommodationById(serviceId);
                    ServicesVol serviceVol = findServiceVolById(serviceId);

                    if (serviceMed != null) {
                        service = serviceMed;
                    } else if (serviceAcc != null) {
                        service = serviceAcc;
                    } else if (serviceVol != null) {
                        service = serviceVol;
                    }

                    if (service != null) {
                        servicesList.add(service);
                    } else {
                        // Handle case when service ID is not found
                        System.out.println("Service not found for ID: " + serviceId);
                    }
                }

                Reservation res = new Reservation(id, idpatient, nomPatient, date, cout, estConfirmee);
                res.setServicesList(servicesList);
                reservationList.add(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
