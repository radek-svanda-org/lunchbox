package lunchbox.supplier

import groovy.util.slurpersupport.NodeChild
import lunchbox.MenuItem

interface HtmlMenuParser {
    List<MenuItem> parseItems(NodeChild html)
}