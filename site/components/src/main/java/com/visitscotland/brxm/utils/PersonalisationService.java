package com.visitscotland.brxm.utils;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.query.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.visitscotland.brxm.model.Persona;

@Component
public class PersonalisationService {

    private static final Logger logger = LoggerFactory.getLogger(PersonalisationService.class);

    @SuppressWarnings("deprecation")
    public HippoBean getPersonalisedVariant(final HstRequestContext request, final HippoBean hippoBean){
        logger.debug("Attempting to find a personalisation variant for the document : {}", hippoBean.getPath());
        final Persona persona = (Persona) request.getAttribute("persona");

        //If there is no persona immediately return default bean
        if (persona != null) {
            try {
                final Node beanHandleNode = hippoBean.getNode();
                final NodeIterator nodeIterator = beanHandleNode.getParent().getNodes(hippoBean.getName() + "*");
                //If nodeIterator size is 0 it means there are no configured content variants return the default bean
                if (nodeIterator.getSize() > 0) {
                    //there are variants we can perform a query
                    final String folderPath = beanHandleNode.getParent().getParent().getPath().substring(1);
                    final String xpathQ = folderPath + "/*/*[jcr:like(@jcr:name, '"+beanHandleNode.getName()+"%')]/element(*, visitscotland:POCnalisation)";
                    final Query variantsQuery = beanHandleNode
                            .getSession()
                            .getWorkspace()
                            .getQueryManager()
                            .createQuery(constructQueryFilters(persona, xpathQ), Query.XPATH);
                    variantsQuery.setLimit(1);
                    final NodeIterator variantsNI = variantsQuery.execute().getNodes();
                    if(variantsNI.hasNext()) {
                        final Node compoundPOCnalisationNode = variantsNI.nextNode();
                        if(compoundPOCnalisationNode.getParent().getName().startsWith(beanHandleNode.getName())) {
                            return (HippoBean) request.getObjectConverter().getObject(compoundPOCnalisationNode.getParent());
                        }
                    }
                }
            } catch (RepositoryException e) {
                logger.error("A repository exception occurred while attempting to match a personalisation variant for {}.", hippoBean.getPath(), e);
            } catch (ObjectBeanManagerException e) {
                logger.error("An ObjectManager exception occurred while trying to get a HippoBean for the personalisation variant.", e);
            }
        }
        return hippoBean;
    }

    private String constructQueryFilters(final Persona persona, final String xpathQ) {
        final String country = persona.getCountry();
        String countryFilter = null;
        if (StringUtils.isNotEmpty(country)){
            countryFilter = "jcr:contains(@visitscotland:country, '"+country+"')";
        }

        final String[] interests = persona.getInterests();
        String interestsFilter = null;
        if (ArrayUtils.isNotEmpty(interests)){
            final StringBuilder interestsFilterBuilder = new StringBuilder();

            for (int i = 0; i < interests.length; i++) {
                final String interest = interests[i];
                interestsFilterBuilder.append("jcr:contains(@visitscotland:interests, '").append(interest).append("')");

                // Append ' or ' only if it's not the last element
                if (i < interests.length - 1) {
                    interestsFilterBuilder.append(" or ");
                }
            }

            interestsFilter = interestsFilterBuilder.toString();
        }

        final String[] businessSectors = persona.getSectors();
        String businessSectorsFilter = null;
        if (ArrayUtils.isNotEmpty(businessSectors)){
            final StringBuilder businessSectorsFilterBuilder = new StringBuilder();

            for (int i = 0; i < businessSectors.length; i++) {
                final String interest = businessSectors[i];
                businessSectorsFilterBuilder.append("jcr:contains(@visitscotland:sectors, '").append(interest).append("')");

                // Append ' or ' only if it's not the last element
                if (i < businessSectors.length - 1) {
                    businessSectorsFilterBuilder.append(" or ");
                }
            }

            businessSectorsFilter = businessSectorsFilterBuilder.toString();
        }

        if (StringUtils.isNotEmpty(countryFilter) || StringUtils.isNotEmpty(interestsFilter) || StringUtils.isNotEmpty(businessSectorsFilter)){
            final StringBuilder combinedFilterBuilder = new StringBuilder();

            // Append non-empty filters with " or " in between
            if (StringUtils.isNotEmpty(countryFilter)) {
                combinedFilterBuilder.append(countryFilter);
            }
            if (StringUtils.isNotEmpty(interestsFilter)) {
                if (combinedFilterBuilder.length() > 0) {
                    combinedFilterBuilder.append(" or ");
                }
                combinedFilterBuilder.append(interestsFilter);
            }
            if (StringUtils.isNotEmpty(businessSectorsFilter)) {
                if (combinedFilterBuilder.length() > 0) {
                    combinedFilterBuilder.append(" or ");
                }
                combinedFilterBuilder.append(businessSectorsFilter);
            }

            final String combinedFilter = combinedFilterBuilder.toString();
            if (StringUtils.isNotEmpty(combinedFilter)) {
                return xpathQ + "[" + combinedFilter + "]";
            }
        }

        return xpathQ;
    }

    public boolean isPersonalisationVariant(final Node handleNode) throws RepositoryException {
        final Node variantNode = handleNode.getNode(handleNode.getName());
        if(variantNode.hasNode("visitscotland:POCnalisation")){
            final Node personalisationCompoundNode = variantNode.getNode("visitscotland:POCnalisation");
            if(personalisationCompoundNode.hasProperty("visitscotland:isVariant")){
                return personalisationCompoundNode.getProperty("visitscotland:isVariant").getBoolean();
            }
        }
        return false;
    }

}
