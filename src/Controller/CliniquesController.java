/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Models.Clinique;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class CliniquesController implements Initializable {

    ObservableList<Clinique> cliniquesList = FXCollections.observableArrayList();

    @FXML
    private BorderPane bp;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<Clinique> tvmeds;
    @FXML
    private TableColumn<Clinique, Integer> colid;
    @FXML
    private TableColumn<Clinique, String> colnom;
    @FXML
    private TableColumn<Clinique, String> coladresse;
    @FXML
    private TextField specialite;
    @FXML
    private TableColumn<Clinique, String> colspec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadFromFile();
        
        colid.setCellValueFactory(new PropertyValueFactory<>("idClinique"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nomClinique"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colspec.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        
        tvmeds.setItems(cliniquesList);
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
            int clinicId = Integer.parseInt(id.getText());
            String clinicName = nom.getText();
            String clinicAddress = adresse.getText();
            String clinicSpecialite = specialite.getText();

            Clinique newClinic = new Clinique(clinicId, clinicName, clinicAddress, clinicSpecialite);
            cliniquesList.add(newClinic);

            tvmeds.refresh();
            clearTextFields();
            saveToFile();
        }

    }

    @FXML
    private void ModifierOn(ActionEvent event) {

        Clinique selectedItem = (Clinique) tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null && isInputValid()) {
            selectedItem.setIdClinique(Integer.parseInt(id.getText()));
            selectedItem.setNomClinique(nom.getText());
            selectedItem.setAdresse(adresse.getText());
            selectedItem.setSpecialite(specialite.getText());
            tvmeds.refresh();
            clearTextFields();
            saveToFile();
        }

    }

    @FXML
    private void SupprimerOn(ActionEvent event) {

        Clinique selectedItem = (Clinique) tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            cliniquesList.remove(selectedItem);
            tvmeds.refresh();
            clearTextFields();
            saveToFile();
        }

    }

    @FXML
    private void onmouse(MouseEvent event) {

        Clinique selectedItem = (Clinique) tvmeds.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            id.setText(String.valueOf(selectedItem.getIdClinique()));
            nom.setText(selectedItem.getNomClinique());
            adresse.setText(selectedItem.getAdresse());
            specialite.setText(selectedItem.getSpecialite());
        }

    }

    private void clearTextFields() {
        id.clear();
        nom.clear();
        adresse.clear();
        specialite.clear();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (id.getText() == null || id.getText().length() == 0) {
            errorMessage += "ID non valide\n";
        } else {
            try {
                Integer.parseInt(id.getText());
            } catch (NumberFormatException e) {
                errorMessage += "ID doit être un entier\n";
            }
        }

        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "Nom non valide!\n";
        }

        if (adresse.getText() == null || adresse.getText().length() == 0) {
            errorMessage += "Adresse non valide\n";
        }

        if (specialite.getText() == null || specialite.getText().length() == 0) {
            errorMessage += "Spécialité non valide\n";
        }

        if (errorMessage.length() == 0) {
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

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\Cliniques.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];
                String specialite = data[3];

                Clinique clinic = new Clinique(id, name, address, specialite);
                cliniquesList.add(clinic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\Cliniques.txt"))) {
            for (Clinique clinique : cliniquesList) {
                String line = String.format("%d,%s,%s,%s",
                        clinique.getIdClinique(), clinique.getNomClinique(), clinique.getAdresse(), clinique.getSpecialite());
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
