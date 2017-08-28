/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/2/2017
 */
package inputOutput;

/**
 *
 * @author nextjuanplz
 */
public class ConnectionData {
    //Fields
    private String type;
    private String url;
    private String ipaddress;
    private String port;
    private String database;
    private String login;
    private String password;
    
    //Methods
    @Override
    public String toString() { 
        //Conc to format jdbc:postgresql://localhost:5432/dvdrental
        return (this.url + "://" + this.ipaddress + ":"
                + this.port + "/" + this.database);     
    }
    
    //Getters and setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIpaddress() {
        return ipaddress;
    }
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
}
