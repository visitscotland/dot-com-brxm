package com.visitscotland.brxm.personalisation;

import com.visitscotland.brxm.hippobeans.Personalization;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

@Component
public class PersonalisationService {
    private static final String PERSONALIZATION_JCR_TYPE = "visitscotland:personalization";

    public List<HippoBean> getPersonalisedVariants(HippoBean hippoBean) {
        final List<Personalization> personalisationCompounds = hippoBean
            .getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class);

        return personalisationCompounds
            .stream()
            .filter(this::matchesVisitorContextCountry)
            .collect(Collectors.toUnmodifiableList());
    }

    private boolean matchesVisitorContextCountry(Personalization personalization) {
        final Optional<VisitorContext> visitorContext = getVisitorContextFromRequest();
        return visitorContext.isPresent() && personalization.getCountry().equals(visitorContext.get().getCountry());
    }

    private Optional<VisitorContext> getVisitorContextFromRequest() {
        final HstRequestContext requestContext = RequestContextProvider.get();
        return Optional.ofNullable((VisitorContext) requestContext.getAttribute("visitorContext"));
    }
}