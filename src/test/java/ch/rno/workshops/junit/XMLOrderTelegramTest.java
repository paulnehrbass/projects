package ch.rno.workshops.junit;

import ch.rno.workshops.objects.Order;
import ch.rno.workshops.objects.Supplement;
import ch.rno.workshops.xmltools.XMLTools;
import junit.framework.TestCase;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapdog on 02.09.2017.
 */
public class XMLOrderTelegramTest extends TestCase {

    private XMLTools xmlTools;
    private Order order;

    @Override
    protected void setUp() throws Exception {
//        super.setUp();
//        Document orderDocument = new Document();
//        Element orderRoot = new Element("Order");
//        orderDocument.addContent(orderRoot);
//        xmlTools = new XMLTools(orderDocument);
//
//        Supplement supplement1 = new Supplement("RNO_SUPPLEMENT_1 (DE)", "rno-1", 10);
//        Supplement supplement2 = new Supplement("RNO_SUPPLEMENT_2 (IT)", "rno-2", 11);
//
//        List<Supplement> supplementsList = new ArrayList<Supplement>();
//        supplementsList.add(supplement1);
//        supplementsList.add(supplement2);
//
//        order = new Order("filename.afp", 10, 10, 10,
//                supplementsList, xmlTools);
//
//        order.createOrderTelegram();
    }
}
