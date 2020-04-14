package com.visitscotland.brmx.components.content;


import com.visitscotland.brmx.beans.*;
import com.visitscotland.brmx.beans.dms.LocationObject;
import com.visitscotland.brmx.beans.mapping.Coordinates;
import com.visitscotland.brmx.beans.mapping.FlatImage;
import com.visitscotland.brmx.beans.mapping.FlatLink;
import com.visitscotland.brmx.beans.mapping.FlatStop;
import com.visitscotland.brmx.utils.CommonUtils;
import com.visitscotland.brmx.utils.HippoUtils;
import com.visitscotland.brmx.utils.LocationLoader;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class ItineraryContentComponent extends PageContentComponent<Itinerary> {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryContentComponent.class);

    public final String STOPS_MAP = "stops";
    public final String FIRST_STOP_LOCATION = "firstStopLocation";
    public final String LAST_STOP_LOCATION = "lastStopLocation";
    public final String HERO_COORDINATES = "heroCoordinates";

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        generateIndexPage(request);
        generateStops(request);
        setCoordinates(request);
    }

    private void setCoordinates(HstRequest request) {
        LocationObject location = LocationLoader.getLocation(getDocument(request).getHeroImage().getLocation(), request.getLocale());

        if (location != null){
            Coordinates coordinates = new Coordinates();
            coordinates.setLatitude(location.getLatitude());
            coordinates.setLongitude(location.getLongitude());
            request.setAttribute(HERO_COORDINATES, coordinates);
        }
    }

    /**
     *
     * @param request HstRequest
     */
    private void generateStops(HstRequest request)  {

        final Itinerary itinerary = getDocument(request);
        final String ITINERARY_ALERTS = "alerts";
        final String ADDRESS = "address";
        final String LOCATION = "city";
        final String URL = "url";
        final String TIME_TO_EXPLORE = "timeToExplore";
        final String PRICE = "price";
        final String DISPLAY_PRICE = "displayPrice";
        final String NAME = "name";
        final String LAT = "latitude";
        final String LON = "longitude";
        final String FACILITIES = "keyFacilities";
        final String OPENING = "todayOpeningTime";
        final String START_TIME = "startTime";
        final String END_TIME = "endTime";
        final String IMAGE = "images";
        final String OPENING_DAY = "day";
        final String OPENING_STATE = "state";
        final String OPENING_PROVISIONAL = "provivisional";
        final Map<String ,FlatStop> products =  new LinkedHashMap<>();

        String firstStopId = null;
        String lastStopId = null;
        int index = 1;

        for (Day day: itinerary.getDays()) {
            for (Stop stop : day.getStops()) {
                List<String> errors = new ArrayList<>();
                FlatStop model = new FlatStop(stop);
                Coordinates coordinates = new Coordinates();
                String visitDuration = null;
                String location = null;
                model.setIndex(index++);
                FlatImage flatImage = null;

                if (stop.getImage() != null) {
                    Image cmsImage = (Image) stop.getStopItemImage();
                    if (cmsImage != null) {
                        flatImage = CommonUtils.getTranslatedImage(cmsImage,request.getLocale());
                        checkImageErrors(flatImage,request.getLocale(),errors);
                        if (!(stop.getStopItem() instanceof DMSLink)){
                            LocationObject locationObject = LocationLoader.getLocation(cmsImage.getLocation(), request.getLocale());
                            if (locationObject != null) {
                               location = locationObject.getName();
                            }
                        }
                    }
                }

                if (stop.getStopItem() instanceof DMSLink){
                    DMSLink dmsLink = (DMSLink) stop.getStopItem();
                    //CONTENT prefix on error messages could mean that the problem can be fixed by altering the content.
                    try {

                        if (dmsLink.getProduct() == null){
                           errors.add("The product's id  was not provided");
                            logger.warn(CommonUtils.contentIssue("The product's id was not provided for %s, Stop %s", itinerary.getName(), model.getIndex()));
                        } else {
                            JSONObject product = CommonUtils.getProduct(dmsLink.getProduct(), request.getLocale());
                            if (product == null){
                                errors.add("The product id does not exist in the DMS");
                                logger.warn(CommonUtils.contentIssue("The product id does not exist in the DMS for %s, Stop %s", itinerary.getName(), model.getIndex()));
                            } else {

                                FlatLink ctaLink = new FlatLink(this.getCtaLabel(dmsLink.getLabel(),request.getLocale()),product.getString(URL));
                                model.setCtaLink(ctaLink);
                                if (product.has(ADDRESS)){
                                    JSONObject address =product.getJSONObject(ADDRESS);
                                    model.setAddress(address);
                                    location = address.has(LOCATION)?address.getString(LOCATION):null;
                                }


                                model.setTimeToexplore(product.has(TIME_TO_EXPLORE)? product.getString(TIME_TO_EXPLORE):null);
                                if (product.has(TIME_TO_EXPLORE)){
                                    visitDuration = product.getString(TIME_TO_EXPLORE);
                                }

                                if (product.has(PRICE)){
                                    JSONObject price = product.getJSONObject(PRICE);
                                    model.setPrice(price.getString(DISPLAY_PRICE));
                                }

                                if (stop.getImage() == null && product.has(IMAGE) ){
                                    JSONArray dmsImageList = product.getJSONArray(IMAGE);
                                    flatImage = new FlatImage(dmsImageList.getJSONObject(0),product.getString(NAME));
                                }

                                coordinates.setLatitude(product.getDouble(LAT));
                                coordinates.setLongitude(product.getDouble(LON));
                                model.setCoordinates(coordinates);

                                if (product.has(FACILITIES)){
                                    List<JSONObject> facilities = new ArrayList<>();
                                    JSONArray keyFacilitiesList = product.getJSONArray(FACILITIES);
                                    if (keyFacilitiesList!=null){
                                        for (int i = 0; i < keyFacilitiesList.length(); i++) {
                                            facilities.add(keyFacilitiesList.getJSONObject(i));
                                        }
                                    }
                                    model.setFacilities(facilities);
                                }

                                if (product.has(OPENING)){
                                    JSONObject opening = product.getJSONObject(OPENING);
                                    //TODO adjust the message to designs when ready
                                    if ((opening.has(OPENING_STATE)) && (!opening.getString(OPENING_STATE).equalsIgnoreCase("unknown"))) {
                                        String openingMessge = opening.getBoolean(OPENING_PROVISIONAL)==false? "Usually " : "Provisionally ";
                                        openingMessge = openingMessge + opening.getString(OPENING_STATE) +" "+ opening.getString(OPENING_DAY);
                                        if ((opening.has(START_TIME)) && (opening.has(END_TIME))) {
                                            openingMessge = openingMessge + ": " + opening.getString(START_TIME) + "-" + opening.getString(END_TIME);
                                        }
                                        model.setOpen(openingMessge);
                                        model.setOpenLink(new FlatLink(HippoUtils.getResourceBundle("stop.opening", "itinerary",
                                                        request.getLocale()),ctaLink.getLink()+"#opening"));
                                    }
                                }

                            }
                        }
                    } catch (IOException exception) {
                        errors.add("Error while querying the DMS: " + exception.getMessage());
                        logger.error("Error while querying the DMS for " + itinerary.getName() + ", Stop " + model.getIndex() + ": " + exception.getMessage());
                    }
                } else if (stop.getStopItem() instanceof ItineraryExternalLink){
                    ItineraryExternalLink externalLink = (ItineraryExternalLink) stop.getStopItem();
                    visitDuration = externalLink.getTimeToExplore();

                    if (externalLink.getExternalLink() != null) {
                        FlatLink ctaLink = new FlatLink(this.getCtaLabel(externalLink.getExternalLink().getLabel(), request.getLocale()), externalLink.getExternalLink().getLink());
                        model.setCtaLink(ctaLink);
                    }

                    if (externalLink.getCoordinates() != null) {
                        coordinates.setLatitude(externalLink.getCoordinates().getLatitude());
                        coordinates.setLongitude(externalLink.getCoordinates().getLongitude());
                        model.setCoordinates(coordinates);
                    }

                }else{
                    errors.add("The product's id  was not provided");
                    logger.warn(CommonUtils.contentIssue("The product's id  was not provided for %s, Stop %s", itinerary.getName(), model.getIndex()));
                }

                if ((stop.getTips()!= null && !stop.getTips().getContent().isEmpty()) && (stop.getTipsTitle() == null || stop.getTipsTitle().isEmpty())){
                    errors.add("Tips title is required to show tips");
                    logger.warn(CommonUtils.contentIssue("Tip title was not provided when tried to add a tip for %s, Stop %s", itinerary.getName(), model.getIndex()));
                }
                lastStopId = model.getIdentifier();
                if (firstStopId == null){
                    firstStopId = lastStopId;
                }
                if (model.getCoordinates()!=null) {
                    flatImage.setCoordinates(model.getCoordinates());
                }
                model.setImage(flatImage);
                if (visitDuration!=null) {
                    visitDuration = visitDuration.equalsIgnoreCase("1") ? visitDuration + " " + HippoUtils.getResourceBundle("stop.hour", "itinerary", request.getLocale())
                            : visitDuration + " " + HippoUtils.getResourceBundle("stop.hours", "itinerary", request.getLocale());
                    model.setTimeToexplore(visitDuration);
                }

                if (stop.getSubtitle() == null || stop.getSubtitle().isEmpty()) {
                    model.setSubTitle(location);
                }else{
                    model.setSubTitle(stop.getSubtitle());
                }
                model.setErrorMessages(errors);
                products.put(model.getIdentifier(), model);
            }
        }

        if (products.size() > 0 ) {
            request.setAttribute(FIRST_STOP_LOCATION, products.get(firstStopId).getSubTitle());
            request.setAttribute(LAST_STOP_LOCATION, products.get(lastStopId).getSubTitle());

            request.setAttribute(STOPS_MAP, products);
        }
    }
  

}
