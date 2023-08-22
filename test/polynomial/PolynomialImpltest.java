package polynomial;

import static org.junit.jupiter.api.Assertions.*;

public class PolynomialImpltest {

    Polynomial p1;
    Polynomial p2;
    Polynomial p3;
    Polynomial p4;
    Polynomial p5;
    Polynomial p6;
    Polynomial p7;
    Polynomial p8;
    Polynomial p9;
    Polynomial p10;
    Polynomial p11;
    Polynomial p12;
    Polynomial p13;
    Polynomial p14;
    Polynomial p15;
    Polynomial p16;
    Polynomial p17;
    Polynomial p18;
    Polynomial p19;
    Polynomial p20;
    Polynomial p21;
    Polynomial p22;
    Polynomial p23;
    Polynomial p24;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p1 = new PolynomialImpl();
        p2 = new PolynomialImpl();
        p3 = new PolynomialImpl("2x^3 +2x^1 +5");
        p4 = new PolynomialImpl("0");
        p5 = new PolynomialImpl("-8x^3 +2x^6 +17x^12");
        p6 = new PolynomialImpl("-17x^3 +21x^6 +17x^8");
        p7 = new PolynomialImpl("17x^1 +17");
        p8 = new PolynomialImpl("0x^11 +0x^8 +0x^0");
        p9 = new PolynomialImpl("0x^11 +0x^8 +0x^0");
        p10 = new PolynomialImpl("4x^3 +3x^1 -5");
        p11 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
        p12 = new PolynomialImpl();
        p13 = new PolynomialImpl("2x^5 -3x^3 +17x^1");
        p16 = new PolynomialImpl();
        p17 = new PolynomialImpl();
        p18 = new PolynomialImpl("2x^5 -2x^3 -1x^3 +12x^1 +5x^1");
        p19 = new PolynomialImpl("2");
        p20 = new PolynomialImpl("");
        p21 = new PolynomialImpl("3x^2 +6x^2 +4x^1 +2x^1 +8");
        p22 = new PolynomialImpl("5x^3 +1x^3 +2x^5 +3x^5");
        p23 = new PolynomialImpl("7x^3 +2x^5 +1x^1");
        p24 = new PolynomialImpl("6x^2 +8x^4 +10x^8");



    }

    @org.junit.jupiter.api.Test
    void testPolynomialImplConstructor() {

        assertEquals("0", p1.toString());
    }

    @org.junit.jupiter.api.Test
    void testPolynomialImplStringConstructor() {

        assertEquals("2x^3 +2x^1 +5", p3.toString());
        assertEquals("2x^5 -3x^3 +17x^1", p18.toString());
        assertEquals("2", p19.toString());
        assertEquals("0", p20.toString());
        assertThrows(IllegalArgumentException.class, () -> {new PolynomialImpl("3x^2+4x^1 -2");});
        assertThrows(IllegalArgumentException.class, () -> {new PolynomialImpl("-3x^3 +-2x^5 -3 +12x^1");});


    }


    @org.junit.jupiter.api.Test
    void testAdd() {

        p1.addTerm(2, 3);
        p1.addTerm(3, 2);
        p1.addTerm(3, 0);

        p2.addTerm(4, 2);
        p2.addTerm(3, 2);
        p2.addTerm(2, 1);
        p2.addTerm(2, 0);


        assertEquals("2x^3 +10x^2 +2x^1 +5", p1.add(p2).toString());
        assertEquals("2x^3 +3x^2 +3", p1.add(p4).toString());
        assertEquals("17x^12 +17x^8 +23x^6 -25x^3", p5.add(p6).toString());
        assertEquals("0", p8.add(p9).toString());
        assertEquals("-2x^5 -3x^4 +4x^3 +14x^1 -10", p10.add(p11).toString());


        assertEquals("5x^5 +6x^3 +9x^2 +6x^1 +8", p21.add(p22).toString());
        assertEquals("10x^8 +2x^5 +8x^4 +7x^3 +6x^2 +1x^1", p23.add(p24).toString());

    }

    @org.junit.jupiter.api.Test
    void testAddTerm() {

        assertThrows(IllegalArgumentException.class, () -> {p1.addTerm(2,-17);});

        p1.addTerm(5, 2);
        assertEquals("5x^2", p1.toString());
        p1.addTerm(4, 1);
        assertEquals("5x^2 +4x^1", p1.toString());
        p1.addTerm(-2, 0);
        assertEquals("5x^2 +4x^1 -2", p1.toString());

        p2.addTerm(-50, 3);
        assertEquals("-50x^3", p2.toString());
        p2.addTerm(1, 2);
        assertEquals("-50x^3 +1x^2", p2.toString());
        p2.addTerm(3, 0);
        assertEquals("-50x^3 +1x^2 +3", p2.toString());

        p12.addTerm(4, 1);
        assertEquals("4x^1", p12.toString());
        p12.addTerm(2, 5);
        assertEquals("2x^5 +4x^1", p12.toString());
        p12.addTerm(-3, 2);
        assertEquals("2x^5 -3x^2 +4x^1", p12.toString());
        p12.addTerm(-10, 0);
        assertEquals("2x^5 -3x^2 +4x^1 -10", p12.toString());

        p16.addTerm(3, 6);
        p16.addTerm(3, 6);
        p16.addTerm(3, 6);
        p16.addTerm(3, 6);
        p16.addTerm(3, 6);
        assertEquals("15x^6", p16.toString());

        p17.addTerm(2, 5);
        p17.addTerm(-3, 3);
        p17.addTerm(12, 1);
        p17.addTerm(5, 1);
        assertEquals("2x^5 -3x^3 +17x^1", p17.toString());


    }

    @org.junit.jupiter.api.Test
    void testIsSame() {

        assertTrue(p8.isSame(p9));
        assertFalse(p8.isSame(p10));

    }


    @org.junit.jupiter.api.Test
    void testEvaluate() {

        assertEquals(-55.0, p3.evaluate(-3.0));
        assertEquals(-15.0, p3.evaluate(-2.0));

        assertEquals(1.0, p11.evaluate(1.0));
        assertEquals(-95.0, p11.evaluate(2.0));

        assertEquals(11.0, p5.evaluate(1.0));
        assertEquals(69696.0, p5.evaluate(2.0));

        assertEquals(65.0, p3.evaluate(3.0));
        assertEquals(141.0, p3.evaluate(4.0));

    }

    @org.junit.jupiter.api.Test
    void testGetCoefficient() {

        assertEquals(17, p7.getCoefficient(1));
        assertEquals(0, p8.getCoefficient(11));
        assertEquals(0, p3.getCoefficient(5));

    }

    @org.junit.jupiter.api.Test
    void testGetDegree() {

        assertEquals(p6.getDegree(), 8);
        assertEquals(p11.getDegree(), 5);
        assertEquals(p3.getDegree(), 3);
        assertEquals(p5.getDegree(), 12);

        assertEquals(0, p19.getDegree());

    }

    @org.junit.jupiter.api.Test
    void testToString(){

        assertEquals("17x^8 +21x^6 -17x^3", p6.toString());
        assertEquals("17x^12 +2x^6 -8x^3", p5.toString());
        assertEquals("2x^5 -3x^3 +17x^1", p13.toString());

    }
}





