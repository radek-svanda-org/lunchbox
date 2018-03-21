package lunchbox.supplier

import lunchbox.Menu
import lunchbox.MenuRating
import lunchbox.MenuSupplier

class EmptyMenuSupplier implements MenuSupplier {
    String name = ''
    String url = ''
    MenuRating rating = MenuRating.defaultRating()

    @Override
    Menu getMenu() {
        new Menu(name, url, [], rating)
    }
}
