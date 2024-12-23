package com.visitscotland.brxm.personalisation;

import com.visitscotland.brxm.hippobeans.Variant;

import org.hippoecm.hst.mock.content.beans.standard.MockHippoBean;
import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
class PersonalisationServiceTest {
    @Mock private MockHippoBean hippoBean;
    @Mock private MockHstRequestContext requestContext;

    private final PersonalisationService personalisationService;

    private static final String VISITOR_CONTEXT_ATTRIBUTE = "visitorContext";
    private static final String VARIANT_JCR_TYPE = "visitscotland:Variant";
    private static final String UNITED_KINGDOM = "UK";
    private static final String UNITED_STATES = "US";

    private static final VisitorContext VISITOR_CONTEXT_UK = new VisitorContext(UNITED_KINGDOM);

    public PersonalisationServiceTest() {
        this.personalisationService = new PersonalisationService();
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void When_IsVariantCalled_With_HippoBean_Expect_ResultMatchesVariantState(boolean isVariant) {
        final Variant variant = mock(Variant.class);

        when(hippoBean.getBean(VARIANT_JCR_TYPE, Variant.class)).thenReturn(variant);
        when(variant.getIsVariant()).thenReturn(isVariant);

        final boolean result = personalisationService.isVariant(hippoBean);

        verify(hippoBean, times(1)).getBean(VARIANT_JCR_TYPE, Variant.class);
        Assertions.assertEquals(isVariant, result);
    }

    @Test
    void When_GetPersonalisedVariants_With_NoVariantCompounds_Expect_EmptyList() {
        when(hippoBean.getChildBeansByName(VARIANT_JCR_TYPE, Variant.class))
            .thenReturn(Collections.emptyList());

        final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

        verify(hippoBean, times(1))
            .getChildBeansByName(VARIANT_JCR_TYPE, Variant.class);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void When_GetPersonalisedVariants_With_VariantMatchingVisitorContext_Expect_ListOfParentHippoBeans() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Variant variant = mock(Variant.class);
            final HippoBean parentBean = mock(HippoBean.class);

            requestContextProviderMock.when(RequestContextProvider::get).thenReturn(requestContext);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE))).thenReturn(VISITOR_CONTEXT_UK);
            when(hippoBean.getChildBeansByName(VARIANT_JCR_TYPE, Variant.class)).thenReturn(List.of(variant));
            when(variant.getCountry()).thenReturn(UNITED_KINGDOM);
            when(variant.getParentBean()).thenReturn(parentBean);

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            Assertions.assertEquals(1, result.size());
            Assertions.assertEquals(parentBean, result.get(0));
        }
    }

    @Test
    void When_GetPersonalisedVariants_With_VariantNotMatchingVisitorContext_Expect_EmptyList() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Variant variant = mock(Variant.class);

            requestContextProviderMock.when(RequestContextProvider::get).thenReturn(requestContext);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE))).thenReturn(VISITOR_CONTEXT_UK);
            when(hippoBean.getChildBeansByName(VARIANT_JCR_TYPE, Variant.class)).thenReturn(List.of(variant));
            when(variant.getCountry()).thenReturn(UNITED_STATES);

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            Assertions.assertTrue(result.isEmpty());
        }
    }

    @Test
    void When_GetPersonalisedVariants_With_NoVisitorContext_Expect_EmptyList() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Variant variant = mock(Variant.class);

            requestContextProviderMock.when(RequestContextProvider::get).thenReturn(requestContext);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE))).thenReturn(null);
            when(hippoBean.getChildBeansByName(VARIANT_JCR_TYPE, Variant.class)).thenReturn(List.of(variant));

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            Assertions.assertTrue(result.isEmpty());
        }
    }
}