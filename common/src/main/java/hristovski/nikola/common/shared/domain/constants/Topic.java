package hristovski.nikola.common.shared.domain.constants;

public class Topic {
    // TODO DONT HARDCOVE THE VERSION OF THE EVENTS

    // Always add the version of the application after the topic name
    // so multiple application versions can be in production without exchanging wrong messages
    public static final String TOPIC_PRODUCT_RATINGS = "t_product_ratings_0.0.1-SNAPSHOT";
    public static final String TOPIC_PRODUCT_RATING_STATISTICS_CHANGED = "t_product_rating_s_c_0.0.1-SNAPSHOT";
    public static final String TOPIC_PRODUCT_RATING_CHANGED = "t_product_rating_changed_0.0.1-SNAPSHOT";
    public static final String TOPIC_PRODUCT_CREATED = "t_product_created_0.0.1-SNAPSHOT";
    public static final String TOPIC_CREATE_SHOPPING_CART_ITEM = "t_create_shopping_cart_item_0.0.1-SHANPSHOT";
}
