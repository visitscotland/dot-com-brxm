package com.visitscotland.brxm.components;

import com.visitscotland.brxm.config.VsComponentManager;
import com.visitscotland.brxm.dms.LocationLoader;
import com.visitscotland.brxm.dms.model.LocationObject;
import com.visitscotland.brxm.model.ValueListLocation;
import org.onehippo.forge.selection.frontend.model.ListItem;
import org.onehippo.forge.selection.frontend.model.ValueList;
import org.onehippo.forge.selection.frontend.provider.ValueListProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import java.util.List;
import java.util.Locale;

/**
 * /cms/console/?1&path=/hippo:configuration/hippo:frontend/cms/cms-services/DmsImageLocationListProvider
 */
public class DmsLocationListProvider implements ValueListProvider {

    private static final Logger logger = LoggerFactory.getLogger(DmsLocationListProvider.class);

    private static final String LEVELS_PROPERTY = "location.levels";

    private String[] levels;

    @Override
    public void configure(final Node providerNode) throws RepositoryException {
        logger.info("Configuring DMS location list provider");

        if (providerNode.hasProperty(LEVELS_PROPERTY)) {
            Value[] values = providerNode.getProperty(LEVELS_PROPERTY).getValues();
            levels  = new String[values.length];

            for (int i = 0; i < values.length; i++) {
                levels[i] = values[i].getString();
            }
        } else {
            levels = new String[0];
        }

    }

    private LocationLoader getLocationLoader(){
        return VsComponentManager.get(LocationLoader.class);
    }

    @Override
    public ValueList getValueList(String s, Locale locale, Session session) {
        List<LocationObject> locations = getLocationLoader().getLocationsByLevel(levels);
        ValueListLocation valueList = new ValueListLocation();

        for (LocationObject location: locations) {
            ListItem list = new ListItem(location.getName(),location.getName());
            valueList.add(list);
        }
        return valueList;
    }
}



