package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.springframework.stereotype.Component
import lunchbox.MenuItem
import lunchbox.supplier.HtmlMenuParser

@Component
class StopkaParser implements HtmlMenuParser {

    @Override
    List<MenuItem> parseItems(NodeChild html) {

        def rowToItem = { node ->

            String title = node.TD[0].localText().join().trim()
            String name = node.TD[1].localText().join().trim()
            String price = node.TD[2].localText().join().trim()

            return new MenuItem("${title} - ${name}", price)
        }

        def partByName = { name ->
            html.BODY.DIV.DIV.DIV.ARTICLE.SECTION.find { section ->
                section.H2.text().contains(name)
            }
        }

        def rowsFromPart = { name ->
            def part = partByName name
            part.TABLE.TBODY.TR
                    .findAll { node -> node.TD.size() == 3 }
                    .collect(rowToItem)
        }

        rowsFromPart('STOPKOVA')
    }

}
