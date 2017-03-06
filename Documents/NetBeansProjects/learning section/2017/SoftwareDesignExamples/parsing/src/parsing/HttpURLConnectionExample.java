package parsing;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        try {
            new HttpURLConnectionExample().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Student Id: ");
        String getId = input.next();
        
        URL url = new URL("http://129.32.92.49/xml/grade.php?id=" +getId);
        URLConnection connection = url.openConnection();

        Document doc = parseXML(connection.getInputStream());

        NodeList descNodes2 = doc.getElementsByTagName("name");
        NodeList descNodes = doc.getElementsByTagName("grade");
        System.out.println();
        for (int i = 0; i < descNodes.getLength(); i++) {
            System.out.println(descNodes2.item(i).getTextContent());
            System.out.println(descNodes.item(i).getTextContent());
        }
        
    }

    private Document parseXML(InputStream stream)
            throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }
}
