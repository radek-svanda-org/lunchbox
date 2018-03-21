package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.XMLReader

class ForkysParserTest extends GroovyTestCase {

    NodeChild html
    ForkysParser parser = new ForkysParser()

    @Override
    void setUp() {
        XMLReader p = new SAXParser()
        html = new XmlSlurper(p).parse(
                ForkysParserTest.class.getResourceAsStream('forkys-menu-01.html')
        )
    }

    void testParseMonday() {
        def items = parser.dayItems(Calendar.MONDAY, html)
        assertEquals(3, items.size())
        assertEquals('Mrkvová polévka se zázvorem (BL)', items[0].name)
        assertEquals('Farmářské kousky se zeleninovou rýží (BL)', items[1].name)
    }

    void testParseFriday() {
        def items = parser.dayItems(Calendar.FRIDAY, html)
        assertEquals(3, items.size())
        assertEquals('Z červeného zelí s bramborem (BL)', items[0].name)
        assertEquals('Dýňový kotlík s fazolemi adzuki, BL chéb (BL)', items[1].name)
    }
}
