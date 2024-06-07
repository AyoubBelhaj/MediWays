/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Models.ServiceAccommodation;
import Models.ServicesMedical;
import static com.sun.javafx.logging.PlatformLogger.Level.WARNING;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ServicesAccController implements Initializable {

    ObservableList<ServiceAccommodation> listserv = FXCollections.observableArrayList();

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField cout;
    @FXML
    private TextField typeacc;
    @FXML
    private TextField nomhotel;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<ServiceAccommodation> tvmeds;
    @FXML
    private TableColumn<ServiceAccommodation, Integer> colid;
    @FXML
    private TableColumn<ServiceAccommodation, String> colnom;
    @FXML
    private TableColumn<ServiceAccommodation, String> coldescription;
    @FXML
    private TableColumn<ServiceAccommodation, Double> colcout;
    @FXML
    private TableColumn<ServiceAccommodation, String> coltacc;
    @FXML
    private TableColumn<ServiceAccommodation, String> colnomhot;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadFromFile();
        colid.setCellValueFactory(new PropertyValueFactory<>("idService"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nomService"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colcout.setCellValueFactory(new PropertyValueFactory<>("cout"));
        coltacc.setCellValueFactory(cellData -> {
            ServiceAccommodation service = cellData.getValue();
            return new SimpleStringProperty(service.getTypeAccommodation());
        });
        colnomhot.setCellValueFactory(cellData -> {
            ServiceAccommodation service = cellData.getValue();
            return new SimpleStringProperty(service.getHotel());
        });

        listserv.addAll(new ServiceAccommodation(1, "hh", "hehrhhehe", 5000.55, "tt", "4toilees"), new ServiceAccommodation(1, "hh", "hehrhhehe", 5000.55, "tt", "4toilees"), new ServiceAccommodation(1, "hh", "hehrhhehe", 5000.55, "tt", "4toilees"));
        tvmeds.setItems(listserv);

    }

    @FXML
    private void AjouterOn(ActionEvent event) {

        int serviceId = Integer.parseInt(id.getText());
        String serviceName = nom.getText();
        String serviceDesc = description.getText();
        double serviceCout = Double.parseDouble(cout.getText());
        String serviceTypeACC = typeacc.getText();

        String clinicName = nomhotel.getText();

        ServiceAccommodation newService = new ServiceAccommodation(serviceId, serviceName, serviceDesc, serviceCout, serviceTypeACC, clinicName);

        listserv.add(newService);
        tvmeds.refresh();
        clearTextFields();
        saveToFile();

    }

    @FXML
    private void ModifierOn(ActionEvent event) {

        ServiceAccommodation selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            selectedItem.setIdService(Integer.parseInt(id.getText()));
            selectedItem.setNomService(nom.getText());
            selectedItem.setDescription(description.getText());
            selectedItem.setCout(Double.parseDouble(cout.getText()));
            selectedItem.setTypeAccommodation(typeacc.getText());
            selectedItem.setHotel(nomhotel.getText());
            tvmeds.refresh();
            clearTextFields();
            saveToFile();

        }

    }

    @FXML
    private void SupprimerOn(ActionEvent event) {

        int SelectedIndex = tvmeds.getSelectionModel().getSelectedIndex();
        if (SelectedIndex >= 0) {
            tvmeds.getItems().remove(SelectedIndex);
            saveToFile();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("aucun item selecte");
            alert.setHeaderText("Il nya pas item pour le supprimer");
            alert.setContentText("Il faut selectionner le item de tableau");
            alert.showAndWait();
        }

    }

    @FXML
    private void retourclickk(ActionEvent event) throws IOException {
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
    private void afficherconsole(MouseEvent event) {

        ServiceAccommodation SelectedItem = tvmeds.getSelectionModel().getSelectedItem();
        if (SelectedItem != null) {
            id.setText(String.valueOf(SelectedItem.getIdService()));
            nom.setText(String.valueOf(SelectedItem.getNomService()));
            description.setText(String.valueOf(SelectedItem.getDescription()));
            cout.setText(String.valueOf(SelectedItem.getCout()));
            typeacc.setText(String.valueOf(SelectedItem.getTypeAccommodation()));
            nomhotel.setText(String.valueOf(SelectedItem.getHotel()));
        }
        System.out.println(tvmeds.getSelectionModel().getSelectedItem().getServiceDetails());

    }

    private void clearTextFields() {
        id.clear();
        nom.clear();
        description.clear();
        cout.clear();
        typeacc.clear();
        nomhotel.clear();
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesAccommodation.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the format in the file is: id,name,desc,cout,typeacc,nomhotel
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String desc = data[2];
                double cout = Double.parseDouble(data[3]);
                String typeAcc = data[4];
                String nomHotel = data[5];

                listserv.add(new ServiceAccommodation(id, name, desc, cout, typeAcc, nomHotel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesAccommodation.txt"))) {
            for (ServiceAccommodation service : listserv) {
                String line = String.format("%d,%s,%s,%.2f,%s,%s",
                        service.getIdService(), service.getNomService(), service.getDescription(),
                        service.getCout(), service.getTypeAccommodation(), service.getHotel());
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
