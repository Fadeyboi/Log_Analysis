package log_analysis.log_analysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FilePathController {
    @FXML
    private Label Submitted;
    @FXML
    private TextField filePathTextField;
    @FXML
    private Button filePathBtn;
    @FXML
    private Button doneBtn;
    @FXML
    private Label doneLabel;
    private int counter = 0;
    private static ArrayList<String> filePaths = new ArrayList<>();

    @FXML
    protected void onFilePathButtonClick(ActionEvent e) {
        counter++;
        String text = filePathTextField.getText();
        filePaths.add(text);
        doneLabel.setText(counter + " file paths added.");
    }

    @FXML
    protected void onDoneButtonClick(ActionEvent e) throws IOException {
        ScreenController.addScreen("Log Records", FXMLLoader.load(Objects.requireNonNull(LogAnalysis.class.getResource("log-analysis.fxml"))));
        ScreenController.activate("Log Records");
        ScreenController.setName("Log Analyser");
        ScreenController.setSize(1040,805.0);
    }

    public static ArrayList<String> getFilePaths(){
        return filePaths;
    }
}
