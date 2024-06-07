/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ResservController implements Initializable {

    private VBox vb;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickres(ActionEvent event) throws Exception {
        
        ap.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/ResTableview.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("Reservations");
        resserv.setScene(scene);
        resserv.show();
        

    }

    @FXML
    private void clickservmed(ActionEvent event) throws Exception {
        ap.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/ServicesMed.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("ServicesMedicale");
        resserv.setScene(scene);
        resserv.show();

    }

    @FXML
    private void clickservacc(ActionEvent event) throws Exception {
        
        ap.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/ServicesAcc.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("ServicesAcc");
        resserv.setScene(scene);
        resserv.show();
        ap.getScene().getWindow().hide();
    }

    @FXML
    private void clickservvol(ActionEvent event) throws Exception {
        ap.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/ServiceVol.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("ServicesVol");
        resserv.setScene(scene);
        resserv.show();
        
    }

    @FXML
    private void goHotel(ActionEvent event) throws IOException {
        ap.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/Hotels.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("Hotels");
        resserv.setScene(scene);
        resserv.show();
        
    }

    @FXML
    private void goClinique(ActionEvent event) throws IOException {
        ap.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/Cliniques.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("Cliniques");
        resserv.setScene(scene);
        resserv.show();
        ap.getScene().getWindow().hide();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        ap.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/InterfAdm.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("Admin Interface Menu");
        resserv.setScene(scene);
        resserv.show();
        
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        
        ap.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("MediWays");
        resserv.setScene(scene);
        resserv.show();
        
    }

}
