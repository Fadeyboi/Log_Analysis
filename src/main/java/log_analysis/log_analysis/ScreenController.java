package log_analysis.log_analysis;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
}