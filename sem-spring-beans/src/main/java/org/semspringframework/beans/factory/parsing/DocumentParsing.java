package org.semspringframework.beans.factory.parsing;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DocumentParsing {
    public static List<Node> getNodeListByElement(Element element, String[] filterName) {

        List<Node> nodeList = new LinkedList<>();

        NodeList childNodes = element.getChildNodes();

        for(int i = 0; i < childNodes.getLength(); ++i) {
            Node item = childNodes.item(i);

            if(item.getNodeName() != null && Arrays.binarySearch(filterName, item.getNodeName()) >= 0) {
                nodeList.add(item);
            }
        }

        return nodeList;
    }

    public static List<Node> getNodeListByNode(Node node, String[] filterName) {
        List<Node> nodeList = new LinkedList<>();

        NodeList childNodes = node.getChildNodes();

        for(int i = 0; i < childNodes.getLength(); ++i) {
            Node item = childNodes.item(i);

            if (item.getNodeName() != null && Arrays.binarySearch(filterName, item.getNodeName()) >= 0) {
                nodeList.add(item);
            }
        }

        return nodeList;
    }

    public static HashMap<String, String> getAttributeByNode(Node node) {

        HashMap<String, String> attributeMap = new HashMap<>();

        NamedNodeMap attributes = node.getAttributes();

        for(int i = 0; i < attributes.getLength(); ++i) {
            Node item = attributes.item(i);

            attributeMap.put(item.getNodeName(), item.getNodeValue());

        }

        return attributeMap;
    }

}
