package lunchbox.supplier

import lunchbox.Menu
import lunchbox.MenuItem
import lunchbox.MenuSupplier

//@Component
class FakeSupplier implements MenuSupplier {

    String name = 'Fake Pub'
    String url = 'http://www.google.com'

    @Override
    Menu getMenu() {
        new Menu(
                name,
                url,
                [new MenuItem('Item 1', '123')]
        )
    }
}
