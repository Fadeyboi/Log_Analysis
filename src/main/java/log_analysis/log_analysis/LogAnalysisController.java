package log_analysis.log_analysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



import java.io.*;
import java.net.URL;
import java.util.*;


public class LogAnalysisController implements Initializable, Serializable {
    @FXML
    private transient TextArea ta;
    @FXML
    private transient ComboBox<String> cb;
    @FXML
    private transient TextField commandField;
    private ArrayList<LogRecord> logRecords = new ArrayList<>();
    private ArrayList<LogRecord> filteredLogRecords = new ArrayList<>();
    private ArrayList<ArrayList<LogRecord>> history = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logRecords = FilePathController.getLogRecords();
        for (LogRecord logRecord : logRecords) {
            ta.setText(ta.getText() + logRecord);
        }
        cb.getItems().addAll("Date", "Time", "Timestamp", "IPAddress", "Username", "Role", "URL", "Description");
        history.add(logRecords);
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
                            if (logRecord.getURL().equalsIgnoreCase(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getURL().equalsIgnoreCase(filterAttribute)) {
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
                            if (logRecord.getIPAddress().equalsIgnoreCase(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getIPAddress().equalsIgnoreCase(filterAttribute)) {
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
                            if (logRecord.getDescription() != null) {
                                if (logRecord.getDescription().equalsIgnoreCase(filterAttribute)) {
                                    filteredLogRecords.add(logRecord);
                                }
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getDescription().equalsIgnoreCase(filterAttribute)) {
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
                            if (logRecord.getRole().equalsIgnoreCase(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getRole().equalsIgnoreCase(filterAttribute)) {
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
                            if (logRecord.getUsername().equalsIgnoreCase(filterAttribute)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getUsername().equalsIgnoreCase(filterAttribute)) {
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
            switch (comparisonOperator){
                case "=":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getDate().equals(date)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getDate().equals(date)) {
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
                case "<":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getDate().compareTo(date) < 0) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (logRecord.getDate().compareTo(date) >= 0) {
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
                case ">":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getDate().compareTo(date) > 0) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (logRecord.getDate().compareTo(date) <= 0) {
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
        else if(command.contains("timestamp")){
            String[] splitCommand = command.split(" ");
            String comparisonOperator = splitCommand[1];
            String timestamp = splitCommand[2];
            switch (comparisonOperator){
                case "=":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getTimestamp().equals(timestamp)) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (!logRecord.getTimestamp().equals(timestamp)) {
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
                case "<":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getTimestamp().compareTo(timestamp) < 0) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (logRecord.getTimestamp().compareTo(timestamp) >= 0) {
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
                case ">":
                    if (filteredLogRecords.isEmpty()) {
                        for (LogRecord logRecord : logRecords) {
                            if (logRecord.getTimestamp().compareTo(timestamp) > 0) {
                                filteredLogRecords.add(logRecord);
                            }
                        }
                    }
                    else {
                        Iterator<LogRecord> iterator = filteredLogRecords.iterator();
                        while (iterator.hasNext()) {
                            LogRecord logRecord = iterator.next();
                            if (logRecord.getTimestamp().compareTo(timestamp) <= 0) {
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
        history.add(filteredLogRecords);
    }

    @FXML
    public void comboBoxAction(ActionEvent e) {
        String selected = cb.getSelectionModel().getSelectedItem();
        boolean resetList = true;
        switch (selected) {
            case "Date":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new DateComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new DateComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
            case "Time":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new TimeComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new TimeComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }

                break;
            case "Timestamp":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new TimestampComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new TimestampComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
            case "IPAddress":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new IPAddressComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new IPAddressComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
            case "Username":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new UsernameComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new UsernameComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
            case "Role":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new RoleComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new RoleComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
            case "URL":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new URLComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new URLComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
            case "Description":
                if(filteredLogRecords.isEmpty()) {
                    logRecords.sort(new DescriptionComparator());
                    for (LogRecord logRecord : logRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                else {
                    filteredLogRecords.sort(new DescriptionComparator());
                    for (LogRecord logRecord : filteredLogRecords) {
                        if (resetList) {
                            ta.setText(logRecord.toString());
                            resetList = false;
                        } else {
                            ta.setText(ta.getText() + logRecord);
                        }
                    }
                }
                break;
        }
    }

    @FXML
    public void saveButtonAction(ActionEvent e) throws IOException {
        String filePath = ScreenController.showFilePath();
        if(filePath == null || filePath.trim().isEmpty()){
            showAlert();
        }
        else {
            File file = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this);
            } catch (FileNotFoundException error) {
                try {
                    showAlert();
                } catch (IOException ex) {
                    System.out.println("happened");
                }
            }
        }
    }

    @FXML
    public void loadButtonAction(ActionEvent e) throws IOException {
        String filePath = ScreenController.showFilePath();
        if(filePath == null || filePath.trim().isEmpty()){
            showAlert();
        }
        else {
            File file = new File(filePath);
            try (FileInputStream fis = new FileInputStream(file)) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                LogAnalysisController lac = (LogAnalysisController) ois.readObject();
                this.logRecords = lac.logRecords;
                this.filteredLogRecords = lac.filteredLogRecords;
                this.history = lac.history;
                ta.setText("");
                if(filteredLogRecords.isEmpty()) {
                    for (LogRecord logRecord : logRecords) {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
                else {
                    for (LogRecord logRecord : filteredLogRecords) {
                        ta.setText(ta.getText() + logRecord);
                    }
                }
            } catch (FileNotFoundException error) {
                try {
                    showAlert();
                } catch (IOException ex) {
                    System.out.println("happened");
                }
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void showAlert() throws IOException {
        ScreenController.showAlert();
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