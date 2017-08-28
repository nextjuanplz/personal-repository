/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/2/2017
 */
package inputOutput;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Juan Manuel Alzate Vanegas
 */

public class XmlParser {
    //Constructor
    public XmlParser(String parseString) {
        parseXmlFile(parseString);
    }
    
    //Fields
    private ConnectionData connectionData;
    private Document document;
    
    //Methods
    private void parseXmlFile(String str) {
        try {
            //Create document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = ClassLoader.getSystemResourceAsStream(str);
            document = builder.parse(is);
            //System.out.println(document.toString());

            //Start parsing file
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
               //Start node
               Node node = nodeList.item(i);
               //System.out.println(i);
               //Instantiate connectionData
               //connectionData = new ConnectionData();
               //Get each element from XML element
               connectionData.setType("org.postgresql.Driver");
               connectionData.setUrl(document.getElementsByTagName("url").toString());
               connectionData.setDatabase(document.getElementsByTagName("database").toString());
               connectionData.setIpaddress(document.getElementsByTagName("ipaddress").toString());
               System.out.println("ip = " + document.getElementsByTagName("ipaddress").toString());
               connectionData.setPort(document.getElementsByTagName("port").toString());
               connectionData.setLogin(document.getElementsByTagName("login").toString());
               connectionData.setPassword(document.getElementsByTagName("password").toString());
           }
        }
        catch (SAXException | IOException | ParserConfigurationException | IllegalArgumentException ex) {
            System.out.println("Fuck");
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    //Getter
    public ConnectionData getConnectionData() {
        return connectionData;
    }
}
