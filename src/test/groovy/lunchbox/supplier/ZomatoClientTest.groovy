package lunchbox.supplier

import org.junit.Ignore

@Ignore("Ignored during build, because of request number limitation")
class ZomatoClientTest extends GroovyTestCase {

    ZomatoClient client = new ZomatoClient()

    @Override
    void setUp() {
        client.zomatoToken = 'xxx'
    }

    void testVacice() {
        def menu = client.getRestaurantMenu(16505961)
        println menu
    }
}
