package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.springframework.stereotype.Component
import lunchbox.MenuItem
import lunchbox.supplier.HtmlMenuParser

@Component
class CajplParser implements HtmlMenuParser {

    @Override
    List<MenuItem> parseItems(NodeChild html) {

        def rowToItem = { node ->
            String name = node.TD[0].localText().join().trim()
            String price = node.TD[2].localText().join().trim()
            String prefix = node.TD[0].SPAN[0]['@class'].text() == 'express' ? '[express] ' : ''

            return new MenuItem("${prefix}${name}", price)
        }

        def partByName = { name ->
            html.BODY.DIV.DIV.DIV.TABLE.TBODY.find { node ->
                node.TR.TD.H2.text() == name
            }
        }

        def rowsFromPart = { name ->
            def part = partByName name
            part.TR
                    .findAll { node -> node.TD.size() == 3 }
                    .collect(rowToItem)
        }

        rowsFromPart('Polévky') + rowsFromPart('Hlavní jídla')
    }

}
