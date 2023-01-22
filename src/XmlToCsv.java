import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XmlToCsv {

    public static void main(String[] args) {
        try {
            File inputFile = new File("..\\student info.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            FileWriter csvWriter = new FileWriter("output.csv");
            String[] headers = {"id", "CourseName", "Instructor", "Courseduration", "Coursetime", "Location"};
            for (int i = 0; i < headers.length; i++) {
                csvWriter.append(headers[i]);
                if (i < headers.length - 1) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");
            NodeList nodes = doc.getElementsByTagName("row");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element row = (Element) nodes.item(i);
                for (int j = 0; j < headers.length; j++) {
                    NodeList data = row.getElementsByTagName(headers[j]);
                    Element dataElement = (Element) data.item(0);
                    csvWriter.append(dataElement.getTextContent());
                    if (j < headers.length - 1) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("File conversion successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}