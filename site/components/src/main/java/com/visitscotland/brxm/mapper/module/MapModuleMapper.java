package com.visitscotland.brxm.mapper.module;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.*;
import com.visitscotland.brxm.dms.model.LocationObject;
import com.visitscotland.brxm.hippobeans.*;
import com.visitscotland.brxm.mapper.ImageMapper;
import com.visitscotland.brxm.model.*;
import com.visitscotland.brxm.services.MapService;
import com.visitscotland.brxm.services.ResourceBundleService;
import com.visitscotland.brxm.utils.CMSProperties;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.utils.SiteProperties;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.core.component.HstRequest;
import org.onehippo.taxonomy.api.Category;
import org.onehippo.taxonomy.api.Taxonomy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;

@Component
public class MapModuleMapper extends ModuleMapper<MapModule, MapsModule> {

    private static final Logger logger = LoggerFactory.getLogger(MapModuleMapper.class);

    private static final String MAPS_API_PROPERTY = "mapsAPI";
    private static final String MAIN_MAP_PAGE = "mainMapPage";
    private static final String HIDE_PAGE_INFO = "hidePageInfo";
    static final String BUNDLE_ID = "map";

    static final String ID = "id";
    static final String TYPE = "type";
    static final String GEOMETRY = "geometry";
    static final String DISCOVER = "map.discover";
    static final String PROPERTIES = "properties";
    static final String DESCRIPTION = "description";
    static final String NAME = "name";
    static final String REGIONS = "regions";
    static final String TITLE = "title";
    static final String MAP_BUNDLE = "map";
    static final String POINT = "Point";

    private final MapService mapService;
    private final HippoUtilsService hippoUtilsService;
    private final ObjectMapper mapper;
    private final DMSDataService dmsDataService;
    private final ResourceBundleService bundle;
    private final CMSProperties cmsProperties;
    private final SiteProperties siteProperties;
    private final ImageMapper imageMapper;
    private final LocationLoader locationLoader;

    public MapModuleMapper(MapService mapService, HippoUtilsService hippoUtilsService, ObjectMapper mapper, DMSDataService dmsDataService, ResourceBundleService bundle, CMSProperties properties, SiteProperties siteProperties, ImageMapper imageMapper, LocationLoader locationLoader) {
        this.mapService = mapService;
        this.hippoUtilsService = hippoUtilsService;
        this.mapper = mapper;
        this.dmsDataService = dmsDataService;
        this.bundle = bundle;
        this.cmsProperties = properties;
        this.siteProperties = siteProperties;
        this.imageMapper = imageMapper;
        this.locationLoader = locationLoader;
    }

    @Override
    void addLabels(PageCompositionHelper page) throws MissingResourceException {
        page.addAllSiteLabels(BUNDLE_ID);

    }

    @Override
    MapsModule map(MapModule document, PageCompositionHelper compositionHelper) throws PageCompositionException {
        Page page = compositionHelper.getPage();
        Locale locale = compositionHelper.getLocale();

        MapsModule module = new MapsModule();

        module.setId(document.getCanonicalUUID());
        module.setHippoBean(document);
        module.setTitle(document.getTitle());
        module.setIntroduction(document.getCopy());
        module.setTabTitle(document.getTabTitle());

        if (Boolean.TRUE.equals(document.isGoogleMap())) {
            configureGoogleMap(document, compositionHelper, module);
        }


        ObjectNode featureCollectionGeoJson = mapper.createObjectNode();
        featureCollectionGeoJson.put(TYPE, "FeatureCollection");
        ArrayNode features = mapper.createArrayNode();
        ArrayNode keys = mapper.createArrayNode();

        if (page instanceof Destination) {
            buildDestinationMapPages(locale,(Destination) page, document, module, keys, features);
        } else {
            module.setDetailsEndpoint("");
            module.setMapPosition(mapper.createObjectNode());
            //bespoke maps data and pins coming from DMS
            if (!Contract.isEmpty(document.getMapType())){
                module.setMapType(document.getMapType());
                //Feature places on top of these maps
                if (!Contract.isNull(document.getFeaturedPlacesItem())) {
                    mapService.addFeaturePlacesNode(module, document.getCategories(), locale, keys, features);
                }
                for (BespokeDmsMap bespokeMap : getValues(document.getMapType())) {
                    buildDMSMapPages(bespokeMap, module, keys, features, locale, null);
                }
            } else {
                // CMS maps, data and pins coming from CMS
                buildMapGeneralPages(compositionHelper.getRequest(), document, module, keys, features);
            }
        }

        //add first Json for filters
        module.setFilters(keys);

        //add second json (geoJson) to the module
        featureCollectionGeoJson.set("features", features);
        module.setGeoJson(featureCollectionGeoJson);
        return module;
    }

    private void configureGoogleMap(MapModule document, PageCompositionHelper compositionHelper, MapsModule module){
        module.setGoogleMap(document.isGoogleMap());
        //TODO: Remove this property
        compositionHelper.addProperty(MAIN_MAP_PAGE, true);
        compositionHelper.addProperty(HIDE_PAGE_INFO, true);
        compositionHelper.addProperty(MAPS_API_PROPERTY, siteProperties.getGoogleMapsApiKey());
    }

    /**
     * Method to build maps for General pages based on taxonomies and manual and optional featured places
     *
     * @param request the request
     * @param mapModuleDocument module coming from the CMS
     * @param module module to be sent to feds
     * @param keys filters for maps
     * @param features features information for mapcards
     */
    private void buildMapGeneralPages (HstRequest request, MapModule mapModuleDocument, MapsModule module, ArrayNode keys, ArrayNode features){

        if (!Contract.isNull(mapModuleDocument.getFeaturedPlacesItem())) {
            mapService.addFeaturePlacesNode(module, mapModuleDocument.getCategories(), request.getLocale(), keys, features);
        }
        boolean multipleTaxonomy = false;
        for (String taxonomy : mapModuleDocument.getKeys()) {
            //get all the Taxonomy information
            Taxonomy vsTaxonomyTree = hippoUtilsService.getTaxonomy();
            if (module.getMapType() == null || module.getMapType().isEmpty()) {
                module.setMapType(vsTaxonomyTree.getCategoryByKey(taxonomy).getKey());
            }
             for (Category mainCategory : vsTaxonomyTree.getCategoryByKey(taxonomy).getChildren()) {
                if (!multipleTaxonomy) {
                    keys.add(mapService.addFilterNode(mainCategory, request.getLocale(), false));
                }

                //if the map has 2 levels, the parent won't be a category for the mapcards, so pick sons
                if (!mainCategory.getChildren().isEmpty()) {
                    for(Category child : mainCategory.getChildren()){
                        //find all the documents with a taxonomy/category
                        mapService.addMapDocumentsToJson(request, module,child, features);
                    }
                } else {
                    //find all the documents with a taxonomy/category
                    mapService.addMapDocumentsToJson(request, module, mainCategory, features);
                }
            }

            if (multipleTaxonomy && taxonomy.equalsIgnoreCase("destinations")){
                keys.add(mapService.addFilterNode(vsTaxonomyTree.getCategoryByKey(taxonomy), request.getLocale(),true));
            }
            multipleTaxonomy = true;
        }
    }

    /**
     *
     * Method to build maps for Destination pages based on DMS products and manual and optional featured places
     *
     * @param locale the language
     * @param mapModuleDocument module coming from the CMS
     * @param module module to be sent to feds
     * @param keys filters for maps
     * @param features features information for mapcards
     */
    private void buildDestinationMapPages (Locale locale,Destination destinationPage, MapModule mapModuleDocument, MapsModule module, ArrayNode keys, ArrayNode features){
       if (Contract.isEmpty(module.getTabTitle())){
           String tabTitle = bundle.getResourceBundle(MAP_BUNDLE, "map.explore", locale) + " " + destinationPage.getTitle();
           module.setTabTitle(tabTitle);
       }
        LocationObject location = locationLoader.getLocation(destinationPage.getLocation(),locale);
        JsonNode geometryNode = null;
        //Feature places on top of these maps
        if (!Contract.isNull(mapModuleDocument.getFeaturedPlacesItem())) {
            mapService.addFeaturePlacesNode(module, mapModuleDocument.getCategories(), locale , keys, features);
        }
        if (location == null) {
            logger.error("The locations list is not available and the map cannot be displayed for {}", destinationPage.getPath());
        } else if (destinationPage.getKeys() == null || !Arrays.asList(destinationPage.getKeys()).contains(REGIONS)) {
            //TODO Regions map are based on DMS and are not in use, refactor this code when we know what is happening with Destination(regions) pages
            module.setMapType(MapType.CITIES.getMapType());
            geometryNode = dmsDataService.getLocationBorders(location.getId(),false);
            for (CitiesMapTab prodType : CitiesMapTab.values()) {
                //filters
                ObjectNode filter = mapService.buildCategoryNode(prodType.getProdTypeId(), bundle.getResourceBundle(MAP_BUNDLE, prodType.getLabel(), locale));

                ///endpoint for data (pins)
                ProductSearchBuilder dmsQuery = this.buildProductSearch (destinationPage.getLocation(), prodType.getProdTypeId(), null, locale, null, 24);
                filter.put("pinsEndpoint", dmsQuery.buildDataMap(false));

                //Endpoint base to bring 24 random results
                filter.put("listProductsEndPoint", dmsQuery.buildCannedSearch());

                filter.put("pinClickEndPoint", cmsProperties.getDmsDataPublicHost() + DMSConstants.VS_DMS_PRODUCT_MAP_CARD
                        +"locale="+locale.toLanguageTag()+"&id=");

                //subcategories added
                ArrayNode childrenArray = dmsDataService.getCatGroup(prodType.getProdTypeId(),locale.getLanguage());
                filter.set("subCategory",childrenArray);
                keys.add(filter);
            }
        }else{
            module.setMapType(MapType.REGIONAL.getMapType());
            //for multipolygon regions we need the bounds to get the zoom level.
            geometryNode = dmsDataService.getLocationBorders(location.getId(), !cmsProperties.getMapMultipolygons().contains(location.getId()));

            for (RegionsMapTab regionMap: RegionsMapTab.values()) {
                buildDMSMapPages(regionMap, module, keys, features, locale, destinationPage);
            }
        }
        module.setDetailsEndpoint(cmsProperties.getDmsDataPublicHost() + DMSConstants.VS_DMS_PRODUCT_MAP_CARD+"locale="+locale.toLanguageTag()+"&id=");
        if (geometryNode != null) {
            module.setMapPosition((ObjectNode) geometryNode);
        }else{
            module.setMapPosition(mapper.createObjectNode());
        }
    }

    private ObjectNode addFilters (String prodTypeId, String prodTypelabel){
        return  mapService.buildCategoryNode(prodTypeId, prodTypelabel);
    }


    private void buildDMSMapPages (BespokeDmsMap bespokeMap, MapsModule module, ArrayNode keys, ArrayNode features, Locale locale, Destination destinationPage) {
        String label = !Contract.isNull(bundle.getResourceBundle(MAP_BUNDLE,bespokeMap.getLabel(),locale))?
                bundle.getResourceBundle(MAP_BUNDLE,bespokeMap.getLabel(),locale):locationLoader.getLocation(bespokeMap.getLocation(), locale).getName();
        ObjectNode regionFilters = this.addFilters(bespokeMap.getCategory(), label);
        keys.add(regionFilters);
        this.addDmsData(this.buildProductSearch(destinationPage != null? destinationPage.getLocation():bespokeMap.getLocation(), bespokeMap.getProdTypeId(), bespokeMap.getDmsCategory(), locale, DMSConstants.SORT_ALPHA, 100),
                module, regionFilters, features, locale);
    }

    private void addDmsData (ProductSearchBuilder dmsQuery, MapsModule module, ObjectNode filter, ArrayNode features, Locale locale){
        JsonNode dmsResponseData =  dmsDataService.cannedSearch(dmsQuery.buildCannedSearchInternal());
        if (dmsResponseData != null && !dmsResponseData.isEmpty()) {
            for (JsonNode jsonNode : dmsResponseData) {
                FlatImage image = imageMapper.createImage(jsonNode, module,locale);
                FlatLink link = new FlatLink(bundle.getResourceBundle(MAP_BUNDLE, DISCOVER, locale), jsonNode.get("productLink").get("link").asText(), LinkType.EXTERNAL);
                ObjectNode data = mapper.createObjectNode();
                String name = jsonNode.has(NAME) ? jsonNode.get(NAME).asText() : null;
                String description = jsonNode.has(DESCRIPTION) ? jsonNode.get(DESCRIPTION).asText() : null;
                String id = jsonNode.has(ID) ? jsonNode.get(ID).asText() : null;
                ObjectNode properties = mapService.getPropertyNode(name, description, image, filter, link, id);
                properties.put(ID, id);
                properties.put(TITLE, name);
                properties.put(DESCRIPTION, description);
                data.set(PROPERTIES, properties );
                data.set(GEOMETRY, mapService.getGeometryNode(mapService.getCoordinates(jsonNode.get("longitude").asDouble(), jsonNode.get("latitude").asDouble()), POINT));

                features.add(data);
            }
        }
    }

    private ProductSearchBuilder buildProductSearch (String location, String prodType, String category, Locale locale, String order, int size){
        return VsComponentManager.get(ProductSearchBuilder.class).location(location).productTypes(prodType).category(category).sortBy(order).size(size).locale(locale);
    }

    private BespokeDmsMap[] getValues(String mapType){
        if (mapType.equals("DistilleriesMap")) {
            return DistilleryMapTab.values();
        }
        return new BespokeDmsMap[0];
    }
}

