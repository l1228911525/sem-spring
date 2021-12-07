package org.semspringframework.aop.config;

/**
 * a class implement the interface{@link AopConfigParse} surface it hold ability of parsing xml file and obtaining aop bean definition.
 */
public interface AopConfigParse {

    /**
     * parse a configuration file
     * @param location
     */
    public void parseXmlAopFile(String location);

    /**
     * parse a few configuration files
     * @param locations
     */
    public void parseXmlAopFile(String...locations);

}
