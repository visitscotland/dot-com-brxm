package com.visitscotland.brmx.dms;

public class DMSConstants {

    private DMSConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TYPE_SERVICES = "serv";
    public static final String TYPE_SEE_DO = "cate,acti,attr,reta";

    public static final String CAT_ICENTRE = "vics";

    public static final String AWARD_IKNOW = "qaiknowscotland";

    public static final String SORT_ALPHA = "alpha";

    /**
     * Unique constants for the Map Search functionallity.
     */
    public class MapSearch {

        private MapSearch() {
            throw new IllegalStateException("Utility class");
        }

        public static final String PROPERTIES = "properties";
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    //Endpoints
    //TODO Remove %s at the beginning of the constants
    public static final String PRODUCT_SEARCH = "%s/info/%s/search-results?";
    public static final String PRODUCT_SEARCH_DATA_MAP = "%s/data/product-search/map?";
    public static final String VS_DMS_PRODUCT_CARD = "%s/data/products/card?";
    public static final String META_PRODUCT_LIST = "/data/meta/%s/list?%s";
    public static final String META_LOCATIONS = "/data/meta/location/list?full";

    //Parameters


}
