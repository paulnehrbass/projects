package ch.pne.projects.xmltools.xmltools;

import ch.pne.projects.xmltools.enums.XMLSource;
import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.AttributeFilter;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.*;

public class XMLTools {
    final static Logger LOGGER = Logger.getLogger(XMLTools.class);

    //------------------------------------------------------------------------------------------------------------------
    // members
    //------------------------------------------------------------------------------------------------------------------
    private Document document;          // jodm2 document
    private Element root;
    private String source;                // path to xml-file from filesystem, or resource

    //------------------------------------------------------------------------------------------------------------------
    // constructors
    //------------------------------------------------------------------------------------------------------------------
    public XMLTools(XMLSource xmlSource, String source){
        this.source = source;
        switch (xmlSource){
            case XML_FILE:
                initFromFile(loadFileFromFilesystem(this.source));
                break;
            case XML_RESOURCE:
                initFromFile(loadFileFromResource(this.source));
                break;
            case XML_STRING:
                initFromString(this.source);
                break;
        }
    }

    // XMLTools(Document document): creates a new XMLTools instance from jdom2 document.
    public XMLTools(Document document) {
        this.document = document;
        this.source = document.getBaseURI();
        this.root = document.getRootElement();
        LOGGER.debug("XMLTools-Document created from Document with root-node: " + document.getRootElement().getName());
    }

    //------------------------------------------------------------------------------------------------------------------
    // publics
    //------------------------------------------------------------------------------------------------------------------
    public Element getElement(String xPath){
        final XPathExpression<Element> compile = XPathFactory.instance().compile(
                xPath, new ElementFilter());
        return compile.evaluateFirst(document);
    }

    public Attribute getAttribute(String xPath){
        final XPathExpression<Attribute> compile = XPathFactory.instance().compile(
                xPath, new AttributeFilter());
        return compile.evaluateFirst(document);
    }

    public void updateElement(String xPath, String newValue){
        final XPathExpression<Element> compile = XPathFactory.instance().compile(
                xPath, new ElementFilter());
        final Element element = compile.evaluateFirst(document);
        LOGGER.debug("updateElement(): element, newValue, oldValue: " + element.getName() + ", " + newValue + ", " + element.getValue());
        element.setText(newValue);
    }

    public void updateAttribute(String xPath, String newValue){
        final XPathExpression<Attribute> compile = XPathFactory.instance().compile(
                xPath, new AttributeFilter());
        final Attribute attribute = compile.evaluateFirst(document);
        LOGGER.debug("attribute, newValue, oldValue: " + attribute.getName() + ", " + newValue + ", " + attribute.getValue());
        attribute.setValue(newValue);
    }

    public void appendElement(String xPathParent, String name, String value){
        final XPathExpression<Element> compile = XPathFactory.instance().compile(
                xPathParent, new ElementFilter());
        final Element parent = compile.evaluateFirst(document);
        Element element = new Element(name);
        element.setText(value);
        parent.addContent(element);
        LOGGER.debug("parent, element, value: " + parent.getName() + ", " + element.getName() + ", " +element.getValue() );
    }

    public void appendAttribute(String xPathParent, String name, String value){
        final XPathExpression<Element> compile = XPathFactory.instance().compile(
                xPathParent, new ElementFilter());
        final Element parent = compile.evaluateFirst(document);
        parent.setAttribute(name, value);
    }

    public void deleteElement(String xPath){
        final XPathExpression<Element> compile = XPathFactory.instance().compile(
                xPath, new ElementFilter());
        final Element element = compile.evaluateFirst(document);
        element.getParent().removeContent(element);

    }

    public void deleteAttribute(String xPath){
        final XPathExpression<Attribute> compile = XPathFactory.instance().compile(
                xPath, new AttributeFilter());
        final Attribute attribute = compile.evaluateFirst(document);
        attribute.getParent().removeAttribute(attribute);
    }

    public String display(Element element){
        Writer writer = new StringWriter();
        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());
        try {
            out.output(element, writer);
        } catch (IOException e) {
            LOGGER.error("Fehler in getElementAsXML: ", e);
        }
        return writer.toString();
    }

    public String display(String xPath){
        final XPathExpression<Element> compile = XPathFactory.instance().compile(
                xPath, new ElementFilter());
        return display(compile.evaluateFirst(document));
    }

    //------------------------------------------------------------------------------------------------------------------
    // privates
    //------------------------------------------------------------------------------------------------------------------
    private File loadFileFromResource(String relativePath){
        ClassLoader classLoader = getClass().getClassLoader();
        File file =  new File(classLoader.getResource(relativePath).getFile());
        LOGGER.debug("File loaded from resource: " + file.getName());
        return file;
    }

    private File loadFileFromFilesystem(String absolutePath){
        File file =  new File(absolutePath);
        LOGGER.debug("File loaded from filesystem: " + file.getName());
        return file;
    }

    private void initFromString(String xmlString){
        InputStream xmlStream;
        SAXBuilder jdomBuilder = new SAXBuilder();
        try {
            xmlStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
            this.document = jdomBuilder.build(xmlStream);
            this.root = document.getRootElement();
            LOGGER.debug("Document created from " + xmlString + " with root-node: " + getRoot().getName());
        } catch (JDOMException e) {
            LOGGER.error("JDOMException initFromString(): ", e);
        } catch (IOException e) {
            LOGGER.error("IOException initFromString(): ", e);
        }
    }

    private void initFromFile(File xmlFile){
        SAXBuilder jdomBuilder = new SAXBuilder();
        try {
            this.document = jdomBuilder.build(xmlFile);
            this.root = document.getRootElement();
            LOGGER.debug("Document created from " + xmlFile.getAbsoluteFile() + " with root-node: " + getRoot().getName());
        } catch (JDOMException e) {
            LOGGER.error("JDOMException initFromFile(): ", e);
        } catch (IOException e) {
            LOGGER.error("IOException initFromFile(): ", e);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    // getter / setter
    //------------------------------------------------------------------------------------------------------------------
    public Element getRoot() {
        return root;
    }
}