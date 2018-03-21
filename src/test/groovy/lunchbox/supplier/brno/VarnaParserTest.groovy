package lunchbox.supplier.brno

import org.cyberneko.html.parsers.SAXParser
import org.xml.sax.XMLReader

/**
 * Created by svandar on 21.11.2017.
 */
class VarnaParserTest extends GroovyTestCase {

    def html
    VarnaParser varna = new VarnaParser()

    @Override
    void setUp() {
        XMLReader p = new SAXParser()
        html = new XmlSlurper(p).parse(
                VarnaParserTest.class.getResourceAsStream('varna-menu-01.html')
        )
    }

    void testParser() {
        def items = varna.parseItems(html)
        assertEquals(7, items.size())
    }


}
