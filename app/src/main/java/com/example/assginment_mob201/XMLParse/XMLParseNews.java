package com.example.assginment_mob201.XMLParse;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParseNews {
    public Document getDocument(String urlXML) throws IOException, SAXException {
        Document document = null;
        DocumentBuilder documentBuilder = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("XML", e.toString());
        }
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(urlXML));
        inputSource.setEncoding("UTF-8");
        document = documentBuilder.parse(inputSource);
        return document;
    }

    public String getValue(Element item, String name) {
        NodeList nodeList = item.getElementsByTagName(name);
        String value = this.getTextNodeValue(nodeList.item(0));
        return value;
    }

    private final String getTextNodeValue(Node node) {
        Node child;
        if (node != null) {
            if (node.hasChildNodes()) {
                for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
