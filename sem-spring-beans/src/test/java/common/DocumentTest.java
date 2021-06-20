package common;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;

public class DocumentTest {

    public static void main(String[] args) throws FileNotFoundException {



        FileInputStream inputStream = new FileInputStream("/Users/lqyyy/Desktop/permanent/code/github/sem-spring/sem-spring-beans/src/main/resources/springspring.xml");

        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            Document document = db.parse(inputStream);

            Element documentElement = document.getDocumentElement();

            NodeList bean = documentElement.getElementsByTagName("bean");

            Node item = bean.item(0);

            NamedNodeMap attributes = item.getAttributes();

            System.out.println(attributes);

            for(int i = 0; i < attributes.getLength(); ++i) {
                Node item1 = attributes.item(i);

                System.out.println(item1.getNodeName());
                System.out.println(item1.getNodeValue());
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
