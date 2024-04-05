package log_analysis.log_analysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SerializeFileController {
    @FXML
    private TextField serializeFileTextField;
    private static String filePath;

    public void onButtonAction(ActionEvent e){
        filePath = serializeFileTextField.getText();
        Stage stage = (Stage) serializeFileTextField.getScene().getWindow();
        stage.close();
    }

    public static String getFilePath() {
        return filePath;
    }
}
