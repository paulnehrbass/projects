package ch.rno.workshops.xmltools;

import ch.rno.workshops.objects.Order;
import ch.rno.workshops.objects.Supplement;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;

public class XMLOrderTelegram {
    final static Logger LOGGER = Logger.getLogger(XMLOrderTelegram.class);

    private Order order;
    private XMLTools xmlOrderTools;

    public XMLOrderTelegram(Order order) {
        this.order = order;
        Document document = new Document();
        Element root = new Element("Order");
        document.addContent(root);
        this.xmlOrderTools = new XMLTools(document);
    }

    public XMLOrderTelegram() {

    }

    public void createOrderTelegram(Order order){

    }

    public void createOrderTelegram(){

        xmlOrderTools.appendElement("/Order", "Filename", order.getFilename());
        xmlOrderTools.appendElement("/Order", "Shipment", order.getCountShipment().toString());
        xmlOrderTools.appendElement("/Order", "Document", order.getCountDocument().toString());
        xmlOrderTools.appendElement("/Order", "Page", order.getCountPage().toString());
        xmlOrderTools.appendElement("/Order", "Supplements", "");

        for(Supplement supplement : order.getSupplements()){
            xmlOrderTools.appendElement("/Order/Supplements", "Supplement", supplement.getSuplement());

        }


    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}