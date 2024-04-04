package log_analysis.log_analysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;


public class LogAnalysisController implements Initializable {
    @FXML
    private TextArea ta;
    @FXML
    private ComboBox<String> cb;
    @FXML
    private Button filterBtn;
    @FXML
    private TextField commandField;
    private ArrayList<LogRecord> logRecords = new ArrayList<>();
    private ArrayList<LogRecord> filteredLogRecords = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> filePaths = FilePathController.getFilePaths();
        for (String filePath : filePaths) {
            File inputFile = new File(filePath);
            try (Scanner input = new Scanner(inputFile)) {
                while (input.hasNext()) {
                    String currentLine = input.nextLine();
                    LogRecord record = new LogRecord(currentLine);
                    logRecords.add(record);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        for (LogRecord logRecord : logRecords) {
            ta.setText(ta.getText() + logRecord);
        }
        cb.getItems().addAll("Date", "Time", "Timestamp", "IPAddress", "Username", "Role", "URL", "Description");

    }

    @FXML
    protected void onFilterButtonClick(ActionEvent e) {
        String command = commandField.getText().toLowerCase();
        boolean resetList = true;
        if(command.contains("contains")){
            String[] splitAttribute = command.split("\\.");
            String[] splitCommand = command.split("([()])");
            String commandAttribute = splitAttribute[0];
            String filterAttribute = splitCommand[1];
            filterAttribute = filterAttribute.substring(1, filterAttribute.length()-1);
            switch (commandAttribute.trim()){
                case "url":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getURL().equals(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getURL().equals(filterAttribute)) {
                                iterator.remove();
                            }
                        }
                    }
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                    break;
                case "ipaddress":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getIPAddress().equals(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getIPAddress().equals(filterAttribute)) {
                                iterator.remove();
                            }
                        }
                    }
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                    break;
                case "description":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getDescription().equals(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getDescription().equals(filterAttribute)) {
                                iterator.remove();
                            }
                        }
                    }
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                    break;
                case "role":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getRole().equals(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getRole().equals(filterAttribute)) {
                                iterator.remove();
                            }
                        }
                    }
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                    break;
                case "username":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getUsername().equals(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getUsername().equals(filterAttribute)) {
                                iterator.remove();
                            }
                        }
                    }
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                    break;
            }
        }
        else if(command.contains("date")){
            String[] splitCommand = command.split(" ");
            String comparisonOperator = splitCommand[1];
            String date = splitCommand[2].substring(1, splitCommand[2].length()-1);
            System.out.println(comparisonOperator);
            System.out.println(date);
        }
    }

    @FXML
    public void comboBoxAction(ActionEvent e) {
        String selected = cb.getSelectionModel().getSelectedItem();
        boolean resetList = true;
        switch (selected) {
            case "Date":
                logRecords.sort(new DateComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "Time":
                logRecords.sort(new TimeComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "Timestamp":
                logRecords.sort(new TimestampComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "IPAddress":
                logRecords.sort(new IPAddressComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "Username":
                logRecords.sort(new UsernameComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "Role":
                logRecords.sort(new RoleComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "URL":
                logRecords.sort(new URLComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
            case "Description":
                logRecords.sort(new DescriptionComparator());
                for (LogRecord logRecord : logRecords) {
                    if (resetList) {
                        ta.setText(logRecord.toString());
                        resetList = false;
                    } else {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                break;
        }
    }
}

class DateComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getDate().compareToIgnoreCase(lr2.getDate());
    }
}

class TimeComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getTime().compareToIgnoreCase(lr2.getTime());
    }
}

class TimestampComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getTimestamp().compareToIgnoreCase(lr2.getTimestamp());
    }
}

class IPAddressComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getIPAddress().compareToIgnoreCase(lr2.getIPAddress());
    }
}

class UsernameComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getUsername().compareToIgnoreCase(lr2.getUsername());
    }
}

class RoleComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getRole().compareToIgnoreCase(lr2.getRole());
    }
}

class URLComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        return lr1.getURL().compareToIgnoreCase(lr2.getURL());
    }
}

class DescriptionComparator implements Comparator<LogRecord> {
    public int compare(LogRecord lr1, LogRecord lr2) {
        if (lr1.getDescription() == null){
            return 1;
        } else if (lr2.getDescription() == null) {
            return -1;
        }
        else {
            return lr1.getDescription().compareToIgnoreCase(lr2.getDescription());
        }
    }
}