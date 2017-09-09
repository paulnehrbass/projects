package ch.pne.projects.xmltools.junit;

import ch.pne.projects.xmltools.enums.XMLSource;
import ch.pne.projects.xmltools.exceptions.XMLToolException;
import ch.pne.projects.xmltools.templates.XMLTemplates;
import ch.pne.projects.xmltools.xmltools.XMLTools;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by lapdog on 02.09.2017.
 */
public class XMLToolsTest extends TestCase{
    //http://www.mkyong.com/unittest/junit-4-tutorial-1-basic-usage/
    final static Logger LOGGER = Logger.getLogger(XMLToolsTest.class);
    final static String pathToFile = "D:\\dev\\projects\\src\\main\\resources\\testdata\\data.xml";
    final static String pathToResource = "testdata/document-root-vorgabe.xml";

    private XMLTools xmlToolsFromResource;
    private XMLTools xmlToolsFromFile;
    private XMLTools xmlToolsFromString;
    private XMLTools xmlToolsFromDocument;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        xmlToolsFromResource= new XMLTools(XMLSource.XML_RESOURCE, pathToResource);
        xmlToolsFromFile    = new XMLTools(XMLSource.XML_FILE, pathToFile);
        xmlToolsFromString  = new XMLTools(XMLSource.XML_STRING, XMLTemplates.XML_ROOT_DOCUMENT);

        Document doc = new Document();
        doc.setContent(new Element("root"));
        xmlToolsFromDocument= new XMLTools(doc);
    }

    @Test
    public void testXmlToolsFromResource(){
        assertThat(xmlToolsFromResource.getRoot().getName(), Matchers.equalTo("XmlNops"));
        LOGGER.info("@testXmlToolsFromResource: root.name equals '"
                + xmlToolsFromResource.getRoot().getName()
                +"' -> " + xmlToolsFromResource.getRoot().getName().equals("XmlNops"));

        assertThat(xmlToolsFromResource.getElement("/XmlNops/File").getName(), Matchers.equalTo("File"));
        LOGGER.info("@testXmlToolsFromResource: file.name equals '"
                + xmlToolsFromResource.getElement("/XmlNops/File").getName()
                +"' -> " + xmlToolsFromResource.getElement("/XmlNops/File").getName().equals("File"));

        assertThat(xmlToolsFromResource.getElement("/XmlNops/Shipment").getName(), Matchers.equalTo("Shipment"));
        LOGGER.info("@testXmlToolsFromResource: shipment.name equals '"
                + xmlToolsFromResource.getElement("/XmlNops/Shipment").getName()
                +"' -> " + xmlToolsFromResource.getElement("/XmlNops/Shipment").getName().equals("Shipment"));

        assertThat(xmlToolsFromResource.getElement("/XmlNops/Document").getName(), Matchers.equalTo("Document"));
        LOGGER.info("@testXmlToolsFromResource: Document.name equals '"
                + xmlToolsFromResource.getElement("/XmlNops/Document").getName()
                +"' -> " + xmlToolsFromResource.getElement("/XmlNops/Document").getName().equals("Document"));

        assertThat(xmlToolsFromResource.getElement("/XmlNops/Page").getName(), Matchers.equalTo("Page"));
        LOGGER.info("@testXmlToolsFromResource: Page.name equals '"
                + xmlToolsFromResource.getElement("/XmlNops/Page").getName()
                +"' -> " + xmlToolsFromResource.getElement("/XmlNops/Page").getName().equals("Page"));
    }

    @Test
    public void testXmlToolsFromFile(){
        assertThat(xmlToolsFromFile.getRoot().getName(), Matchers.equalTo("company"));
        LOGGER.info("@testXmlToolsFromFile: root.name equals 'company' -> "
                + xmlToolsFromFile.getRoot().getName().equals("company"));

    }


    @Test
    public void testXmlToolsFromString(){
        assertThat(xmlToolsFromString.getRoot().getName(), Matchers.equalTo("Document"));
        LOGGER.info("@testXmlToolsFromString: root.name equals 'Document' -> "
                + xmlToolsFromString.getRoot().getName().equals("Document"));

    }

    @Test
    public void testXmlToolsFromDocument(){
        assertThat(xmlToolsFromDocument.getRoot().getName(), Matchers.equalTo("root"));
        LOGGER.info("@testxmlToolsFromDocument: root.name equals 'root' -> "
                + xmlToolsFromDocument.getRoot().getName().equals("root"));

    }

    @Test
    public void testXmlToolsFromDocumentNullException(){
        xmlToolsFromDocument = null;
        try {
            xmlToolsFromDocument = new XMLTools(null);
        } catch (XMLToolException e) {
            assertThat(xmlToolsFromDocument, Matchers.nullValue());
            LOGGER.info("@testXmlToolsFromDocumentException: xmlToolsFromDocument nullValue() 'true' -> "
                + Matchers.nullValue().toString());
            LOGGER.error(e.getMessage());
        }finally {
            LOGGER.info("@testXmlToolsFromDocumentException: finally");
        }
    }

    @Test
    public void testXmlToolsFromDocumentRootNullException(){
        Document doc = new Document();
        //doc.setContent(new Element("root"));
        try {
            xmlToolsFromDocument = new XMLTools(doc);
            assertThat(doc.getRootElement(), Matchers.nullValue());
        } catch (XMLToolException e) {

            LOGGER.info("@testXmlToolsFromDocumentException: xmlToolsFromDocument.getRoot nullValue() 'true' -> "
                    + Matchers.nullValue().toString());
            LOGGER.error(e.getMessage());
        }finally {
            LOGGER.info("@testXmlToolsFromDocumentException: finally");
        }
    }

    @Test
    public void testGetElement(){
        Element folding = xmlToolsFromResource.getElement("/XmlNops/Document/Folding");
        assertThat(folding.getName(), Matchers.equalTo("Folding"));
        LOGGER.info("@testGetElement(folding): equalTo('Folding'): " + folding.getName().equals("Folding")
                + " -> " + xmlToolsFromResource.display(folding));
    }

    @Test
    public void testGetAttribute(){
        Attribute id = xmlToolsFromResource.getAttribute("/XmlNops/Document/@id");
        assertThat(id.getName(), Matchers.equalTo("id"));
        LOGGER.info("@testGetAttribute(/Document/@id): Document/@id.name equalTo('id'): " + id.getName().equals("id")
                + " -> " + xmlToolsFromResource.display(id.getParent()));
    }

    @Test
    public void testUpdateElement() {
        String xPath = "/XmlNops/Document/Folding";
        xmlToolsFromResource.updateElement(xPath, "rno");
        Element origin = xmlToolsFromResource.getElement(xPath);
        assertThat(origin.getValue(), Matchers.equalTo("rno"));
        LOGGER.info("@testUpdateElement(origin): equalsTo('rno'): " + origin.getValue().equals("rno")
                + " -> " + xmlToolsFromResource.display(origin));
    }

    @Test
    public void testUpdateAttribute(){
        String xPath="/XmlNops/Document/@id";
        xmlToolsFromResource.updateAttribute(xPath, "999");
        Attribute id = xmlToolsFromResource.getAttribute(xPath);
        assertThat(id.getValue(), Matchers.equalTo("999"));
        LOGGER.info("@testUpdateAttribute(@id): id.value equalsTo('999'): " + id.getValue().equals("999")
                + " -> " + xmlToolsFromResource.display(id.getParent()));
    }

    @Test
    public void testAppendElement(){
        String xPath="/XmlNops";
        xmlToolsFromResource.appendElement(xPath,"rno", "value");
        Element element = xmlToolsFromResource.getElement("/XmlNops/rno");
        assertThat(element.getName(), Matchers.equalTo("rno"));
        LOGGER.info("@testAppendElement(/XmlNops/rno): rno.name equalsTo('rno'): " + element.getName().equals("rno")
                + " -> " + xmlToolsFromResource.display(xmlToolsFromResource.getRoot()));
    }

    @Test
    public void testAppendAttribute(){
        String xPathParent = "/XmlNops";
        xmlToolsFromResource.appendAttribute(xPathParent, "rno", "value");
        Attribute attribute = xmlToolsFromResource.getAttribute(xPathParent + "/@rno");
        assertThat(attribute.getName(), Matchers.equalTo("rno"));
        LOGGER.info("@testAppendAttribute(/XmlNops/@rno): rno.name equalsTo('rno'): " + attribute.getName().equals("rno")
                + " \n-> " + xmlToolsFromResource.display(xmlToolsFromResource.getRoot()));
    }

    @Test
    public void testDeleteElement(){
        String xPath = "/XmlNops/File";
        xmlToolsFromResource.deleteElement(xPath);
        Element element = xmlToolsFromResource.getElement(xPath);
        assertNull(element);
        LOGGER.info("@testDeleteElement(/XmlNops/File): XmlNops/File.name equalsTo('File'): " + null
                + " \n-> " + xmlToolsFromResource.display(xmlToolsFromResource.getRoot()));
    }

    @Test
    public void testDeleteAttribute(){
        String xPath = "/XmlNops/File/@id";
        xmlToolsFromResource.deleteAttribute(xPath);
        Attribute attribute = xmlToolsFromResource.getAttribute(xPath);
        assertNull(attribute);
        LOGGER.info("@testDeleteAttribute(/XmlNops/File/@id): XmlNops/File/@id.name equalsTo('id'): " + null
                + " \n-> " + xmlToolsFromResource.display(xmlToolsFromResource.getRoot()));
    }

}
