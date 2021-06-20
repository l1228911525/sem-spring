package org.semspringframework.beans.factory.xml;

import org.semspringframework.beans.factory.parsing.DocumentParsing;
import org.semspringframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.semspringframework.beans.factory.support.BeanDefinition;
import org.semspringframework.beans.factory.support.RefType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private final String BEAN_NAME = "bean";

    private final String CONSTRUCTOR_ARG = "constructor-arg";

    private final String PROPERTY = "property";

    /**
     * loading inputstream of the xml file， return all beandefinition of the xml file
     */
    @Override
    public void doLoadBeanDefinition(InputStream inputStream) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.parse(inputStream);

            Element rootElement = document.getDocumentElement();

            List<Node> nodeList = DocumentParsing.getNodeListByElement(rootElement, new String[]{"bean"});



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("fail to parse document");
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println("fail to parse document");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to parse document");
        }
    }

    public BeanDefinition getBeanDefinition(Node node) {

        BeanDefinition beanDefinition = new BeanDefinition();

        HashMap<String, String> attributeMap = DocumentParsing.getAttributeByNode(node);

        beanDefinition.setBeanName(attributeMap.get("id"));
        try {
            beanDefinition.setBeanClass(Class.forName(attributeMap.get("class")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("fail to generate the class object using bean name");
        }
        beanDefinition.setSingleton(Boolean.parseBoolean(attributeMap.get("singleton")));
        beanDefinition.setLazy(Boolean.parseBoolean(attributeMap.get("lazy")));
        beanDefinition.setInitMethod(attributeMap.get("initMethod"));
        beanDefinition.setDestroyMethod(attributeMap.get("destroyMethod"));

        // get set and init parameter
        List<Node> parameterNodes = DocumentParsing.getNodeListByNode(node, new String[]{CONSTRUCTOR_ARG, PROPERTY});

        for (Node parameter : parameterNodes) {

            HashMap<String, String> paramMap = DocumentParsing.getAttributeByNode(parameter);

            String name = paramMap.get("name");

            String value = paramMap.get("value");

            String ref = paramMap.get("ref");

            Object paramObject = null != value ? value : new RefType(name, ref);

            if(CONSTRUCTOR_ARG.equals(parameter.getNodeName()))
                beanDefinition.getInitParam().put(name, paramObject);
            else if(PROPERTY.equals(parameter.getNodeName()))
                beanDefinition.getSetParam().put(name, paramObject);
        }

        return null;

    }

}
