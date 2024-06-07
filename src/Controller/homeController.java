package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class homeController implements Initializable {

    @FXML
    private Button adm;
    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void loginAdm(ActionEvent event) throws IOException {
        
        ap.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/tourismemedical/loginAdmin.fxml"));
        Scene scene = new Scene(root);
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("admin login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
