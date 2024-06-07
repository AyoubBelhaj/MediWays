/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Models.Hotel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class HotelsController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField numtel;
    @FXML
    private TextField chtotale;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<Hotel> tvmeds;
    @FXML
    private TableColumn<Hotel, Integer> colid;
    @FXML
    private TableColumn<Hotel, Integer> colnom;
    @FXML
    private TableColumn<Hotel, String> coladresse;
    @FXML
    private TableColumn<Hotel, Integer> colnumtel;
    @FXML
    private TableColumn<Hotel, Integer> colchtot;

    private ObservableList<Hotel> hotelsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadFromFile();

        colid.setCellValueFactory(new PropertyValueFactory<>("idHotel"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nomHotel"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colchtot.setCellValueFactory(new PropertyValueFactory<>("chambreTotal"));

        tvmeds.setItems(hotelsList);
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
    private void AjouterOn(ActionEvent event) {

        if (isInputValid()) {
            int hotelId = Integer.parseInt(id.getText());
            String hotelName = nom.getText();
            String hotelAddress = adresse.getText();
            int hotelNumtel = Integer.parseInt(numtel.getText());
            int hotelChtotale = Integer.parseInt(chtotale.getText());

            Hotel newHotel = new Hotel(hotelId, hotelName, hotelAddress);
            newHotel.setNumtel(hotelNumtel);
            newHotel.setChambreTotal(hotelChtotale);

            hotelsList.add(newHotel);
            clearFields();
            saveToFile();
        }

    }

    @FXML
    private void ModifierOn(ActionEvent event) {

        Hotel selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null && isInputValid()) {
            selectedItem.setIdHotel(Integer.parseInt(id.getText()));
            selectedItem.setNomHotel(nom.getText());
            selectedItem.setAdresse(adresse.getText());
            selectedItem.setNumtel(Integer.parseInt(numtel.getText()));
            selectedItem.setChambreTotal(Integer.parseInt(chtotale.getText()));

            tvmeds.refresh();
            clearFields();
            saveToFile();
        }

    }

    @FXML
    private void SupprimerOn(ActionEvent event) {

        Hotel selectedHotel = tvmeds.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            id.setText(String.valueOf(selectedHotel.getIdHotel()));
            nom.setText(selectedHotel.getNomHotel());
            adresse.setText(selectedHotel.getAdresse());
            numtel.setText(String.valueOf(selectedHotel.getNumtel()));
            chtotale.setText(String.valueOf(selectedHotel.getChambreTotal()));
        }

    }

    private void clearFields() {
        id.clear();
        nom.clear();
        adresse.clear();
        numtel.clear();
        chtotale.clear();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (id.getText() == null || id.getText().isEmpty() || !id.getText().matches("\\d+")) {
            errorMessage += "ID non valide\n";
        }

        if (nom.getText() == null || nom.getText().isEmpty()) {
            errorMessage += "Nom non valide!\n";
        }

        if (adresse.getText() == null || adresse.getText().isEmpty()) {
            errorMessage += "Adresse non valide\n";
        }

        if (numtel.getText() == null || numtel.getText().isEmpty() || !numtel.getText().matches("\\d+")) {
            errorMessage += "Numéro de téléphone non valide\n";
        }

        if (chtotale.getText() == null || chtotale.getText().isEmpty() || !chtotale.getText().matches("\\d+")) {
            errorMessage += "Nombre de chambres total non valide\n";
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

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\Hotels.txt"))) {
            for (Hotel hotel : hotelsList) {
                String line = String.format("%d,%s,%s,%d,%d",
                        hotel.getIdHotel(), hotel.getNomHotel(), hotel.getAdresse(),
                        hotel.getNumtel(), hotel.getChambreTotal());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\Hotels.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];
                int numtel = Integer.parseInt(data[3]);
                int chambreTotal = Integer.parseInt(data[4]);

                Hotel hotel = new Hotel(id, name, address);
                hotel.setNumtel(numtel);
                hotel.setChambreTotal(chambreTotal);

                hotelsList.add(hotel);
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
