package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import org.springframework.stereotype.Component
import lunchbox.MenuItem
import lunchbox.supplier.HtmlMenuParser

@Component
class VarnaParser implements HtmlMenuParser {

    @Override
    List<MenuItem> parseItems(NodeChild html) {

        def article = html.depthFirst()
                .findAll { it.name() == 'DIV' && it.@id == 'pravy-sloupec-obsah' }
                .first()
                .depthFirst()
                .findAll { it.name() == 'ARTICLE' }
                .first()

        def h2cnt = 0

        def items = []

        for (def node : article.children()) {
            if (node.name() == 'H2') {
                h2cnt++
                continue;
            }

            if (node.name() == 'H3') continue

            if (h2cnt < 1) continue

            if (h2cnt > 1) break

            node[0].children()[0].children().forEach { tr ->
                def nodeText = { it?.text()?.trim() }
                def name = {
                    nodeText(it[it.size() == 3 ? 1 : 0])
                            .replace('Polévka dne + hlavní chod + bonaqua 0,25l', '')
                            .trim()
                }
                def price = { nodeText(it[it.size() == 3 ? 2 : 1]) }
                def children = tr.children()
                items << new MenuItem(name(children), price(children) ?: "")
            }

        }
        items
    }
}
