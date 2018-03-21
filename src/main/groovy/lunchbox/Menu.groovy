package lunchbox

class Menu {
    final String name
    final String url
    final List<MenuItem> items
    final MenuRating rating

    Menu(String name, String url, List<MenuItem> items, MenuRating rating = MenuRating.defaultRating()) {
        this.name = name
        this.url = url
        this.items = items
        this.rating = rating
    }
}