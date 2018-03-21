package lunchbox

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
class LunchBoxApplication {

    static void main(String[] args) {
        SpringApplication.run LunchBoxApplication, args
    }

    @Value('#{"${suppliers}".split(",")}')
    List<String> suppliers

    @Value('#{"${consumers}".split(",")}')
    List<String> consumers

    @Bean
    CommandLineRunner commandLineRunner(
            ApplicationContext ctx,
            MessageFactory messageFactory) {

        { String... args ->

            def supplierBeans = ctx.getBeansOfType(MenuSupplier)
                    .findAll { suppliers.contains(it.key.replace('Supplier', '')) }
                    .collect { it.value }

            def consumerBeans = ctx.getBeansOfType(MessageConsumer)
                    .findAll { consumers.contains(it.key.replace('Consumer', '')) }
                    .collect { it.value }

            def sendMessage = { Menu menu -> consumerBeans.each { it.accept(menu) } }

            def exceptionMenu = { MenuSupplier provider, Exception ex ->
                ex.printStackTrace()
                return new Menu(
                        provider.name,
                        provider.url,
                        [new MenuItem('Provider failed', ex.getMessage())]
                )
            }

            def safeMenuMapper = { MenuSupplier supplier ->
                try {
                    return supplier.menu
                } catch (Exception e) {
                    return exceptionMenu(supplier, e)
                }
            }

            supplierBeans
                    .collect (safeMenuMapper)
                    .sort { a, b -> (0 - (a.rating.order <=> b.rating.order)) ?: a.name <=> b.name }
                    .each(sendMessage)

            consumerBeans.each { it.close() }
        }
    }
}
