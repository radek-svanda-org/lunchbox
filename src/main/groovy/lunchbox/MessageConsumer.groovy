package lunchbox

import java.util.function.Consumer

interface MessageConsumer extends Consumer<Menu>, ClosableConsumer {
}