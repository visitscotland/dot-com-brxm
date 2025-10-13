package com.visitscotland.brxm.comparator;

import com.visitscotland.brxm.comparator.model.ComparatorModule;
import com.visitscotland.brxm.comparator.model.Feature;
import com.visitscotland.brxm.comparator.model.Provider;
import com.visitscotland.brxm.hippobeans.ComparatorFeature;
import com.visitscotland.brxm.hippobeans.ComparatorSystem;
import com.visitscotland.brxm.hippobeans.DevModule;
import com.visitscotland.brxm.services.HippoUtilsService;
import com.visitscotland.utils.Contract;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.onehippo.forge.selection.hst.contentbean.ValueListItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper service for converting Hippo CMS documents into comparator module data structures.
 * Handles the mapping of features and providers for the comparator functionality.
 */
@Component
public class ComparatorMapper {

    private static final Logger logger = LoggerFactory.getLogger(ComparatorMapper.class);
    public static final String BSH_OBS_FEATURES = "bsh-obs-features";
    public static final String BSH_OBS_GROUPS = "bsh-obs-groups";



    private final HippoQueryService queryService;
    private final HippoUtilsService hippoUtilsService;

    public ComparatorMapper(HippoQueryService queryService, HippoUtilsService hippoUtilsService) {
        this.queryService = queryService;
        this.hippoUtilsService = hippoUtilsService;
    }

    /**
     * Maps a DevModule document to a ComparatorModule with features and providers.
     *
     * @param document The DevModule document to map
     * @return ComparatorModule with populated features and providers
     * @throws VsContractException If contract validation fails
     * @throws BrxmWrapperException If repository access fails
     */
    public ComparatorModule map(DevModule document) throws VsContractException, BrxmWrapperException {
        ComparatorModule module = new ComparatorModule(document);
        module.setFeatures(getFeatures());
        module.setProviders(getProviders());
        module.setProperties(document.getBespokenProperties());
        module.setSubmitUrl(getSubmitURL(document.getBespokenProperties()));

        return module;
    }

    /**
     *  This was a productionized PoC. In future Implementations it's better just to return the properties
     *
     *  TODO: The front-end should consume properties instead of submitUrl
     *  TODO: Remove submitUrl property from ComparatorModule
     *
     * @deprecated productionized PoC. Remove method once the front-end is consuming properties
     */
    @Deprecated(forRemoval = true)
    private String getSubmitURL(List<ValueListItem> properties) {
        String submitURL = "";
        if (properties != null) {
            for (ValueListItem item: properties) {
                if ("submitUrl".equals(item.getKey())) {
                    submitURL = item.getLabel();
                    break;
                }
            }
        }

        if (Contract.isEmpty(submitURL)) {
            logger.error("No submit URL was provided for the Online Booking Provider");
        }

        return submitURL;
    }

    /**
     * Retrieves all system providers for the comparator.
     *
     * @return List of Provider objects
     * @throws BrxmWrapperException If repository access fails
     * @throws VsContractException If contract validation fails
     */
    List<Provider> getProviders() throws BrxmWrapperException, VsContractException {
        List<Provider> entries = new ArrayList<>();
        try {
            HippoBeanIterator iterator = queryService.findAll(ComparatorSystem.class);

            while (iterator.hasNext()){
                ComparatorSystem hippoBean = (ComparatorSystem) iterator.nextHippoBean();
                entries.add(new Provider(hippoBean));
            }

            return entries;
        } catch (RepositoryException | QueryException e) {
            logger.error(e.getMessage(), e);
            throw new BrxmWrapperException(e);
        }
    }

    /**
     * Retrieves all features for the comparator.</br>
     *
     * This features depend on document types to be created if they are not they will still appear on the list
     * but with less information.
     *
     * @return List of Feature objects
     * @throws BrxmWrapperException If repository access fails
     * @throws VsContractException If contract validation fails
     */
    List<Feature> getFeatures() throws BrxmWrapperException, VsContractException {
        List<Feature> entries = new ArrayList<>();
        Map<String, String> valueList = hippoUtilsService.getValueMap(BSH_OBS_FEATURES);
        Map<String, Feature> features = getFeaturesMap();

        for (Map.Entry<String, String> entry : valueList.entrySet()) {
            if (features.containsKey(entry.getKey())) {
                entries.add(features.get(entry.getKey()));
            } else {
                entries.add(new Feature(entry.getKey(), entry.getValue()));
                logger.warn("Feature {} is not defined", entry.getKey());
            }
        }

        return entries;
    }

    /**
     * Creates a map of feature IDs to Feature objects from repository data.
     *
     * @return Map with feature IDs as keys and Feature objects as values
     * @throws BrxmWrapperException If repository access fails
     * @throws VsContractException If contract validation fails
     */
    Map<String, Feature> getFeaturesMap() throws BrxmWrapperException, VsContractException {
        Map<String, Feature> map = new HashMap<>();

        Map<String, String> featureList = hippoUtilsService.getValueMap(BSH_OBS_FEATURES);
        Map<String, String> groupList = hippoUtilsService.getValueMap(BSH_OBS_GROUPS);

        try {
            HippoBeanIterator iterator = queryService.findAll(ComparatorFeature.class);

            while (iterator.hasNext()){
                var document = (ComparatorFeature) iterator.nextHippoBean();
                map.put(document.getId(), new Feature(document, featureList.get(document.getId()), groupList.get(document.getCategory())));
            }

            return map;

        } catch (RepositoryException | QueryException e) {
            throw new BrxmWrapperException(e);
        }
    }
}
