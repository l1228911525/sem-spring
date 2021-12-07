package org.semspringframework.aop.config;

import org.semspringframework.aop.framework.*;
import org.semspringframework.beans.factory.config.SingletonRegistry;
import org.semspringframework.beans.factory.parsing.DocumentParsing;
import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * parse xml file to obtain aop bean and register it to BeanFactory
 */
// todo parsing xml node appear null pointer exception
public class XmlAopConfigParse implements AopConfigParse {

    String AOPCONFIG = "aopconfig";

    String AOPPOINTCUT = "aoppointcut";

    String AOPASPECT = "aopaspect";

    String AOPBEFORE = "aopbefore";

    String AOPAFTER = "aopafter";

    private SingletonRegistry singletonRegistry;

    public XmlAopConfigParse() {
    }

    public XmlAopConfigParse(SingletonRegistry singletonRegistry) {
        this.singletonRegistry = singletonRegistry;
    }

    public SingletonRegistry getSingletonRegistry() {
        return singletonRegistry;
    }

    public void setSingletonRegistry(SingletonRegistry singletonRegistry) {
        this.singletonRegistry = singletonRegistry;
    }

    @Override
    public void parseXmlAopFile(String location) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource(location);

        try {

            InputStream inputStream = resource.getInputStream();

            doParseXmlAopFile(inputStream);



        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

        @Override
    public void parseXmlAopFile(String... locations) {

        for (String location : locations) {
            parseXmlAopFile(location);
        }

    }

    public void doParseXmlAopFile(InputStream inputStream) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.parse(inputStream);

            Element rootElement = document.getDocumentElement();

            List<Node> nodeList = DocumentParsing.getNodeListByElement(rootElement, new String[]{AOPCONFIG});

            for (Node node : nodeList) {

                List<Node> appointcutList = DocumentParsing.getNodeListByNode(node, new String[]{AOPPOINTCUT});

                // parse appointcut
                for (Node appointcut : appointcutList) {

                    NamedNodeMap attributes = appointcut.getAttributes();

                    String id = attributes.getNamedItem("id").getNodeValue();

                    String expression = attributes.getNamedItem("expression").getNodeValue();

                    Pointcut pointcut = new Pointcut(id, expression);

                    this.singletonRegistry.registerSingleton(id,pointcut);
                }

                // parse AOPASPECT
                List<Node> aopaspectList = DocumentParsing.getNodeListByNode(node, new String[]{AOPASPECT});

                for (Node aopaspect : aopaspectList) {

                    Aspect aspect = new Aspect();

                    NamedNodeMap attributes = aopaspect.getAttributes();

                    String id = attributes.getNamedItem("id").getNodeValue();

                    String ref = attributes.getNamedItem("ref").getNodeValue();

                    aspect.setId(id);

                    aspect.setRef(ref);

                    List<Node> aopBeforeList = DocumentParsing.getNodeListByNode(aopaspect, new String[]{"aopbefore"});

                    for (Node aopBefore : aopBeforeList) {
                        NamedNodeMap aopBeforeAttributes = aopBefore.getAttributes();

                        String method = aopBeforeAttributes.getNamedItem("method").getNodeValue();

                        String prointcutRef = aopBeforeAttributes.getNamedItem("pointcut-ref").getNodeValue();

                        String pointcut = aopBeforeAttributes.getNamedItem("pointcut").getNodeValue();

                        BeforeAdvice beforeAdvice = new BeforeAdvice();

                        beforeAdvice.setMethod(method);
                        beforeAdvice.setPointcut(pointcut);
                        beforeAdvice.setPointcutRef(prointcutRef);

                        aspect.addBeforeAdvice(beforeAdvice);
                    }

                    // after
                    List<Node> aopAfterList = DocumentParsing.getNodeListByNode(aopaspect, new String[]{"aopafter"});

                    for (Node aopAfter : aopAfterList) {
                        NamedNodeMap aopAfterAttributes = aopAfter.getAttributes();

                        String method = aopAfterAttributes.getNamedItem("method").getNodeValue();

                        String prointcutRef = aopAfterAttributes.getNamedItem("pointcut-ref").getNodeValue();

                        String pointcut = aopAfterAttributes.getNamedItem("pointcut").getNodeValue();

                        AfterAdvice afterAdvice = new AfterAdvice();

                        afterAdvice.setMethod(method);
                        afterAdvice.setPointcut(pointcut);
                        afterAdvice.setPointcutRef(prointcutRef);

                        aspect.addAfterAdvice(afterAdvice);
                    }

                    singletonRegistry.registerSingleton(aspect.getId(), aspect);

                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SAXException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlAopConfigParse aopConfigParse = new XmlAopConfigParse(beanFactory);

        aopConfigParse.parseXmlAopFile("aop.xml");

    }

}
