package com.training.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.training.domain.Circle;

public class DomParser {

    private Document document;
    private List<Circle> circles = new ArrayList<>();

    public void initDoc(String inputXml) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(inputXml);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createCircles() {
        NodeList nodeList = document.getElementsByTagName("record");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i); 
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (!isExistNullElementTag(element)) {
                    addCircle(element);
                }
            }
        }
    }

    private void addCircle(Element element) {
        Circle circle = new Circle(
                Double.parseDouble(element.getElementsByTagName("latitude").item(0).getTextContent()), 
                Double.parseDouble(element.getElementsByTagName("longitude").item(0).getTextContent()), 
                element.getElementsByTagName("state").item(0).getTextContent()
                );
        circles.add(circle);
    }

    private boolean isExistNullElementTag(Element element) {
        return element.getElementsByTagName("latitude").item(0) == null ||
                element.getElementsByTagName("longitude").item(0) == null ||
                element.getElementsByTagName("state").item(0) == null;
    }

    public List<Circle> getCircles() {
        return circles;
    }


}
