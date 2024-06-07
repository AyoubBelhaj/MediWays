package Controller;

import Models.Facture;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;

public class ListerFact implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button ret;
    @FXML
    private TableView<Facture> tableFact;
    @FXML
    private TableColumn<Models.Facture, Integer> col_fact;
    @FXML
    private TableColumn<Models.Facture, Double> col_mnt;
    @FXML
    private TableColumn<Models.Facture, Integer> col_pat;
    @FXML
    private ImageView homeImg;
    @FXML
    private Button imp;
    @FXML
    private TextField mnt;
    @FXML
    private TextField id_pat;
    @FXML
    private TextField num;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;

    ObservableList<Models.Facture> oblistFact = FXCollections.observableArrayList();

    private final String filePath = "factures.txt"; // Path to your text file

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFacturesFromTextFile(); // Assuming you have a method to load invoice data

        col_fact.setCellValueFactory(new PropertyValueFactory<>("iDFacture"));
        col_mnt.setCellValueFactory(new PropertyValueFactory<>("montant"));
        col_pat.setCellValueFactory(new PropertyValueFactory<>("iDPatient"));

        tableFact.setItems(oblistFact);
    }

    private void loadFacturesFromTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\factures.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int idFacture = Integer.parseInt(data[0]);
                double montant = Double.parseDouble(data[1]);
                int idPatient = Integer.parseInt(data[2]);

                oblistFact.add(new Models.Facture(idFacture, montant, idPatient));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFacturesToTextFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\factures.txt"))) {
            for (Models.Facture facture : oblistFact) {
                String line = String.format("%d,%f,%d",
                        facture.getIDFacture(), facture.getMontant(), facture.getIDPatient());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void interAdm(ActionEvent e) throws IOException {
        Stage gpat = new Stage();
        ret.getScene().getWindow().hide();
        Parent root2;
        root2 = FXMLLoader.load(getClass().getResource("/tourismemedical/InterfAdm.fxml"));
        Scene scene = new Scene(root2);
        gpat.setScene(scene);
        gpat.show();
        gpat.setResizable(false);
    }

    @FXML
    public void homee(ActionEvent e) throws IOException {
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root1;
        root1 = FXMLLoader.load(getClass().getResource("/tourismemedical/home.fxml"));
        Scene scene = new Scene(root1);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    @FXML
    private void imprimer(ActionEvent event) {
        Models.Facture selectedFacture = tableFact.getSelectionModel().getSelectedItem();
        if (selectedFacture != null) {
            int inumFact = selectedFacture.getIDFacture();
            double imontant = selectedFacture.getMontant();
            int iid_patient = selectedFacture.getIDPatient();

            // Your PDF creation code here
            // ...
            System.out.println("Facture N." + inumFact + " générée avec succès!");
        } else {
            // Handle the case where no item is selected
            System.out.println("Veuillez sélectionner une facture.");
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            int idFacture = Integer.parseInt(num.getText());
            double montant = Double.parseDouble(mnt.getText());
            int idPatient = Integer.parseInt(id_pat.getText());

            oblistFact.add(new Models.Facture(idFacture, montant, idPatient));
            saveFacturesToTextFile();
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer des valeurs numériques valides.");
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Models.Facture selectedFacture = tableFact.getSelectionModel().getSelectedItem();
        if (selectedFacture != null) {
            oblistFact.remove(selectedFacture);
            saveFacturesToTextFile();
        } else {
            System.out.println("Veuillez sélectionner une facture à supprimer.");
        }
    }
}
