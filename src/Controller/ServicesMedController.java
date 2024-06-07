/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Models.DM;
import Models.Reservation;
import Models.ServicesMedical;
import com.sun.jdi.connect.spi.Connection;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ServicesMedController implements Initializable {

    List<ServicesMedical> servicesMed;
    Connection conn;

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField cout;
    @FXML
    private TextField typemed;
    @FXML
    private TextField id_rec_pers;
    @FXML
    private TextField desc_rec;
    @FXML
    private TextField nomCli;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<ServicesMedical> tvmeds;
    @FXML
    private TableColumn<ServicesMedical, Integer> colid;
    @FXML
    private TableColumn<ServicesMedical, String> colnom;
    @FXML
    private TableColumn<ServicesMedical, String> coldescription;
    @FXML
    private TableColumn<ServicesMedical, Double> colcout;
    @FXML
    private TableColumn<ServicesMedical, String> coultypemed;
    @FXML
    private TableColumn<ServicesMedical, Integer> colidrec;
    @FXML
    private TableColumn<ServicesMedical, String> coldescrec;
    @FXML
    private TableColumn<ServicesMedical, String> colnomclinique;
    @FXML
    private BorderPane bp;

    public ServicesMedController() {
        servicesMed = new ArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadFromFile();

        // try {
        // conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismedical", "root", "root");
        //} catch (SQLException e) {
        //      e.printStackTrace(); 
        //}
        colid.setCellValueFactory(new PropertyValueFactory<>("idService"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nomService"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colcout.setCellValueFactory(new PropertyValueFactory<>("cout"));
        coultypemed.setCellValueFactory(new PropertyValueFactory<>("Typemed"));

        colidrec.setCellValueFactory(cellData -> {
            ServicesMedical service = cellData.getValue();
            return new SimpleIntegerProperty(service.getDossierMedical().getIdPatient()).asObject();
        });

        coldescrec.setCellValueFactory(cellData -> {
            ServicesMedical service = cellData.getValue();
            return new SimpleStringProperty(service.getDossierMedical().getDescription());
        });

        colnomclinique.setCellValueFactory(cellData -> {
            ServicesMedical service = cellData.getValue();
            return new SimpleStringProperty(service.getClinique());
        });

        refreshTableView();
    }

    private void refreshTableView() {
        ObservableList<ServicesMedical> data = FXCollections.observableArrayList(servicesMed);
        tvmeds.setItems(data);
    }

    @FXML
    private void AjouterOn(ActionEvent event) {
        if (isInputValid()) {
            int serviceId = Integer.parseInt(id.getText());
            String serviceName = nom.getText();
            String serviceDesc = description.getText();
            double serviceCout = Double.parseDouble(cout.getText());
            String serviceTypeMed = typemed.getText();
            int recordId = Integer.parseInt(id_rec_pers.getText());
            String recordDesc = desc_rec.getText();
            String clinicName = nomCli.getText();

            DM record = new DM(recordId, recordDesc);
            record.setIdPatient(recordId);
            record.setDescription(recordDesc);

            ServicesMedical newService = new ServicesMedical(serviceId, serviceName, serviceDesc, serviceCout, serviceTypeMed, record, clinicName);

            servicesMed.add(newService);
            refreshTableView();
            clearTextFields();
            saveToFile();
        }
        

    }

    @FXML
    private void ModifierOn(ActionEvent event) {
        ServicesMedical selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null && isInputValid()) {
            selectedItem.setIdService(Integer.parseInt(id.getText()));
            selectedItem.setNomService(nom.getText());
            selectedItem.setDescription(description.getText());
            selectedItem.setCout(Double.parseDouble(cout.getText()));
            selectedItem.setTypemed(typemed.getText());
            selectedItem.getDossierMedical().setIdPatient(Integer.parseInt(id_rec_pers.getText())); // Assuming this field name is idRecord
            selectedItem.getDossierMedical().setDescription(desc_rec.getText());
            selectedItem.setClinique(nomCli.getText());

            tvmeds.refresh();
            clearTextFields();
            saveToFile();

        }
    }

    @FXML
    private void retourclickk(ActionEvent event) throws IOException {
        bp.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Resserv.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("ResSerc Menu");
        resserv.setScene(scene);
        resserv.show();
    }

    @FXML
    private void SupprimerOn(ActionEvent event) {
        ServicesMedical selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            servicesMed.remove(selectedItem);

            refreshTableView();
            clearTextFields();
            saveToFile();

        }
    }

    @FXML
    private void onTableViewClicked(MouseEvent event) {
        ServicesMedical selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            id.setText(String.valueOf(selectedItem.getIdService()));
            nom.setText(selectedItem.getNomService());
            description.setText(selectedItem.getDescription());
            cout.setText(String.valueOf(selectedItem.getCout()));
            typemed.setText(selectedItem.getTypemed());
            id_rec_pers.setText(String.valueOf(selectedItem.getDossierMedical().getIdPatient())); // Assuming this field name is idRecord
            desc_rec.setText(selectedItem.getDossierMedical().getDescription());
            nomCli.setText(selectedItem.getClinique());
        }
    }
    
    private boolean isInputValid() {
    String errorMessage = "";

    if (id.getText() == null || id.getText().isEmpty()) {
        errorMessage += "ID non valide\n";
    }else{
        try {
                Integer.parseInt(id.getText());
            } catch (NumberFormatException e) {
                errorMessage += "ID doit être un entier\n";
            }
    }

    if (nom.getText() == null || nom.getText().isEmpty()) {
        errorMessage += "Nom non valide!\n";
    }

    if (description.getText() == null || description.getText().isEmpty()) {
        errorMessage += "Description non valide\n";
    }

    if (cout.getText() == null || cout.getText().isEmpty() ) {
        errorMessage += "Cout non valide\n";
    }else{
        try {
                Double.parseDouble(cout.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Cout doit être un réel\n";
            }
    }

    if (typemed.getText() == null || typemed.getText().isEmpty()) {
        errorMessage += "Type Médical non valide\n";
    }

    if (id_rec_pers.getText() == null || id_rec_pers.getText().isEmpty() || !id_rec_pers.getText().matches("\\d+")) {
        errorMessage += "ID Record non valide\n";
    }else{
        try {
                Integer.parseInt(id_rec_pers.getText());
            } catch (NumberFormatException e) {
                errorMessage += "ID Vol doit être un entier\n";
            }
    }

    if (desc_rec.getText() == null || desc_rec.getText().isEmpty()) {
        errorMessage += "Description Record non valide\n";
    }

    if (nomCli.getText() == null || nomCli.getText().isEmpty()) {
        errorMessage += "Nom Clinique non valide\n";
    }

    if (errorMessage.isEmpty()) {
        return true;
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs invalides");
        alert.setHeaderText("SVP corrigez les champs invalides");
        alert.setContentText(errorMessage);
        alert.showAndWait();
        return false;
    }
}


    private void clearTextFields() {
        id.clear();
        nom.clear();
        description.clear();
        cout.clear();
        typemed.clear();
        id_rec_pers.clear();
        desc_rec.clear();
        nomCli.clear();
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesMedical.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the format in the file is: id,name,desc,cout,typemed,id_rec_pers,desc_rec,nomClinique
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String desc = data[2];
                double cout = Double.parseDouble(data[3]);
                String typemed = data[4];
                int id_rec_pers = Integer.parseInt(data[5]);
                String desc_rec = data[6];
                String nomClinique = data[7];

                DM record = new DM(id_rec_pers, desc_rec);
                record.setIdPatient(id_rec_pers);
                record.setDescription(desc_rec);

                ServicesMedical service = new ServicesMedical(id, name, desc, cout, typemed, record, nomClinique);
                servicesMed.add(service);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesMedical.txt"))) {
            for (ServicesMedical service : servicesMed) {
                String line = String.format("%d,%s,%s,%.2f,%s,%d,%s,%s",
                        service.getIdService(), service.getNomService(), service.getDescription(),
                        service.getCout(), service.getTypemed(), service.getDossierMedical().getIdPatient(),
                        service.getDossierMedical().getDescription(), service.getClinique());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        
        bp.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("Home");
        resserv.setScene(scene);
        resserv.show();
        
    }

}
