package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.springframework.stereotype.Component
import lunchbox.MenuItem
import lunchbox.supplier.HtmlMenuParser

@Component
class ZlataLodParser implements HtmlMenuParser {

    def liItemMapper = {
        new MenuItem(
                it.localText().join() as String,
                it.STRONG.text() as String
        )
    }

    @Override
    List<MenuItem> parseItems(NodeChild html) {
        html.BODY.DIV[0].SECTION.MENU[0].OL.LI
                .collect(liItemMapper)
    }

}
