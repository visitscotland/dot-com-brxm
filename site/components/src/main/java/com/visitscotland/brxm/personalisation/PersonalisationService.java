package com.visitscotland.brxm.personalisation;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.visitscotland.brxm.hippobeans.Variant;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Objects;
import java.util.List;

import static com.visitscotland.brxm.hippobeans.Variant.VARIANT_JCR_TYPE;

@Component
public class PersonalisationService {
    public List<HippoBean> getPersonalisedVariants(HippoBean hippoBean) {
        final List<Variant> variants = hippoBean
            .getChildBeansByName(VARIANT_JCR_TYPE, Variant.class);

        return variants
            .stream()
            .filter(this::variantMatchesCountry)
            .map(Variant::getParentBean)
            .filter(Objects::nonNull)
            .collect(Collectors.toUnmodifiableList());
    }

    public boolean isVariant(HippoBean hippoBean) {
        final Optional<Variant> variant = Optional.ofNullable(hippoBean
            .getBean(VARIANT_JCR_TYPE, Variant.class));

        return variant.isPresent() ? variant.get().getIsVariant() : false;
    }

    private boolean variantMatchesCountry(Variant variant) {
        return getVisitorContextFromRequest()
            .filter(visitorContext -> visitorContext.getCountry().equals(variant.getCountry()))
            .isPresent();
    }

    private Optional<VisitorContext> getVisitorContextFromRequest() {
        final HstRequestContext requestContext = RequestContextProvider.get();
        return Optional.ofNullable((VisitorContext) requestContext.getAttribute("visitorContext"));
    }
}