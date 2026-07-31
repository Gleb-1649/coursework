package common.utils;

import common.model.Person;
import common.file.FileHandler;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlUtils {
    public Vector<Person> loadFromXml(String fileName) {
        Vector<Person> collection = new Vector<>();
        FileHandler fh = new FileHandler();
        String xmlContent = fh.readFromFile(fileName);
        if (xmlContent.isEmpty()) {
            return collection;
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
            doc.getDocumentElement().normalize();
            NodeList personNodes = doc.getElementsByTagName("person");
            for (int i = 0; i < personNodes.getLength(); i++) {
                Node node = personNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String id = elem.getElementsByTagName("id").item(0).getTextContent();
                    String name = elem.getElementsByTagName("name").item(0).getTextContent();
                    Long coordX = Long.parseLong(elem.getElementsByTagName("coordX").item(0).getTextContent());
                    Integer coordY = Integer.parseInt(elem.getElementsByTagName("coordY").item(0).getTextContent());
                    common.model.Coordinates coordinates = new common.model.Coordinates(coordX, coordY);
                    // creationDate не хранится в файле, используем текущее время
                    java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.now();
                    Float height = Float.parseFloat(elem.getElementsByTagName("height").item(0).getTextContent());
                    String weightStr = elem.getElementsByTagName("weight").item(0).getTextContent();
                    Float weight = weightStr.isBlank() ? null : Float.parseFloat(weightStr);
                    common.enumeration.Color eyeColor = null;
                    try {
                        String eyeColorStr = elem.getElementsByTagName("eyeColor").item(0).getTextContent();
                        if (!eyeColorStr.isBlank()) {
                            eyeColor = common.enumeration.Color.valueOf(eyeColorStr.toUpperCase());
                        }
                    } catch (Exception ignored) {}
                    String natStr = elem.getElementsByTagName("nationality").item(0).getTextContent();
                    common.enumeration.Country nationality = common.enumeration.Country.valueOf(natStr.toUpperCase());
                    Double locX = Double.parseDouble(elem.getElementsByTagName("locX").item(0).getTextContent());
                    int locY = Integer.parseInt(elem.getElementsByTagName("locY").item(0).getTextContent());
                    float locZ = Float.parseFloat(elem.getElementsByTagName("locZ").item(0).getTextContent());
                    String locName = elem.getElementsByTagName("locName").item(0).getTextContent();
                    common.model.Location location = new common.model.Location(locX, locY, locZ, locName);
                    Person person = new Person(id, name, coordinates, creationDate, height, weight, eyeColor, nationality, location);
                    collection.add(person);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке XML: " + e.getMessage());
        }
        return collection;
    }

    public void saveToXml(Vector<Person> collection, String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("persons");
            doc.appendChild(rootElement);
            for (Person p : collection) {
                Element personElem = doc.createElement("person");

                Element idElem = doc.createElement("id");
                idElem.appendChild(doc.createTextNode(p.getId()));
                personElem.appendChild(idElem);

                Element nameElem = doc.createElement("name");
                nameElem.appendChild(doc.createTextNode(p.getName()));
                personElem.appendChild(nameElem);

                Element coordXElem = doc.createElement("coordX");
                coordXElem.appendChild(doc.createTextNode(p.getCoordinates().getX().toString()));
                personElem.appendChild(coordXElem);

                Element coordYElem = doc.createElement("coordY");
                coordYElem.appendChild(doc.createTextNode(p.getCoordinates().getY().toString()));
                personElem.appendChild(coordYElem);

                Element heightElem = doc.createElement("height");
                heightElem.appendChild(doc.createTextNode(p.getHeight().toString()));
                personElem.appendChild(heightElem);

                Element weightElem = doc.createElement("weight");
                weightElem.appendChild(doc.createTextNode(p.getWeight() == null ? "" : p.getWeight().toString()));
                personElem.appendChild(weightElem);

                Element eyeColorElem = doc.createElement("eyeColor");
                eyeColorElem.appendChild(doc.createTextNode(p.getEyeColor() == null ? "" : p.getEyeColor().name()));
                personElem.appendChild(eyeColorElem);

                Element nationalityElem = doc.createElement("nationality");
                nationalityElem.appendChild(doc.createTextNode(p.getNationality().name()));
                personElem.appendChild(nationalityElem);

                if (p.getLocation() != null) {
                    Element locXElem = doc.createElement("locX");
                    locXElem.appendChild(doc.createTextNode(p.getLocation().getX().toString()));
                    personElem.appendChild(locXElem);

                    Element locYElem = doc.createElement("locY");
                    locYElem.appendChild(doc.createTextNode(Integer.toString(p.getLocation().getY())));
                    personElem.appendChild(locYElem);

                    Element locZElem = doc.createElement("locZ");
                    locZElem.appendChild(doc.createTextNode(Float.toString(p.getLocation().getZ())));
                    personElem.appendChild(locZElem);

                    Element locNameElem = doc.createElement("locName");
                    locNameElem.appendChild(doc.createTextNode(p.getLocation().getName()));
                    personElem.appendChild(locNameElem);
                }

                rootElement.appendChild(personElem);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")) {
                StreamResult result = new StreamResult(writer);
                transformer.transform(source, result);
            }
            System.out.println("Коллекция сохранена в файл " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении XML: " + e.getMessage());
        }
    }
}
