package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField login;

    @FXML
    private PasswordField pswd;

    private Button homeBtn;


    @FXML
    private Button admBtn;
    @FXML
    private ImageView homeImg;
    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code, if needed
    }

    @FXML
    public void loginActionAdm(ActionEvent e) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\21650\\OneDrive\\Documents\\NetBeansProjects\\TourismeMedical\\data\\admins.txt"))) {
            String line;
            boolean isValid = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(login.getText()) && parts[1].equals(pswd.getText())) {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                System.out.println("Connexion Admin avec succès");

                Stage logp = new Stage();
                admBtn.getScene().getWindow().hide();
                Parent rootA = FXMLLoader.load(getClass().getResource("/tourismemedical/InterfAdm.fxml"));
                Scene scene = new Scene(rootA);
                logp.setScene(scene);
                logp.show();
                logp.setResizable(false);
            } else {
                showErrorAlert("Veuillez vérifier votre Identifiant et votre Mot de passe !");
            }

        } catch (IOException ex) {
            // Handle the exception or log it
        }
    }

    //@FXML
    //public void loginAdm(ActionEvent e) throws IOException {
        //Stage logp = new Stage();
        //adm.getScene().getWindow().hide();
        //Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/loginAdm.fxml"));
        //Scene scene = new Scene(root);
        //logp.setScene(scene);
        //logp.show();
        //logp.setResizable(false);
        
        //Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/loginAdm.fxml"));
        //Scene scene = new Scene(root);
        
        //Stage stage = new Stage() ;
        
       // stage.setTitle("ResServ Menu");
       // stage.setScene(scene);
        //stage.show();
        
    //}

    public void homee(ActionEvent e) throws IOException {
        Stage hm = new Stage();
        homeBtn.getScene().getWindow().hide();
        Parent root1 = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root1);
        hm.setScene(scene);
        hm.show();
        hm.setResizable(false);
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void backhommee(ActionEvent event) throws IOException {
        
        ap.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tourismemedical/Home.fxml"));
        Scene scene = new Scene(root);
        Stage resserv = new Stage();
        resserv.setTitle("Home");
        resserv.setScene(scene);
        resserv.show();
        
    }
}
