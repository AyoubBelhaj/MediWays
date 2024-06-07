package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InterfaceAdm
{
    @FXML
    private Button homeBtn;
    @FXML
    private Button rdvs;

    @FXML
    private Button meds;
    @FXML
    private Button pats;
    @FXML
    private Button fctrs;
    @FXML
    private Button reser;





    @FXML
    private void affTabPat(ActionEvent e) throws IOException
    {
        Stage gpat = new Stage();
        pats.getScene().getWindow().hide();
        Parent root1;
        root1 = FXMLLoader.load(getClass().getResource("/tourismemedical/GererPat.fxml"));
        Scene scene = new Scene(root1);
        gpat.setScene(scene);
        gpat.show();
        gpat.setResizable(false);
    }

    @FXML
    private void affTabMed(ActionEvent e) throws IOException
    {
        Stage gmed = new Stage();
        meds.getScene().getWindow().hide();
        Parent root2;
        root2 = FXMLLoader.load(getClass().getResource("/tourismemedical/GererMed.fxml"));
        Scene scene = new Scene(root2);
        gmed.setScene(scene);
        gmed.show();
        gmed.setResizable(false);
    }



    @FXML
    private void affTabRdv(ActionEvent e) throws IOException
    {
        Stage gpat = new Stage();
        rdvs.getScene().getWindow().hide();
        Parent root4;
        root4 = FXMLLoader.load(getClass().getResource("/tourismemedical/GererRdv.fxml"));
        Scene scene = new Scene(root4);
        gpat.setScene(scene);
        gpat.show();
        gpat.setResizable(false);
    }



    @FXML
    private void affTabFact(ActionEvent e) throws IOException
    {
        Stage gpat = new Stage();
        fctrs.getScene().getWindow().hide();
        Parent root6;
        root6 = FXMLLoader.load(getClass().getResource("/tourismemedical/GererFact.fxml"));
        Scene scene = new Scene(root6);
        gpat.setScene(scene);
        gpat.show();
        gpat.setResizable(false);
    }


    @FXML
    public void homee(ActionEvent e) throws IOException
    {
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/home.fxml"));
        Scene scene = new Scene(root);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    @FXML
    private void Reservations(ActionEvent event) throws IOException {
        
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Resserv.fxml"));
        Scene scene = new Scene(root);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
        
    }
}
