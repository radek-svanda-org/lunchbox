package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.springframework.stereotype.Component
import lunchbox.MenuItem
import lunchbox.supplier.HtmlMenuParser

@Component
class ForkysParser implements HtmlMenuParser {

    // monday is on index 0, but Calendar constants are not
    static final int DOW_OFFSET = Calendar.MONDAY - 0

    @Override
    List<MenuItem> parseItems(NodeChild html) {
        def dow = Calendar.instance.get(Calendar.DAY_OF_WEEK)
        dayItems(dow, html)
    }

    def menuItemMapper = {
        new MenuItem(
                it.H2.text() as String,
                it.SPAN[1].text() as String
        )
    }

    List<MenuItem> dayItems(int dow, NodeChild html) {
        def index = dow - DOW_OFFSET
        html.BODY.SECTION.DIV.SECTION.ARTICLE[index]
                .DIV.DIV.UL.LI
                .collect(menuItemMapper)
    }
}
