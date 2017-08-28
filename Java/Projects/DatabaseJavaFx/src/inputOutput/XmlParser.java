/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/1/2017
 */
package inputOutput;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    private void parseXmlFile(String fileName) {        
        //Get the DOM Builder Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Load and Parse the XML document
            //document contains the complete XML as a Tree.
            document = db.parse(ClassLoader.getSystemResourceAsStream(fileName));
 
            //Iterating through the nodes and extracting the data.
            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for(int i = 0; i < nodeList.getLength(); i++) {
                /** get the <driver> element */
                Node node = nodeList.item(i);

                if(node instanceof Element) {
                    //for each <driver> element get text or int values of
                    //name ,id, age and name
                    String type = node.getAttributes().getNamedItem("type").getNodeValue();
                    //Create a new ConnectionData
                    connectionData = new ConnectionData();
                    connectionData.setType(type);
                    
                    NodeList childNodes = node.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++)  {
                        Node cNode = childNodes.item(j);  
 
                        //Identifying the child tag of employee encountered.
                        if (cNode instanceof Element) {
                            String content = cNode.getLastChild().getTextContent().trim();
                            switch (cNode.getNodeName()) {
                                case "url":
                                  connectionData.setUrl(content);
                                  break;
                                case "ipaddress":
                                  connectionData.setIpaddress(content);
                                  break;
                                case "port":
                                  connectionData.setPort(content);
                                  break;
                                case "database":
                                  connectionData.setDatabase(content);
                                  break;
                                case "login":
                                  connectionData.setLogin(content);
                                  break;
                                case "password":
                                  connectionData.setPassword(content);
                                  break;
                            }
                        }
                    }
                }
            }
        } catch(ParserConfigurationException | SAXException | IOException pce) {
            pce.printStackTrace();
        }
    }
    
    //Getter
    public ConnectionData getConnectionData() {
        return connectionData;
    }
}
