package com.visitscotland.brmx.components.content;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brmx.beans.*;

import com.visitscotland.brmx.beans.dms.LocationObject;
import com.visitscotland.brmx.beans.mapping.*;
import com.visitscotland.brmx.beans.mapping.Coordinates;
import com.visitscotland.brmx.utils.CommonUtils;
import com.visitscotland.brmx.dms.LocationLoader;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class ListicleContentComponent extends PageContentComponent<Listicle> {

    private static final Logger logger = LoggerFactory.getLogger(ListicleContentComponent.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        generateItems(request, getDocument(request));
    }

    /**
     * @param request HstRequest
     * @param listicle listicle document
     */
    private void generateItems(HstRequest request, Listicle listicle) {
        final String LISTICLE_ITEMS = "items";
        final String ADDRESS = "address";
        final String LOCATION = "city";
        final String LATITUDE = "latitude";
        final String LONGITUDE = "longitude";
        final String NAME = "name";
        final String IMAGE = "images";
        final List<FlatListicle> items =  new ArrayList<>();

        int index = listicle.getDescOrder()?listicle.getItems().size():1;


        //TODO:separate image, main product and optional cta in different methods ?
        for (ListicleItem listicleItem : listicle.getItems()) {
            ObjectMapper mapper = new ObjectMapper();
            List<String> errors = new ArrayList<>();
            FlatListicle model = new FlatListicle(listicleItem);
            List<FlatLink> links = new ArrayList<>();
            FlatImage flatImage = null;
            String location = null;

            model.setIndex(listicle.getDescOrder()?index--:index++);

            //Set the image
            if (listicleItem.getListicleItemImage() != null) {
                if (listicleItem.getListicleItemImage() instanceof InstagramImage) {
                    InstagramImage instagramLink = (InstagramImage) listicleItem.getListicleItemImage();
                    location = instagramLink.getLocation();
                    try {
                        String caption = CommonUtils.getInstagramCaption(instagramLink);
                        if (caption != null) {
                            flatImage = new FlatImage(instagramLink,caption,request.getLocale());
                        } else {
                            errors.add("The Instagram id is not valid");
                            logger.warn(CommonUtils.contentIssue("The Instagram id %s is not valid, Listicle = %s - %s",
                                    instagramLink.getId(), listicle.getPath(), listicleItem.getTitle()));
                        }
                    } catch (IOException e) {
                        errors.add("Error while accessing Instagram: " + e.getMessage());
                        logger.error("Error while accessing Instagram", e);
                    }
                } else {
                    if (listicleItem.getListicleItemImage() instanceof Image) {
                        Image cmsImage = (Image) listicleItem.getListicleItemImage();
                        if (cmsImage != null) {
                            flatImage = new FlatImage(cmsImage,request.getLocale());
                            checkImageErrors(flatImage,request.getLocale(),errors);
                            LocationObject locationObject = LocationLoader.getLocation(cmsImage.getLocation(), request.getLocale());
                            if (locationObject!=null) {
                                flatImage.setCoordinates(new Coordinates(locationObject.getLatitude(), locationObject.getLongitude()));
                                if (listicleItem.getListicleItem() != null && !(listicleItem.getListicleItem() instanceof DMSLink)){
                                    location = locationObject.getName();
                                }
                            }
                        }
                    }
                }
            }

            //Set the main product
            if (listicleItem.getListicleItem() != null) {
                if (listicleItem.getListicleItem() instanceof DMSLink) {
                    DMSLink dmsLink = (DMSLink) listicleItem.getListicleItem();
                    JsonNode product;
                    try {
                        product = CommonUtils.getProduct(dmsLink.getProduct(), request.getLocale());
                        if (product == null) {
                            errors.add("The product id does not exists in the DMS");
                            logger.warn(CommonUtils.contentIssue("The product's id  wasn't provided for %s, Listicle = %s - %s",
                                    dmsLink.getProduct(), listicle.getPath(), listicleItem.getTitle()));
                        } else {
                            if (product.has(ADDRESS)){
                                JsonNode address = product.get(ADDRESS);
                                if (location== null && address.has(LOCATION)) {
                                    location = address.get(LOCATION).asText();
                                }
                            }

                              if (flatImage == null){
                                if (product.has(IMAGE)){
                                    JsonNode dmsImageList = product.get(IMAGE);
                                  	flatImage = new FlatImage(dmsImageList.get(0),product.get(NAME).asText());
                                  	if (product.has(LATITUDE) && product.has(LONGITUDE)){
                                      Coordinates coordinates = new Coordinates(product.get(LATITUDE).asDouble(), product.get(LONGITUDE).asDouble());
                                      flatImage.setCoordinates(coordinates);
                                  }
                                }
                            }else{
                              if (flatImage.getCoordinates() == null){
                                  Coordinates coordinates = new Coordinates(product.get(LATITUDE).asDouble(),product.get(LONGITUDE).asDouble());
                                  flatImage.setCoordinates(coordinates);
                              }
                            }

                             model.setFacilities(getFacilities(product));

                        }
                    } catch (IOException e) {
                        errors.add("Error while querying the DMS: " + e.getMessage());
                        logger.error(String.format("Error while querying the DMS for %s, Listicle item %s: 5s",
                                listicle.getName(), model.getIndex(), e.getMessage()));
                    }
                }

                links.add(createLink(request, listicleItem.getListicleItem()));

            }

            //Set Extra Links
            for (HippoCompound compound : listicleItem.getExtraLinks()) {
                links.add(createLink(request, compound));
            }
            if (listicleItem.getSubtitle() == null || listicleItem.getSubtitle().isEmpty()) {
                model.setSubTitle(location);
            }else{
                model.setSubTitle(listicleItem.getSubtitle());
            }
            model.setImage(flatImage);
            model.setLinks(links);
            model.setErrorMessages(errors);
            items.add(model);
        }

        request.setAttribute(LISTICLE_ITEMS, items);
    }
}
