package log_analysis.log_analysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController {
    @FXML
    private Button errorBtn;
    @FXML
    protected void onErrorButtonClick(ActionEvent e){
        Stage stage = (Stage) errorBtn.getScene().getWindow();
        stage.close();
    }
}
