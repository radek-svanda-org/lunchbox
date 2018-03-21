package lunchbox.consumer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import lunchbox.Menu
import lunchbox.MessageConsumer
import lunchbox.MessageFactory

@Component
class FakeConsumer implements MessageConsumer {

    @Autowired
    MessageFactory messageFactory

    @Override
    void accept(Menu menu) {
        println messageFactory.createMessage(menu)
    }
}
