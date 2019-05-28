import main.Parcelsize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelsizeTests {

    @Test
    void xsPackage() {
        Parcelsize p = new Parcelsize();
        p.setHeight("0");
        p.setLength("0");
        p.setWidth("0");
        p.calculateSize();
        assertEquals("XS", p.getSize());
    }

    @Test
    void sPackage() {
        Parcelsize p = new Parcelsize();
        p.setHeight("5");
        p.setLength("6");
        p.setWidth("10");
        p.calculateSize();
        assertEquals("S", p.getSize());
    }

    @Test
    void mPackage() {
        Parcelsize p = new Parcelsize();
        p.setHeight("5");
        p.setLength("11");
        p.setWidth("15");
        p.calculateSize();
        assertEquals("M", p.getSize());
    }

    @Test
    void lPackage() {
        Parcelsize p = new Parcelsize();
        p.setHeight("25");
        p.setLength("6");
        p.setWidth("5");
        p.calculateSize();
        assertEquals("L", p.getSize());
    }

    @Test
    void xlPackage() {
        Parcelsize p = new Parcelsize();
        p.setHeight("5");
        p.setLength("51");
        p.setWidth("10");
        p.calculateSize();
        assertEquals("XL", p.getSize());
    }


}
