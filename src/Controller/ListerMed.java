package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Models.Doctor;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ListerMed implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button ret;

    @FXML
    private TableView<Models.Doctor> tableMed;
    @FXML
    private TableColumn<Models.Doctor, Integer> col_id;
    @FXML
    private TableColumn<Models.Doctor, String> col_nom;
    @FXML
    private TableColumn<Models.Doctor, String> col_prenom;
    @FXML
    private TableColumn<Models.Doctor, String> col_Date;
    @FXML
    private TableColumn<Models.Doctor, String> col_tel;
    @FXML
    private TableColumn<Models.Doctor, String> col_adr;
    @FXML
    private TableColumn<Models.Doctor, String> col_spec;

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField date_N;
    @FXML
    private TextField tel;
    @FXML
    private TextField adr;
    @FXML
    private TextField spec;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    ObservableList<Models.Doctor> oblistMed = FXCollections.observableArrayList();

    private final String filePath = "data/doctors.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDoctorsFromTextFile();

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        col_adr.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_spec.setCellValueFactory(new PropertyValueFactory<>("specialite"));

        tableMed.setItems(oblistMed);
    }


    private void loadDoctorsFromTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nom = data[1];
                String prenom = data[2];
                String dateOfBirth = data[3];
                String tel = data[4];
                String adresse = data[5];
                String specialite = data[6];

                oblistMed.add(new Doctor(id, nom, prenom, adresse, dateOfBirth, tel, specialite));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveDoctorsToTextFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Doctor doctor : oblistMed) {
                String line = String.format("%d,%s,%s,%s,%s,%s,%s",
                        doctor.getID(), doctor.getNom(), doctor.getPrenom(), doctor.getDateNaissance(),
                        doctor.getNumTel(), doctor.getEmail(), doctor.getSpecialite());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void interAdm(ActionEvent e) throws IOException {
        Stage gadm = new Stage();
        ret.getScene().getWindow().hide();
        Parent root2 = FXMLLoader.load(getClass().getResource("/tourismemedical/InterfAdm.fxml"));
        Scene scene = new Scene(root2);
        gadm.setScene(scene);
        gadm.show();
        gadm.setResizable(false);
    }

    @FXML
    public void homee(ActionEvent e) throws IOException {
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root1 = FXMLLoader.load(getClass().getResource("/tourismemedical/home.fxml"));
        Scene scene = new Scene(root1);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    @FXML
    private void ajouterDoc(ActionEvent event) {
        try {
            int idValue = Integer.parseInt(id.getText());
            String nomValue = nom.getText();
            String prenomValue = prenom.getText();
            String dateNValue = date_N.getText();
            String telValue = tel.getText();
            String adrValue = adr.getText();  // Corrected order
            String specValue = spec.getText();

            // Create a new Doctor object with corrected parameter order
            Doctor newDoctor = new Doctor(idValue, nomValue, prenomValue, adrValue, dateNValue, telValue, specValue);

            // Add the new doctor to the observable list
            oblistMed.add(newDoctor);

            // Save the updated list to the text file
            saveDoctorsToTextFile();

            // Clear input fields after adding
            clearInputFields();
        } catch (NumberFormatException ex) {
            showAlert("Format incorrect", "Veuillez entrer des valeurs numériques valides.");
        }
    }








    @FXML
    private void modifierDoc(ActionEvent event) {
        Doctor selectedDoctor = tableMed.getSelectionModel().getSelectedItem();

        if (selectedDoctor != null) {
            // Remove the selected doctor
            oblistMed.remove(selectedDoctor);

            // Update the fields with the selected doctor's information
            id.setText(String.valueOf(selectedDoctor.getID()));
            nom.setText(selectedDoctor.getNom());
            prenom.setText(selectedDoctor.getPrenom());
            date_N.setText(selectedDoctor.getDateNaissance());
            tel.setText(selectedDoctor.getNumTel());
            adr.setText(selectedDoctor.getEmail());
            spec.setText(selectedDoctor.getSpecialite());

            // Save the modified list to the text file
            saveDoctorsToTextFile();
        } else {
            // No doctor selected, display a message or handle accordingly
            showAlert("Aucun médecin sélectionné", "Veuillez sélectionner un médecin à modifier.");
        }
    }

    @FXML
    private void supprimerDoc(ActionEvent event) {
        Doctor selectedDoctor = tableMed.getSelectionModel().getSelectedItem();

        if (selectedDoctor != null) {
            // Remove the selected doctor
            oblistMed.remove(selectedDoctor);

            // Save the modified list to the text file
            saveDoctorsToTextFile();
        } else {
            // No doctor selected, display a message or handle accordingly
            showAlert("Supprimer", "Aucun médecin sélectionné.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearInputFields() {
        // Assuming id, nom, prenom, date_N, tel, adr, and spec are your TextField IDs
        id.clear();
        nom.clear();
        prenom.clear();
        date_N.clear();
        tel.clear();
        adr.clear();
        spec.clear();
    }
}