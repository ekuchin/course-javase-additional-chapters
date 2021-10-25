package ru.ekuchin.filetypes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class XmlCatBuilder {

    public static XmlCat[] readXML(String filename) throws Exception {
        File fXmlFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document xmldoc = dBuilder.parse(fXmlFile);

        NodeList nodeList = xmldoc.getElementsByTagName("cat");
        XmlCat[] result = new XmlCat[nodeList.getLength()];
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element nodeCat = (Element) nodeList.item(i);
            String name = nodeCat.getElementsByTagName("name").item(0).getTextContent();
            String breed = nodeCat.getElementsByTagName("breed").item(0).getTextContent();
            int weight = Integer.parseInt(nodeCat.getElementsByTagName("weight").item(0).getTextContent());
            boolean isAngry = nodeCat.getElementsByTagName("isAngry").item(0).getTextContent().equals("true");

            XmlCat tmpcat = new XmlCat(name,breed,weight,isAngry);
            result[i]=tmpcat;
        }
        return result;
    }

    public static void writeXml(XmlCat[] cats, String filename) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("cats");
        doc.appendChild(rootElement);

        for(XmlCat cat:cats){
            Element element = doc.createElement("cat");
            element.setAttribute("name", cat.getName());
            element.setAttribute("breed", cat.getBreed());
            element.setAttribute("weight", ""+cat.getWeight());
            element.setAttribute("isAngry", ""+cat.isAngry());
            rootElement.appendChild(element);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }

    public static void transformXml(String sourceFileName, String xslFileName, String outFileName) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslt = new StreamSource(new File(xslFileName));
        Transformer transformer = factory.newTransformer(xslt);

        StreamSource text = new StreamSource(new File(sourceFileName));
        transformer.transform(text, new StreamResult(new File(outFileName)));
    }

    public static boolean validateXml(String sourceFileName, String xsdFileName) throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(xsdFileName));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(sourceFileName)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JaxbCatCollection readJaxb(String filename) throws Exception {
        JAXBContext context = JAXBContext.newInstance(JaxbCatCollection.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (JaxbCatCollection) unmarshaller.unmarshal(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8));
    }

    public static void writeJaxb(JaxbCatCollection cats, String filename) throws Exception {

        JAXBContext context = JAXBContext.newInstance(JaxbCatCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // Можно записать в System.out
        //marshaller.marshal(cats, System.out);
        marshaller.marshal(cats, new File(filename));
    }

}
