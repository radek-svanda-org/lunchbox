package lunchbox.supplier.brno

import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.NodeChildren
import org.springframework.stereotype.Component
import lunchbox.MenuItem
import lunchbox.supplier.HtmlMenuParser

@Component
class PanoptikumParser implements HtmlMenuParser {

    List<String> days = [
            '---',
            'neděle',
            'pondělí',
            'úterý',
            'středa',
            'čtvrtek',
            'pátek',
            'sobota',
            'neděle',
            '---'
    ]

    @Override
    List<MenuItem> parseItems(NodeChild html) {
        def dow = Calendar.instance.get(Calendar.DAY_OF_WEEK)
        def cleanValue = { it.text().trim() as String }

        dayRows(dow, html)
                .findAll { (cleanValue(it.TD[3])) }
                .collect {
            new MenuItem(
                    cleanValue(it.TD[3]),
                    cleanValue(it.TD[4])
            )
        }
    }

    def dayRows(int dow, NodeChild html) {

        def today = days.get(dow)
        def tomorrow = days.get(dow + 1)

        NodeChildren tbody = html.BODY.DIV.DIV.DIV.DIV.TABLE.TBODY

        def readSwitch = false
        def emptyLines = 0

        def tableRowPredicate = { tr ->
            String itemText = tr.TD[3].text().trim()
            emptyLines = itemText.isEmpty() ? (emptyLines + 1) : 0

            if (!readSwitch && itemText.contains(today)) {
                // this day name found => start reading
                readSwitch = true
            } else if (readSwitch && itemText.contains(tomorrow)) {
                // next day name found => stop reading
                readSwitch = false
            } else if (emptyLines >= 2) {
                // more than 2 empty lines => stop reading
                readSwitch = false
            }
            return readSwitch
        }

        return tbody.TR
                .findAll(tableRowPredicate)
                // drop first line, it contains day name
                .drop(1)
    }

}
