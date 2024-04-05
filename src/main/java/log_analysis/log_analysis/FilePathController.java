package log_analysis.log_analysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FilePathController {
    @FXML
    private TextField filePathTextField;
    @FXML
    private Label doneLabel;
    private int counter = 0;
    private static final ArrayList<LogRecord> logRecords = new ArrayList<>();

    @FXML
    protected void onFilePathButtonClick(ActionEvent e) throws IOException {
        String filePath = filePathTextField.getText();
        if(filePath == null || filePath.trim().isEmpty()){
            showAlert();
        }
        else{
            File inputFile = new File(filePath);
            try (Scanner input = new Scanner(inputFile)) {
                    while (input.hasNext()) {
                        String currentLine = input.nextLine();
                        LogRecord record = new LogRecord(currentLine);
                        logRecords.add(record);
                    }
                counter++;
                } catch(FileNotFoundException error){
                    try {
                        showAlert();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        doneLabel.setText(counter + " file paths added.");
        filePathTextField.setText("");
        filePathTextField.setPromptText("File added");
    }

    @FXML
    protected void onDoneButtonClick(ActionEvent e) throws IOException {

        ScreenController.addScreen("Log Records", FXMLLoader.load(Objects.requireNonNull(LogAnalysis.class.getResource("log-analysis.fxml"))));
        ScreenController.activate("Log Records");
        ScreenController.setName("Log Analyser");
        ScreenController.setSize(1040,805.0);
        ScreenController.center();
    }

    public void showAlert() throws IOException {
        ScreenController.showAlert();
    }

    public static ArrayList<LogRecord> getLogRecords(){
        return logRecords;
    }
}
