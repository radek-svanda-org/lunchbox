package lunchbox.supplier

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ParserRegistry
import lunchbox.Menu
import lunchbox.MenuRating
import lunchbox.MenuSupplier

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class HtmlMenuSupplier implements MenuSupplier {

    final String url
    final String name
    final HtmlMenuParser parser
    final Charset charset
    final MenuRating rating

    HtmlMenuSupplier(Map params) {
        name = params.name ?: 'Missing name'
        url = params.url ?: "http://www.google.cz?q=${name}"
        parser = params.parser
        charset = params.charset ?: StandardCharsets.UTF_8
        rating = params.rating ?: MenuRating.defaultRating()
    }

    @Override
    Menu getMenu() {
        def http = new HTTPBuilder(url)
        ParserRegistry.defaultCharset = charset.name()
        def html = http.get([:])
        return new Menu(name, url, parser.parseItems(html), rating)
    }
}
