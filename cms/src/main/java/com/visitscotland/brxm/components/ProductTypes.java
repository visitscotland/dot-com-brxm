package com.visitscotland.brxm.components;

import java.util.HashMap;
import java.util.Map;
import vs.ase.Contract;


/**
 * This class has been copied from data-service-common {@code vs.ase.dms.ProductTypes}
 */
@Deprecated
public enum ProductTypes {

    ACCOMMODATION("acco", "Accommodation", "accommodation"),
    EVENT("even", "Event", "events"),
    ATTRACTION("attr", "Attraction", "see-do"),
    ACTIVITY("acti", "Activity", "see-do"),
    FOOD_DRINK("cate", "Food & Drink", "food-drink"),
    ENTERTAINMENT("ente", "Entertainment", "see-do"),
    FACILITY("faci", "Facility", "facilities"),
    SHOPPING("reta", "Shopping", "shopping"),
    TOURIST_INFO("serv", "General & Tourist Information", "services"),
    TRANSPORT("tran", "Transport", "transport"),
    ORGANISATION("busi", "Organisation", "organisation"),
    CONFERENCES_WEDDINGS("conf", "Conferences & Weddings", "conferences-weddings"),
    TRAVEL_TRADE("trtr", "Travel Trade", "travel-trade"),
    PROPERTY("prop", "Property", "property"),
    BROCHURE("broc", "Brochure", "brochure"),
    TOWNS_VILLAGES("twnv", "Towns & Villages", "towns-villages"),
    EDUCATION_TRAINING("edtr", "Education & Training", "education-training"),
    ARTIST("arti", "Artist", "artist"),
    PROPERTY_REAL_ESTATE("real", "Real Estate Property", "property"),
    TOURS("tour", "Tours & Tour Guides", "tours");

    private final String _id;
    private final String _description;
    private final String _urlPath;

    private static final Map<String, ProductTypes> BY_PARAM_VALUE = new HashMap<String, ProductTypes>();

    static {
        for (final ProductTypes so : values()) {
            BY_PARAM_VALUE.put(so._id, so);
        }
    }


    /**
     * Private constructor.
     *
     * @param value The value for thsi enum member.
     */
    private ProductTypes(final String id, final String description, final String urlPath) {
        _id = id;
        _description = description;
        _urlPath = urlPath;
    }


    /**
     * @return The param value for this sort order.
     */
    public String getId() {
        return _id;
    }


    /**
     * @return Description for this param value for use in GUI.
     */
    public String getDescription() {
        return _description;
    }

    /**
     *
     * @return URL Path for building up product listing URL
     */
    public String getUrlPath() {
        return _urlPath;
    }


    /**
     * Get the sort order by its value.
     *
     * @param paramValue String containing one of the values of the SortOrder
     *                   enum.
     * @return The SortOrder.
     */
    public static ProductTypes byId(final String id) {
        return Contract.checkNull(BY_PARAM_VALUE.get(Contract.checkEmpty(id, "id")), "byId result");
    }
}
