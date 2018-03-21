package lunchbox

import groovy.text.SimpleTemplateEngine
import org.springframework.stereotype.Component

@Component
class MessageFactory {

    def template = new SimpleTemplateEngine().createTemplate(
            new InputStreamReader(
                    getClass().getResourceAsStream('html.template')
            )
    )

    String createMessage(Menu menu) {
        template.make([menu: menu]).toString()
    }

}
