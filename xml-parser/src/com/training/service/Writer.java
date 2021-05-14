package com.training.service;

import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.training.domain.Circle;

public class Writer {

    private Document document;
    private List<Circle> circles;

    public Writer(List<Circle> circles) {
        this.circles = circles;
        createNewDocument();
    }

    private void createNewDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            this.document = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void addElementsAndAttributes() {
        Element html = createHtml();
        Element body = createBody(html);
        Element svg = createSvg(body);
        createWidth(svg);
        createHight(svg);
        for (Circle circle : circles) {
            Element circleElement = createCircle(svg);
            setWidth(circle, circleElement);
            setHight(circle, circleElement);
            setCircleFill(circle, circleElement);
            setCircleRation(circleElement);
        }
    }

    private Element createHtml() {
        Element rootElement = document.createElement("html");
        document.appendChild(rootElement);
        return rootElement;
    }

    private Element createBody(Element rootElement) {
        Element body = document.createElement("body");
        rootElement.appendChild(body);
        return body;
    }

    private Element createSvg(Element body) {
        Element svg = document.createElement("svg");
        body.appendChild(svg);
        return svg;
    }

    private void createWidth(Element svg) {
        Attr attrWidth = document.createAttribute("width");
        attrWidth.setValue("1700");
        svg.setAttributeNode(attrWidth);
    }

    private void createHight(Element svg) {
        Attr attrHight = document.createAttribute("height");
        attrHight.setValue("800");
        svg.setAttributeNode(attrHight);
    }

    private Element createCircle(Element svg) {
        Element circleElement = document.createElement("circle");
        svg.appendChild(circleElement);
        return circleElement;
    }

    private void setWidth(Circle circle, Element circleElement) {
        Attr attributeCy = document.createAttribute("cy");
        attributeCy.setValue(String.format(Locale.US, "%f", circle.getLatitude()));
        circleElement.setAttributeNode(attributeCy);
    }

    private void setHight(Circle circle, Element circleElement) {
        Attr attributeCx = document.createAttribute("cx");
        attributeCx.setValue(String.format(Locale.US, "%f", circle.getLongitude()));
        circleElement.setAttributeNode(attributeCx);
    }

    private void setCircleFill(Circle circle, Element circleElement) {
        Attr attributeFill = document.createAttribute("fill");
        attributeFill.setValue(circle.getStateColorCode());
        circleElement.setAttributeNode(attributeFill);
    }

    private void setCircleRation(Element circleElement) {
        Attr attributeRation = document.createAttribute("r");
        attributeRation.setValue("1");
        circleElement.setAttributeNode(attributeRation);
    }

    public void write(String fileName) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(new DOMSource(document), result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
