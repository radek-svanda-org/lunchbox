package lunchbox

@FunctionalInterface
interface MenuSupplier {
    Menu getMenu()
    String getName()
    String getUrl()
}