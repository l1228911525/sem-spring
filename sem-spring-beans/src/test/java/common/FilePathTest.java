package common;

import org.semspringframework.beans.factory.Demo;
import org.semspringframework.beans.factory.support.RefType;
import org.springframework.core.io.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FilePathTest {
    public static void main(String[] args) throws IOException {
        FileUrlResource resource = new FileUrlResource("C://spring.xml");

        System.out.println(resource);

        InputStream inputStream = resource.getInputStream();

        System.out.println(inputStream);

        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource1 = resourceLoader.getResource("classpath:springspring.xml");

        System.out.println(resource1);

        InputStream inputStream1 = resource1.getInputStream();
    }

}
