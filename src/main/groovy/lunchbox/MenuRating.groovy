package lunchbox

enum MenuRating {

    GOOD(1),
    OK(2),
    AWFUL(3)

    final int order
    private MenuRating(int order) {
        this.order = order
    }

    static MenuRating defaultRating() {
        OK
    }
}