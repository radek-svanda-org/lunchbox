package lunchbox.supplier.brno

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import lunchbox.MenuRating
import lunchbox.MenuSupplier
import lunchbox.supplier.EmptyMenuSupplier
import lunchbox.supplier.HtmlMenuSupplier
import lunchbox.supplier.ZomatoClient

import java.nio.charset.Charset

import static lunchbox.MenuRating.AWFUL
import static lunchbox.MenuRating.GOOD

@Configuration
class BrnoConfiguration {

    @Autowired
    ZomatoClient zomatoClient

    @Bean
    MenuSupplier vaciceSupplier() {
        zomato(
                16505961,
                'Veselá Vačice',
                'http://www.veselavacice.cz/denni-menu/'
        )
    }

    @Bean
    MenuSupplier freelandSupplier() {
        zomato(
                16505893,
                'Freeland',
                'http://freelandclub.cz/',
                AWFUL
        )
    }

    @Bean
    MenuSupplier drevenyOrelSupplier() {
        zomato(
                16506896,
                'U Dřevěného orla',
                'http://www.drevenyorel.cz/cz/page/tydenni-menu.html'
        )
    }

    @Bean
    MenuSupplier drevenyVlkSupplier() {
        zomato(
                16507597,
                'U Dřevěného vlka',
                'http://www.drevenyvlk.cz/cz/page/tydenni-menu.html'
        )
    }

    @Bean
    MenuSupplier jakobySupplier() {
        zomato(
                16506525,
                'Jakoby',
                'http://www.restauracejakoby.cz/?page_id=1128'
        )
    }

    @Bean
    MenuSupplier statlSupplier() {
        zomato(
                18494615,
                'Štatl',
                'http://www.statlbrno.cz/denni-menu',
                GOOD
        )
    }

    @Bean
    MenuSupplier knoflikSupplier() {
        zomato(
                16507592,
                'Na Knoflíku',
                'http://www.brnorestaurace.cz/tydenni-menu/',
                AWFUL
        )
    }

    @Bean
    MenuSupplier certiDvorakovaSupplier() {
        zomato(
                16507255,
                'U Třech čertů - Dvořákova',
                'http://ucertu.cz/nabidka-dvorakova/',
                AWFUL
        )
    }

    @Bean
    MenuSupplier panoptikumSupplier(PanoptikumParser parser) {
        new HtmlMenuSupplier(
                name: 'Panoptikum',
                url: 'http://www.restaurace-panoptikum.cz/denni-menu.html',
                charset: Charset.forName('cp1250'),
                parser: parser,
                rating: AWFUL
        )
    }

    @Bean
    MenuSupplier cajplSupplier(CajplParser parser) {
        new HtmlMenuSupplier(
                name: 'Lokál U Cajpla',
                url: 'http://lokal-ucaipla.ambi.cz/cz/menu?id=17809',
                parser: parser,
                rating: GOOD
        )
    }

    @Bean
    MenuSupplier stopkaSupplier(StopkaParser parser) {
        new HtmlMenuSupplier(
                name: 'Stopkova Plzeňská Pivnice',
                url: 'http://www.kolkovna.cz/cs/denni-menu',
                parser: parser
        )
    }

    @Bean
    MenuSupplier forkysSupplier(ForkysParser parser) {
        new HtmlMenuSupplier(
                name: 'Forkys',
                url: 'http://www.forkys.cz/poledni-menu/',
                parser: parser,
                rating: GOOD
        )
    }

    @Bean
    MenuSupplier zlataLodSupplier(ZlataLodParser parser) {
        new HtmlMenuSupplier(
                name: 'Zlatá loď',
                url: 'http://www.zlatalod.com/denni-menu',
                parser: parser
        )
    }

    @Bean
    MenuSupplier varnaSupplier(VarnaParser parser) {
        new HtmlMenuSupplier(
                name: 'Varna',
                url: 'http://www.restauracevarna.cz/denni-menu/',
                parser: parser,
                rating: AWFUL
        )
    }

    @Bean
    MenuSupplier patrickSupplier() {
        new EmptyMenuSupplier(
                name: 'Saint Patrick Pub',
                url: 'http://saintpatrickpub.cz/dennni-menu/'
        )
    }

    private ZomatoSupplier zomato(int id, String name, String url, MenuRating rating = MenuRating.defaultRating()) {
        new ZomatoSupplier(
                zomatoId: id,
                name: name,
                url: url,
                zomatoClient: zomatoClient,
                rating: rating)
    }

}
