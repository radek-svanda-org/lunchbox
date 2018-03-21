package lunchbox.supplier.brno

import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.XMLReader

class PanoptikumParserTest extends GroovyTestCase {

    def html
    PanoptikumParser parser = new PanoptikumParser()

    @Override
    void setUp() {
        XMLReader p = new SAXParser()
        html = new XmlSlurper(p).parse(
                PanoptikumParserTest.class.getResourceAsStream('panoptikum-menu-01.html')
        )
    }

    void testParseMonday() {
        def items = parser.dayRows(Calendar.MONDAY, html)
        assertEquals(4, items.size())
        assertEquals("polévka: Hráškový krém (1,3,7)", items[0].TD[3].text())
        assertEquals("Steak", items[2].TD[3].text().substring(0, 5))
    }

    void testParseFriday() {
        def items = parser.dayRows(Calendar.FRIDAY, html)
        assertEquals(4, items.size())
        assertEquals("polévka: Frankfurtská (1,3,7 9)", items[0].TD[3].text())
        assertEquals("Zapečené", items[2].TD[3].text().substring(0, 8))
    }

}
