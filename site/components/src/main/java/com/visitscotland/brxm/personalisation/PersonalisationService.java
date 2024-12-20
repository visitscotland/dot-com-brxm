package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import com.visitscotland.brxm.hippobeans.Variant;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

import static com.visitscotland.brxm.hippobeans.Variant.VARIANT_JCR_TYPE;

@Component
public class PersonalisationService {
    public List<HippoBean> getPersonalisedVariants(HippoBean hippoBean) {
        final List<Variant> variantCompounds = hippoBean
            .getChildBeansByName(VARIANT_JCR_TYPE, Variant.class);

        return variantCompounds
            .stream()
            .filter(this::matchesVisitorContextCountry)
            .map(HippoBean::getParentBean)
            .collect(Collectors.toUnmodifiableList());
    }

    public boolean isVariant(HippoBean hippoBean) {
        return hippoBean
            .getBean(VARIANT_JCR_TYPE, Variant.class)
            .getIsVariant();
    }

    private boolean matchesVisitorContextCountry(Variant variant) {
        final Optional<VisitorContext> visitorContext = getVisitorContextFromRequest();
        return visitorContext.isPresent() && variant.getCountry().equals(visitorContext.get().getCountry());
    }

    private Optional<VisitorContext> getVisitorContextFromRequest() {
        final HstRequestContext requestContext = RequestContextProvider.get();
        return Optional.ofNullable((VisitorContext) requestContext.getAttribute("visitorContext"));
    }
}