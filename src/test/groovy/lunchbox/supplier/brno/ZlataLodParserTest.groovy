package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.XMLReader

class ZlataLodParserTest extends GroovyTestCase {

    NodeChild html
    ZlataLodParser parser = new ZlataLodParser()

    @Override
    void setUp() {
        XMLReader p = new SAXParser()
        html = new XmlSlurper(p).parse(
                ZlataLodParser.class.getResourceAsStream('zlata-lod-menu-01.html')
        )
    }

    void testParseFirst() {
        def items = parser.parseItems(html)
        assertEquals(8, items.size())
        assertEquals('PEČENÉ VEPŘOVÉ KARÉ', items[0].name[0..18])
    }
}
