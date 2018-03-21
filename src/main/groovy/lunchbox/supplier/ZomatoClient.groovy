package lunchbox.supplier

import com.google.common.cache.CacheBuilder
import com.google.common.cache.LoadingCache
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import lunchbox.MenuItem

@Component
class ZomatoClient {

    @Value('${zomato.token}')
    String zomatoToken

    def parseMenuItem = { json ->
        //TODO validate date
        json.dishes.collect { new MenuItem(it.dish.name, it.dish.price) }
    }

    def parseJsonResponse = { json ->
        if (json.status != "success") {
            return [new MenuItem("Failed to read menu from Zomato", "---")]
        } else {
            return parseMenuItem(json.daily_menus[0].daily_menu)
        }
    }

    LoadingCache<Integer, List<MenuItem>> menuCache = CacheBuilder.newBuilder().build({ Integer key ->

        def url = "https://developers.zomato.com/api/v2.1/dailymenu?res_id=${key}"
        def html = new HTTPBuilder(url).get([
                contentType: ContentType.JSON,
                headers: [
                        user_key: zomatoToken
                ]
        ])

        return parseJsonResponse(html)
    })

    List<MenuItem> getRestaurantMenu(int resId) {
        menuCache.get(resId)
    }

}
