package log_analysis.log_analysis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogAnalysis extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LogAnalysis.class.getResource("file-path.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 130);
        stage.setTitle("File Path");
        stage.setScene(scene);
        stage.show();


        ScreenController.setStage(stage);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        ScreenController.setMain(scene);
        ScreenController.addScreen("File Path", FXMLLoader.load(Objects.requireNonNull(LogAnalysis.class.getResource("file-path.fxml"))));
    }


    public static void main(String[] args) {
        launch(args);
    }
}