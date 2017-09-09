package ch.rno.workshops.xmltools;

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


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class XMLNopTools {
    final static Logger logger = Logger.getLogger(XMLNopTools.class);

    public String xmlNopsSource = "testdata/document-root-vorgabe.xml";
    public Document jdomDocument;
    public Element root;
    public Element xmlNopFile;
    public Element xmlNopDocument;
    public Element xmlNopShipment;
    public Element xmlNopPage;

    public String getElementAsXML(Element element){
        Writer writer = new StringWriter();

        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());

        try {
            out.output(element, writer);
        } catch (IOException e) {
            logger.error("Fehler in getElementAsXML: ", e);
        }

        return writer.toString();
    }

    public void getActTimestamp(){
        Date actDate = new Date(System.currentTimeMillis());
        //2016-08-15T16:00:12+02:00
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate = formatter.format(actDate);
        logger.info("formattedDate: " + formattedDate);
    }

    //Beispiel 1:   - /XmlNops/File/Origin  -> selektiert das Element 'Origin'
    //Beispiel 2:   - /XmlNops/File/@id    -> selektiert das Attribute File/@id
    public void updateXmlNop(String xPath, String newValue){
        if(xPath.contains("@")){
            //attribute
            final XPathExpression<Attribute> compile = XPathFactory.instance().compile(
                    xPath, new AttributeFilter());
            final Attribute attribute = compile.evaluateFirst(jdomDocument);
            logger.debug("attribute, newValue, oldValue: " + attribute.getName() + ", " + newValue + ", " + attribute.getValue());
            attribute.setValue(newValue);
        }else{
            //element
            final XPathExpression<Element> compile = XPathFactory.instance().compile(
                    xPath, new ElementFilter());
            final Element element = compile.evaluateFirst(jdomDocument);
            logger.debug("element, newValue, oldValue: " + element.getName() + ", " + newValue + ", " + element.getValue());
            element.setText(newValue);
        }
    }



    public XMLNopTools() {
        init();
    }

    public XMLNopTools(String xmlNopsSource) {
        this.xmlNopsSource = xmlNopsSource;
        init();
    }

    public void init(){
        File xmlNops = loadFileFromResource(xmlNopsSource);
        SAXBuilder jdomBuilder = new SAXBuilder();
        jdomDocument = null;
        try {
            jdomDocument = jdomBuilder.build(xmlNops);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root = jdomDocument.getRootElement();
        logger.debug("Root-Node: " + root.getName());
        //File
        xmlNopFile = root.getChild("File");
        logger.debug("File-Node: " + xmlNopFile.getName());
        //Document
        xmlNopDocument = root.getChild("Document");
        logger.debug("Document-Node: " + xmlNopDocument.getName());
        //Shippment
        xmlNopShipment = root.getChild("Shipment");
        logger.debug("Shipment-Node: " + xmlNopShipment.getName());
        //Page
        xmlNopPage = root.getChild("Page");
        logger.debug("Page-Node: " + xmlNopPage.getName());
    }

    private File loadFileFromResource(String relativePath){
        ClassLoader classLoader = getClass().getClassLoader();
        File file =  new File(classLoader.getResource(relativePath).getFile());
        logger.info("File loaded: " + file.getName());
        return file;

    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
    }

    public Element getXmlNopFile() {
        return xmlNopFile;
    }

    public void setXmlNopFile(Element xmlNopFile) {
        this.xmlNopFile = xmlNopFile;
    }

    public Element getXmlNopDocument() {
        return xmlNopDocument;
    }

    public void setXmlNopDocument(Element xmlNopDocument) {
        this.xmlNopDocument = xmlNopDocument;
    }

    public Element getXmlNopShipment() {
        return xmlNopShipment;
    }

    public void setXmlNopShipment(Element xmlNopShipment) {
        this.xmlNopShipment = xmlNopShipment;
    }

    public Element getXmlNopPage() {
        return xmlNopPage;
    }

    public void setXmlNopPage(Element xmlNopPage) {
        this.xmlNopPage = xmlNopPage;
    }

    public Document getJdomDocument() {
        return jdomDocument;
    }

    public void setJdomDocument(Document jdomDocument) {
        this.jdomDocument = jdomDocument;
    }
}