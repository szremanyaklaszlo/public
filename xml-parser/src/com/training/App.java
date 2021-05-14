package com.training;

import com.training.service.DomParser;
import com.training.service.Writer;

public class App {

    public static void main(String[] args) {
        DomParser parser = readXmlDocument();
        writeHtml(parser);
    }

    private static DomParser readXmlDocument() {
        DomParser parser = new DomParser();
        parser.initDoc("zip-full.xml");
        parser.createCircles();
        return parser;
    }

    private static void writeHtml(DomParser parser) {
        Writer writer = new Writer(parser.getCircles());
        writer.addElementsAndAttributes();
        writer.write("result.html");
    }

}
