package log_analysis.log_analysis;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    public static Stage stage;
    private static final HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main;

    public static void setMain(Scene main){
        ScreenController.main = main;
    }

    public static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public static void removeScreen(String name){
        screenMap.remove(name);
    }

    public static Pane getScreen(String name){
        return screenMap.get(name);
    }

    public static void activate(String name){
        main.setRoot( screenMap.get(name) );
    }

    public static void center(){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void setName(String name){
        stage.setTitle(name);
    }

    public static void setSize(double x, double y){
        stage.setMinWidth(x);
        stage.setMinHeight(y);
    }

    public static String getName(){
        return stage.getTitle();
    }

    public static void setStage(Stage stage){
        ScreenController.stage = stage;
    }

    public static void showAlert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LogAnalysis.class.getResource("file-path-error.fxml"));
        double width = 340;
        double height = 139;
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage errorStage = new Stage();
        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setTitle("File Path Error");
        errorStage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        errorStage.setX((primScreenBounds.getWidth() - width) / 2);
        errorStage.setY((primScreenBounds.getHeight() - height) / 2);
        errorStage.showAndWait();
    }

    public static String showFilePath() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LogAnalysis.class.getResource("serialize-file-path.fxml"));
        double width = 600;
        double height = 88;
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Serialize File Path");
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        popupStage.setX((primScreenBounds.getWidth() - width) / 2);
        popupStage.setY((primScreenBounds.getHeight() - height) / 2);
        popupStage.setScene(scene);
        popupStage.showAndWait();
        return SerializeFileController.getFilePath();
    }
}