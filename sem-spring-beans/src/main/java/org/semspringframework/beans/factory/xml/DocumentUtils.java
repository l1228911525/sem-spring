package org.semspringframework.beans.factory.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DocumentUtils {
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
        return null;
    }

    public static HashMap<String, String> getAttributeByNode(Node node) {
        return null;
    }

}
