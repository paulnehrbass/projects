package ch.pne.projects.xmltools.objects;

import java.util.List;

/**
 * Created by lapdog on 02.09.2017.
 * <Order>
 <Filename>Swisscom_Production_KundeXY_1_20160815-160012_A-Post.afp</Filename>
 <Shipment>2156</Shipment>
 <Document>3453</Document>
 <Page>4354</Page>
 <Supplements>
 <Supplement id="B123" count="12">Beilage Musikfestival</Supplement>
 <eSupplement id="E123" count="37">Flyer StuCard</eSupplement>
 </Supplements>
 </Order>
 */
public class Order {
    private String filename;
    private Integer countShipment;
    private Integer countDocument;
    private Integer countPage;
    private List<Supplement> supplements;

    public Order(String filename, Integer countShipment, Integer countDocument, Integer countPage,
                 List<Supplement> supplements) {
        this.filename = filename;
        this.countShipment = countShipment;
        this.countDocument = countDocument;
        this.countPage = countPage;
        this.supplements = supplements;
    }

    public Order() {}

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getCountShipment() {
        return countShipment;
    }

    public void setCountShipment(Integer countShipment) {
        this.countShipment = countShipment;
    }

    public Integer getCountDocument() {
        return countDocument;
    }

    public void setCountDocument(Integer countDocument) {
        this.countDocument = countDocument;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }

    public List<Supplement> getSupplements() {
        return supplements;
    }

    public void setSupplements(List<Supplement> supplements) {
        this.supplements = supplements;
    }
}
