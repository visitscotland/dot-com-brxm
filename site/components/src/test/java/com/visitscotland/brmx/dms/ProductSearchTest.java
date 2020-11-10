package com.visitscotland.brmx.dms;

import com.visitscotland.brmx.utils.Properties;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static com.visitscotland.brmx.dms.ProductSearchBuilder.*;

//TODO Convert to JUnit 5
// TODO this test relies on lan conection: FIX
@ExtendWith(MockitoExtension.class)
public class ProductSearchTest {

    private final static String DEFAULT_TYPE = "cate";

    //TODO when LocationLoader is not available

    @Test()
    @DisplayName("A test with no Product Type should throw an exception")
    public void noProductType() {

        Assertions.assertThrows(RuntimeException.class, () -> new ProductSearchBuilder().build());
    }

    @Test
    public void basic() {
        String url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .build();

        Assert.assertTrue("A basic URL couldn't be build", url != null && !url.isEmpty());
        validate(url);
    }

    @Test()
    public void productSearch() {

        String url = new ProductSearchBuilder().productTypes("cate").build();

        Assert.assertTrue(
                String.format("The Generated URL is expected to contain " + PRODUCT_SEARCH + " (%s) ", Properties.VS_DMS_SERVICE, PATH_FOOD_DRINK, url),
                url.contains(String.format(PRODUCT_SEARCH, Properties.VS_DMS_SERVICE, PATH_FOOD_DRINK)));
        validate(url);
    }

    @Test()
    public void productSearchAccommodation() {
        String url = new ProductSearchBuilder().productTypes("acco").build();

        Assert.assertTrue(
                String.format("The Generated URL is expected to contain the parameter %s (%s) ", AVAILABILITY, url),
                url.contains(AVAILABILITY));
        validate(url);
    }

    @Test
    public void productSearchUndefinedType() {
        final String TYPE = "test";
        String url =  new ProductSearchBuilder().productTypes(TYPE).build();

        Assert.assertTrue(
                String.format("The Generated URL is expected to contain the url path %s ", PATH_SEE_DO),
                url.contains(PATH_SEE_DO));
    }

    private void checkLocationAvailability() {
        final String LOCATION = "Edinburgh";

        if (LocationLoader.getInstance().getLocation(LOCATION, null) == null) {
            Assert.fail("LocationLoader might not be working or the location " + LOCATION + " no longer exists");
        }
    }

    @Test
    public void location() {
        final String LOCATION = "Edinburgh";

        checkLocationAvailability();

        String url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .location(LOCATION)
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain %s=%s and %s={some id} (%s) ", LOCATION_NAME_PARAM, LOCATION, LOCATION_PLACE_PARAM, url),
                url.contains(LOCATION_NAME_PARAM + "=" + LOCATION) && url.contains(LOCATION_PLACE_PARAM + "="));
        validate(url);
    }

    @Test
    public void locationPolygon() {
        final String LOCATION = "The Highlands";

        checkLocationAvailability();

        String url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .location(LOCATION)
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain the parameters '%s' and '%s' (%s) ", LOCATION_NAME_PARAM, LOCATION_POLYGON_PARAM, url),
                url.contains(LOCATION_NAME_PARAM + "=") && url.contains(LOCATION_POLYGON_PARAM + "="));
        validate(url);
    }

    @Test
    //TODO localtion by languageTag
    public void locationTranslation() {
        final String LOCATION = "Edinburgh";

        checkLocationAvailability();

        String url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .location(LOCATION)
                .locale(Locale.forLanguageTag("es-es"))
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain %s=Edimburgo (%s) ", LOCATION_NAME_PARAM, url),
                url.contains(LOCATION_NAME_PARAM + "=Edimburgo"));
        validate(url);
    }

    @Test
    public void category() {
        String url;

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .category("cat1")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain %s=cat1 (%s) ", CATEGORY_PARAM, url),
                url.contains(CATEGORY_PARAM + "=cat1"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .category("cat1")
                .category("cat2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain two categories (%s) ", url),
                url.contains(CATEGORY_PARAM + "=cat1") && url.contains(CATEGORY_PARAM + "=cat2"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .category("")
                .category((String) null)
                .category(" \t \n ")
                .category("cat1&cat2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain no categories (%s) ", url),
                !url.contains(CATEGORY_PARAM + "="));
        validate(url);
    }

    @Test
    public void awards() {
        String url;

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .award("1")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain %s=1 (%s) ", AWARD_PARAM, url),
                url.contains(AWARD_PARAM + "=1"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .award("1")
                .award("2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain two awards (%s) ", url),
                url.contains(AWARD_PARAM + "=1") && url.contains(AWARD_PARAM + "=2"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .award("")
                .award((String) null)
                .award(" \t \n ")
                .award("1&2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain no awards (%s) ", url),
                !url.contains(AWARD_PARAM + "="));
        validate(url);

    }

    @Test
    public void facilities() {
        String url;

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .facility("1")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain %s=1 (%s) ", FACILITY_PARAM, url),
                url.contains(FACILITY_PARAM + "=1"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .facility("1")
                .facility("2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain two facilities(%s) ", url),
                url.contains(FACILITY_PARAM + "=1") && url.contains(FACILITY_PARAM + "=2"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .facility("")
                .facility((String) null)
                .facility(" \t \n ")
                .facility("1&2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain no facilities (%s) ", url),
                !url.contains(FACILITY_PARAM + "="));
        validate(url);
    }

    @Test
    public void rating() {
        String url;

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .rating("1")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain %s=1 (%s) ", RATING_PARAM, url),
                url.contains(RATING_PARAM + "=1"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .rating("1")
                .rating("2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain two facilities(%s) ", url),
                url.contains(RATING_PARAM + "=1") && url.contains(RATING_PARAM + "=2"));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .rating("")
                .rating((String) null)
                .rating(" \t \n ")
                .rating("1&2")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to contain no facilities (%s) ", url),
                !url.contains(RATING_PARAM + "="));
        validate(url);

    }

    @Test
    public void order() {
        String url;

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .sortBy("DISTANCE")
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to have an order (%s) ", url),
                url.contains(ORDER_PARAM + "="));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .sortBy("NONE")
                .build();
        Assert.assertFalse(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(ORDER_PARAM + "="));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .sortBy("SOMETHING THAT DOES NOT EXISTS")
                .build();
        Assert.assertFalse(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(ORDER_PARAM + "="));
        validate(url);

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .sortBy(null)
                .build();
        Assert.assertFalse(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(ORDER_PARAM + "="));
        validate(url);


    }

    @Test
    public void proximity() {
        final String LOCATION = "Edinburgh";
        final Double PROXIMITY = 6.0;


        String url;
        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .location(LOCATION)
                .proximity(PROXIMITY)
                .sortBy(null)
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(PROXIMITY_LOCATION_PARAM + "=" + PROXIMITY));

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .location(LOCATION)
                .proximity(null)
                .sortBy(null)
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(PROXIMITY_LOCATION_PARAM + "=" + DEFAULT_PROXIMITY));

        url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .location(LOCATION)
                .proximity(0.0)
                .sortBy(null)
                .build();
        Assert.assertTrue(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(PROXIMITY_LOCATION_PARAM + "=" + DEFAULT_PROXIMITY));

        validate(url);
    }

    @Test
    public void coordinates() {
        String url = new ProductSearchBuilder().productTypes(DEFAULT_TYPE)
                .sortBy(null)
                .build();
        Assert.assertFalse(
                String.format("The Generated URL is expected to have no order (%s) ", url),
                url.contains(ORDER_PARAM + "="));
        validate(url);
    }

    @Test
    public void fromProductSearch() {
        //TODO Mock ProductSearchObject
    }

    //TODO Create test-utils?
    private void validate(String url) {
        //The following is true just for Product Search
        //At least one question mark
        Assert.assertTrue(String.format("The url should not contain spaces (%s)", url),
                url.contains("?")
        );
        //No more than one question mark
        Assert.assertEquals(String.format("Only one question mark is expected (%s)", url),
                1, url.chars().filter(ch -> ch == '?').count()
        );
        //Starts with http, https (fully qualified) or  / (domain related)
        Assert.assertTrue(String.format("The url should starts with http[s] or / (%s)", url),
                url.startsWith("/") || url.startsWith("http")
        );
        //No space character in it
        Assert.assertFalse(String.format("The url should not contain spaces (%s)", url),
                url.contains(" ")
        );

    }
}
