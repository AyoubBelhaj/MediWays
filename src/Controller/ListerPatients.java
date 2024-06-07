package Controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Models.Patient;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ListerPatients implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button ret;

    @FXML
    private TableView<Patient> tablePat;
    @FXML
    private TableColumn<Patient, Integer> col_id;
    @FXML
    private TableColumn<Patient, String> col_nom;
    @FXML
    private TableColumn<Patient, String> col_prenom;
    @FXML
    private TableColumn<Patient, String> col_DateN;
    @FXML
    private TableColumn<Patient, Integer> col_tel;
    @FXML
    private TableColumn<Patient, String> col_adr;
    @FXML
    private TableColumn<Patient, String> col_nat;

    ObservableList<Patient> oblistPat = FXCollections.observableArrayList();
    @FXML
    private ImageView homeImg;
    @FXML
    private TextField tel;
    @FXML
    private TextField adr;
    @FXML
    private TextField dateN;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nat;
    @FXML
    private TextField id;
    @FXML
    private Button modifier;
    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPatientsFromTextFile();
        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_DateN.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        col_adr.setCellValueFactory(cellData -> {
            Patient patient = cellData.getValue();
            return new SimpleStringProperty(patient.getEmail());
        });

        col_nat.setCellValueFactory(new PropertyValueFactory<>("nationality"));

        tablePat.setItems(oblistPat);
    }

    private void loadPatientsFromTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\patients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nom = data[1];
                String prenom = data[2];
                String adresse = data[5];
                String dateN = data[3];
                String tel = data[4];

                String nationality = data[6];

                oblistPat.add(new Patient(id, nom, prenom, adresse, dateN, tel, nationality));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void savePatientsToTextFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\patients.txt"))) {
            for (Patient patient : oblistPat) {
                String line = String.format("%d,%s,%s,%s,%s,%s,%s",
                        patient.getID(), patient.getNom(), patient.getPrenom(), patient.getEmail(),
                        patient.getDateNaissance(), patient.getNumTel(), patient.getNationality());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void interAdm(ActionEvent e) throws IOException {
        ap.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/InterfAdm.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Admin Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void homee(ActionEvent e) throws IOException {
        ap.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Media Ways");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ajouterPat(ActionEvent event) {
        oblistPat.add(new Patient(Integer.parseInt(id.getText()), nom.getText(), prenom.getText(),
                dateN.getText(), tel.getText(), adr.getText(), nat.getText()));

        savePatientsToTextFile();
    }

    @FXML
    private void suprPat(ActionEvent event) {
        Patient selectedPatient = tablePat.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            oblistPat.remove(selectedPatient);
            savePatientsToTextFile(); // Save changes to the file
        } else {
            // Show an alert or message indicating that no item is selected
            showAlert("Aucun patient sélectionné", "Veuillez sélectionner un patient à supprimer.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void fichePat(ActionEvent event) {
    }
}
