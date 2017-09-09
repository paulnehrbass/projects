package ch.rno.workshops.objects;

/**
 * Created by lapdog on 02.09.2017.
 *
 * <Supplements>
 <Supplement id="B123" count="12">Beilage Musikfestival</Supplement>
 <eSupplement id="E123" count="37">Flyer StuCard</eSupplement>
 </Supplements>
 */
public class Supplement {
    private String suplement;
    private String id;
    private Integer countSupplement;

    public Supplement(String suplement, String id, Integer count) {
        this.suplement = suplement;
        this.id = id;
        this.countSupplement = count;
    }

    public String getSuplement() {
        return suplement;
    }

    public void setSuplement(String suplement) {
        this.suplement = suplement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCountSupplement() {
        return countSupplement;
    }

    public void setCountSupplement(Integer countSupplement) {
        this.countSupplement = countSupplement;
    }
}
