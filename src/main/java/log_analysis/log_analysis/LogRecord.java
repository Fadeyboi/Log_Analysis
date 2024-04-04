package log_analysis.log_analysis;

public class LogRecord {
    private String date;
    private String time;
    private String timestamp;
    private String IPAddress;
    private String username;
    private String role;
    private String url;
    private String description;

    public LogRecord(String currentLine) {
        String[] attributes = currentLine.split("-");
        if(attributes.length == 7){
            this.date = attributes[0];
            this.time = attributes[1];
            this.timestamp = attributes[2];
            this.IPAddress = attributes[3];
            this.username = attributes[4];
            this.role = attributes[5];
            this.url = attributes[6];
            this.description = null;
        }
        else if(attributes.length == 8){
            this.date = attributes[0];
            this.time = attributes[1];
            this.timestamp = attributes[2];
            this.IPAddress = attributes[3];
            this.username = attributes[4];
            this.role = attributes[5];
            this.url = attributes[6];
            this.description = attributes[7];
        }
    }

    @Override
    public String toString(){
        return this.date + "-" + this.time + "-" + this.timestamp + "-" + this.IPAddress + "-" + this.username + "-" + this.role + "-" + this.url + "-" + this.description + "\n";
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getURL() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}