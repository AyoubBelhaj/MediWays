package Controller;

import Models.RDV;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Models.RDV;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ListerRdv implements Initializable {

    @FXML
    private ImageView homeImg;
    @FXML
    private Button homeBtn;
    @FXML
    private Button ret;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Models.RDV> tableRdv;
    @FXML
    private TableColumn<Models.RDV, Integer> col_num;
    @FXML
    private TableColumn<Models.RDV, String> col_date;
    @FXML
    private TableColumn<Models.RDV, String> col_rq;
    @FXML
    private TableColumn<Models.RDV, Integer> col_pat;
    @FXML
    private TableColumn<Models.RDV, Integer> col_med;
    @FXML
    private TextField num;  // Add TextField for each input
    @FXML
    private TextField date;
    @FXML
    private TextField rque;
    @FXML
    private TextField id_pat;
    @FXML
    private TextField id_med;

    private final ObservableList<Models.RDV> oblistRdv = FXCollections.observableArrayList();

    private final String dataFilePath = "data/rdv_data.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadRdvDataFromFile();

        col_num.setCellValueFactory(new PropertyValueFactory<>("IDRDV"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateRDV"));
        col_rq.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_pat.setCellValueFactory(new PropertyValueFactory<>("patient"));
        col_med.setCellValueFactory(new PropertyValueFactory<>("doctor"));

        tableRdv.setItems(oblistRdv);
    }


    @FXML
    private void interAdm(ActionEvent e) throws IOException {
        Stage gpat = new Stage();
        ret.getScene().getWindow().hide();
        Parent root2 = FXMLLoader.load(getClass().getResource("/tourismemedical/InterfAdm.fxml"));
        Scene scene = new Scene(root2);
        gpat.setScene(scene);
        gpat.show();
        gpat.setResizable(false);
    }
    @FXML
    public void homee(ActionEvent e) throws IOException {
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root1 = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root1);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    @FXML
    private void ajouter(ActionEvent e) {
        try {
            int num_rdv = Integer.parseInt(num.getText());
            String date_rdv = date.getText();
            String remarque = rque.getText();
            int id_pat_value = Integer.parseInt(id_pat.getText());
            int id_med_value = Integer.parseInt(id_med.getText());

            RDV newRdv = new RDV(num_rdv, id_pat_value, remarque, id_med_value, date_rdv);
            oblistRdv.add(newRdv);
            tableRdv.refresh();
            saveRdvDataToFile();

            // Clear input fields after adding
            clearInputFields();
        } catch (NumberFormatException ex) {
            showAlert("Format incorrect", "Veuillez entrer des valeurs numériques valides.");
        }
    }

    @FXML
    private void modifier(ActionEvent e) {
        RDV selectedRdv = tableRdv.getSelectionModel().getSelectedItem();

        if (selectedRdv != null) {
            // Remove the selected RDV
            oblistRdv.remove(selectedRdv);

            // Update the fields with the selected RDV's information
            num.setText(String.valueOf(selectedRdv.getIDRDV()));
            date.setText(selectedRdv.getDateRDV());
            rque.setText(selectedRdv.getDescription());
            id_pat.setText(String.valueOf(selectedRdv.getPatient()));
            id_med.setText(String.valueOf(selectedRdv.getDoctor()));

            // Save the modified list to the text file
            saveRdvDataToFile();
        } else {
            // No RDV selected, display a message or handle accordingly
            showAlert("Aucun rendez-vous sélectionné", "Veuillez sélectionner un rendez-vous à modifier.");
        }
    }

    @FXML
    private void supprimer(ActionEvent e) {
        RDV selectedRdv = tableRdv.getSelectionModel().getSelectedItem();
        if (selectedRdv != null) {
            oblistRdv.remove(selectedRdv);
            saveRdvDataToFile();
        } else {
            // Display a message indicating that no item is selected for deletion
            showAlert("Supprimer", "Aucun rendez-vous sélectionné.");
        }
    }

    private void loadRdvDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\rdv_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int num_rdv = Integer.parseInt(fields[0]);
                String date_rdv = fields[1];
                String remarque = fields[2];
                int id_pat = Integer.parseInt(fields[3]);
                int id_med = Integer.parseInt(fields[4]);
                oblistRdv.add(new RDV(num_rdv, id_pat, remarque, id_med, date_rdv));
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Handle exceptions (e.g., file not found, invalid format, etc.)
            e.printStackTrace();
        }
    }


    private void saveRdvDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\rdv_data.txt"))) {
            for (RDV rdv : oblistRdv) {
                String line = String.format("%d,%s,%s,%d,%d",
                        rdv.getIDRDV(), rdv.getDateRDV(), rdv.getDescription(),
                        rdv.getPatient(), rdv.getDoctor());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found, etc.)
            e.printStackTrace();
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
        // Assuming num, date, rque, id_pat, and id_med are your TextField IDs
        num.clear();
        date.clear();
        rque.clear();
        id_pat.clear();
        id_med.clear();
    }
}