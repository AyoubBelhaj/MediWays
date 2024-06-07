/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

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
public class ServiceVolController implements Initializable {

    ObservableList<ServicesVol> servicesVol = FXCollections.observableArrayList();

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField cout;
    @FXML
    private TextField idvol;
    @FXML
    private TextField classevol;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TableView<ServicesVol> tvmeds;
    @FXML
    private TableColumn<ServicesVol, Integer> colid;
    @FXML
    private TableColumn<ServicesVol, String> colnom;
    @FXML
    private TableColumn<ServicesVol, String> coldescription;
    @FXML
    private TableColumn<ServicesVol, Double> colcout;
    @FXML
    private TableColumn<ServicesVol, Integer> colidvol;
    @FXML
    private TableColumn<ServicesVol, String> colclvol;
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
        colidvol.setCellValueFactory(cellData -> {
            ServicesVol service = cellData.getValue();
            return new SimpleIntegerProperty(service.getVol()).asObject();
        });

        colclvol.setCellValueFactory(cellData -> {
            ServicesVol service = cellData.getValue();
            return new SimpleStringProperty(service.getClasseVol());
        });
        
        tvmeds.setItems(servicesVol);
        tvmeds.refresh();
    }

    @FXML
    private void AjouterOn(ActionEvent event) {
        if(isInputValid()){
        
        int serviceId = Integer.parseInt(id.getText());
        String serviceName = nom.getText();
        String serviceDesc = description.getText();
        double serviceCout = Double.parseDouble(cout.getText());
        int idvoll = Integer.parseInt(idvol.getText());
        String classevoll = classevol.getText();

        ServicesVol newService = new ServicesVol(serviceId, serviceName, serviceDesc, serviceCout, idvoll, classevoll);
        servicesVol.add(newService);

        tvmeds.refresh();
        clearTextFields();
        saveToFile();
            
        }
        

    }

    @FXML
    private void ModifierOn(ActionEvent event) {

        ServicesVol selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null  && isInputValid()) {
            selectedItem.setIdService(Integer.parseInt(id.getText()));
            selectedItem.setNomService(nom.getText());
            selectedItem.setDescription(description.getText());
            selectedItem.setCout(Double.parseDouble(cout.getText()));
            selectedItem.setVol(Integer.parseInt(idvol.getText()));
            selectedItem.setClasseVol(classevol.getText());
            tvmeds.refresh();
            clearTextFields();
            saveToFile();

        }

    }

    @FXML
    private void SupprimerOn(ActionEvent event) {

        ServicesVol selectedItem = tvmeds.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            servicesVol.remove(selectedItem);

            tvmeds.refresh();
            clearTextFields();
            saveToFile();

        }

    }

    @FXML
    private void retourclick(ActionEvent event) throws IOException {
        bp.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Resserv.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("ResServ Menu");
        resserv.setScene(scene);
        resserv.show();
    }

    private void clearTextFields() {
        id.clear();
        nom.clear();
        description.clear();
        cout.clear();
        idvol.clear();
        classevol.clear();
    }

    @FXML
    private void onmouse(MouseEvent event) {

        ServicesVol SelectedItem = tvmeds.getSelectionModel().getSelectedItem();
        if (SelectedItem != null) {
            id.setText(String.valueOf(SelectedItem.getIdService()));
            nom.setText(String.valueOf(SelectedItem.getNomService()));
            description.setText(String.valueOf(SelectedItem.getDescription()));
            cout.setText(String.valueOf(SelectedItem.getCout()));
            idvol.setText(String.valueOf(SelectedItem.getVol()));
            classevol.setText(String.valueOf(SelectedItem.getClasseVol()));
        }
        System.out.println(tvmeds.getSelectionModel().getSelectedItem().getServiceDetails());

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

        if (description.getText() == null || description.getText().length() == 0) {
            errorMessage += "Description non valide\n";
        }

        if (cout.getText() == null || cout.getText().length() == 0) {
            errorMessage += "Cout non valide\n";
        } else {
            try {
                Double.parseDouble(cout.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Cout doit être un réel\n";
            }
        }

        if (idvol.getText() == null || idvol.getText().length() == 0) {
            errorMessage += "ID Vol non valide\n";
        } else {
            try {
                Integer.parseInt(idvol.getText());
            } catch (NumberFormatException e) {
                errorMessage += "ID Vol doit être un entier\n";
            }
        }

        if (classevol.getText() == null || classevol.getText().length() == 0) {
            errorMessage += "Classe Vol non valide\n";
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
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesVol.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the format in the file is: id,name,desc,cout,idvol,classevol
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String desc = data[2];
                double cout = Double.parseDouble(data[3]);
                int idvol = Integer.parseInt(data[4]);
                String classevol = data[5];

                servicesVol.add(new ServicesVol(id, name, desc, cout, idvol, classevol));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\ServicesVol.txt"))) {
            for (ServicesVol service : servicesVol) {
                String line = String.format("%d,%s,%s,%.2f,%d,%s",
                        service.getIdService(), service.getNomService(), service.getDescription(),
                        service.getCout(), service.getVol(), service.getClasseVol());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
