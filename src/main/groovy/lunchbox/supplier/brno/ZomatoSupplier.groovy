package lunchbox.supplier.brno

import lunchbox.Menu
import lunchbox.MenuRating
import lunchbox.MenuSupplier
import lunchbox.supplier.ZomatoClient

class ZomatoSupplier implements MenuSupplier {

    int zomatoId
    String name
    String url
    ZomatoClient zomatoClient
    MenuRating rating

    @Override
    Menu getMenu() {
        new Menu(
                name, url, zomatoClient.getRestaurantMenu(zomatoId), rating
        )
    }
}
