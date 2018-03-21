package lunchbox.supplier.brno

import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.XMLReader

class StopkaParserTest extends GroovyTestCase {

    def html
    StopkaParser stopka = new StopkaParser()

    @Override
    void setUp() {
        XMLReader p = new SAXParser()
        html = new XmlSlurper(p).parse(
                StopkaParserTest.class.getResourceAsStream('stopka-menu.html')
        )
    }

    void testParser() {
        def items = stopka.parseItems(html)
        assertEquals(3, items.size())
        assertEquals(1, items.findAll { it.name.startsWith('Polévka') }.size())
    }
}
