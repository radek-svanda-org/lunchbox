package lunchbox.supplier.brno

import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.XMLReader

class CajplParserTest extends GroovyTestCase {

    def html
    CajplParser cajpl = new CajplParser()

    @Override
    void setUp() {
        XMLReader p = new SAXParser()
        html = new XmlSlurper(p).parse(
                CajplParserTest.class.getResourceAsStream('cajpl-menu-01.html')
        )
    }

    void testParser() {
        def items = cajpl.parseItems(html)
        assertEquals(7, items.size())
        assertEquals(3, items.findAll { it.name.startsWith('[express]') }.size())
    }
}
