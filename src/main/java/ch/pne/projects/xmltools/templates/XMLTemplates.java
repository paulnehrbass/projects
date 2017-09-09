package ch.pne.projects.xmltools.templates;

/**
 * Created by lapdog on 02.09.2017.
 */
public class XMLTemplates {

    public static final String XML_TEMPALTE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public static final String XML_ROOT_DOCUMENT = XML_TEMPALTE + "\n" +
            "<Document id=\"1\" shipmentid=\"1\" fileid=\"1\">\n" +
            "\t\t<Folding>0</Folding>\n" +
            "\t\t<DocumentType>Kontoauszug jährlich</DocumentType>\n" +
            "\t\t<Recipient>\n" +
            "\t\t\t<line>Stephan Bühler</line>\n" +
            "\t\t\t<line>Weinberglistrasse 4</line>\n" +
            "\t\t\t<line>6004 Luzern</line>\n" +
            "\t\t</Recipient>\n" +
            "\t\t<Supplements>\n" +
            "\t\t\t<eSupplement>E123</eSupplement>\n" +
            "\t\t</Supplements>\n" +
            "\t</Document>";
}
