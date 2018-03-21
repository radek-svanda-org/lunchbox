package lunchbox

import groovy.transform.Immutable

@Immutable
class MenuItem {
    String name
    String price

    @Override
    String toString() {
        "MenuItem { name='$name', price='$price' }"
    }
}
