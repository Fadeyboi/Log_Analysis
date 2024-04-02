module log_analysis.log_analysis {
    requires javafx.controls;
    requires javafx.fxml;


    opens log_analysis.log_analysis to javafx.fxml;
    exports log_analysis.log_analysis;
}