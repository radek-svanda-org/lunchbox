package lunchbox.consumer

import io.evanwong.oss.hipchat.v2.HipChatClient
import io.evanwong.oss.hipchat.v2.rooms.MessageColor
import io.evanwong.oss.hipchat.v2.rooms.MessageFormat
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import lunchbox.Menu
import lunchbox.MenuRating
import lunchbox.MessageConsumer
import lunchbox.MessageFactory

import java.util.concurrent.TimeUnit

@Component
class HipchatConsumer implements MessageConsumer, InitializingBean {

    @Value('${hipchat.room.token}')
    String hipchatToken

    @Value('${hipchat.room}')
    String hipchatRoom

    @Autowired
    MessageFactory messageFactory

    private HipChatClient client

    @Override
    void accept(Menu menu) {
        String message = messageFactory.createMessage(menu)
        client
                .prepareSendRoomNotificationRequestBuilder(hipchatRoom, message)
                .setMessageFormat(MessageFormat.HTML)
                .setColor(ratingColor(menu.rating))
                .build()
                .execute()
                .get(10, TimeUnit.SECONDS)
    }

    static MessageColor ratingColor(MenuRating menuRating) {
        switch (menuRating) {
            case MenuRating.AWFUL: return MessageColor.GRAY
            case MenuRating.GOOD: return MessageColor.GREEN
            default: return MessageColor.YELLOW
        }
    }

    @Override
    void close() {
        client.close()
    }

    @Override
    void afterPropertiesSet() throws Exception {
        client = new HipChatClient(hipchatToken)
    }
}
