package com.visitscotland.brxm.personalisation;

import com.visitscotland.brxm.hippobeans.Personalization;

import org.hippoecm.hst.mock.content.beans.standard.MockHippoBean;
import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonalisationServiceTest {
    @Mock private MockHippoBean hippoBean;
    @Mock private MockHstRequestContext requestContext;

    private final PersonalisationService personalisationService;

    private static final String PERSONALIZATION_JCR_TYPE = "visitscotland:Personalization";
    private static final String VISITOR_CONTEXT_ATTRIBUTE = "visitorContext";
    private static final String UNITED_STATES = "US";
    private static final String UNITED_KINGDOM = "UK";

    private static final VisitorContext VISITOR_CONTEXT_UK = new VisitorContext(UNITED_KINGDOM);

    public PersonalisationServiceTest() {
        this.personalisationService = new PersonalisationService();
    }

    @Test
    void When_GetPersonalisedVariants_With_NoPersonalizationCompounds_Expect_EmptyList() {
        final List<Personalization> personalizationCompounds = Collections.emptyList();

        when(hippoBean.getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class))
            .thenReturn(personalizationCompounds);

        final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

        verify(hippoBean, times(1)).getChildBeansByName(
            PERSONALIZATION_JCR_TYPE,
            Personalization.class
        );

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void When_GetPersonalisedVariants_With_PersonalizationCompoundsNotMatchingVisitorContext_Expect_EmptyList() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Personalization personalisedVariant = mock(Personalization.class);
            final List<Personalization> personalizationCompounds = List.of(personalisedVariant);

            requestContextProviderMock
                .when(RequestContextProvider::get)
                .thenReturn(requestContext);
            when(personalisedVariant.getCountry())
                .thenReturn(UNITED_STATES);
            when(hippoBean.getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class))
                .thenReturn(personalizationCompounds);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE)))
                .thenReturn(VISITOR_CONTEXT_UK);

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            Assertions.assertTrue(result.isEmpty());
        }
    }

    @Test
    void When_GetPersonalisedVariants_With_PersonalizationCompoundsMatchingVisitorContext_Expect_ListOfReferencedHippoBeans() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Personalization personalisedVariant = mock(Personalization.class);
            final List<Personalization> personalizationCompounds = List.of(personalisedVariant);

            requestContextProviderMock
                .when(RequestContextProvider::get)
                .thenReturn(requestContext);
            when(personalisedVariant.getCountry())
                .thenReturn(UNITED_KINGDOM);
            when(personalisedVariant.getModule())
                .thenReturn(new MockHippoBean());
            when(hippoBean.getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class))
                .thenReturn(personalizationCompounds);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE)))
                .thenReturn(VISITOR_CONTEXT_UK);

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            verify(personalisedVariant, times(1)).getModule();
            Assertions.assertEquals(1, result.size());
        }
    }

    @Test
    void When_GetPersonalisedVariants_With_NoVisitorContextPresent_Expect_EmptyList() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Personalization personalisedVariant = mock(Personalization.class);
            final List<Personalization> personalizationCompounds = List.of(personalisedVariant);

            requestContextProviderMock
                .when(RequestContextProvider::get)
                .thenReturn(requestContext);
            when(hippoBean.getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class))
                .thenReturn(personalizationCompounds);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE)))
                .thenReturn(null);

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            Assertions.assertTrue(result.isEmpty());
        }
    }

    @Test
    void When_GetPersonalisedVariants_With_NoModulePresent_Expect_EmptyList() {
        try (MockedStatic<RequestContextProvider> requestContextProviderMock = mockStatic(RequestContextProvider.class)) {
            final Personalization personalisedVariant = mock(Personalization.class);
            final List<Personalization> personalizationCompounds = List.of(personalisedVariant);

            requestContextProviderMock
                .when(RequestContextProvider::get)
                .thenReturn(requestContext);
            when(personalisedVariant.getCountry())
                .thenReturn(UNITED_KINGDOM);
            when(personalisedVariant.getModule())
                .thenReturn(null);
            when(hippoBean.getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class))
                .thenReturn(personalizationCompounds);
            when(requestContext.getAttribute(eq(VISITOR_CONTEXT_ATTRIBUTE)))
                .thenReturn(VISITOR_CONTEXT_UK);

            final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

            Assertions.assertTrue(result.isEmpty());
        }
    }
}