package com.visitscotland.brxm.personalisation;

import com.visitscotland.brxm.hippobeans.Personalization;

import org.hippoecm.hst.mock.content.beans.standard.MockHippoBean;
import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonalisationServiceTest {
    @Mock private MockHippoBean hippoBean;
    @Mock private MockHstRequestContext requestContext;

    private final PersonalisationService personalisationService;

    private static final String PERSONALIZATION_JCR_TYPE = "visitscotland:personalization";

    @BeforeEach
    void setUp() {
        try (MockedStatic<RequestContextProvider> mockedStatic = mockStatic(RequestContextProvider.class)) {
            mockedStatic
                .when(RequestContextProvider::get)
                .thenReturn(requestContext);
        }
    }

    public PersonalisationServiceTest() {
        this.personalisationService = new PersonalisationService();
    }

    @Test
    void When_GetPersonalisedVariants_WithHippoBeanWithNoPersonalizationCompounds_Expect_EmptyList() {
        when(hippoBean.getChildBeansByName(PERSONALIZATION_JCR_TYPE, Personalization.class))
            .thenReturn(Collections.emptyList());

        final List<HippoBean> result = personalisationService.getPersonalisedVariants(hippoBean);

        Assertions.assertEquals(0, result.size());
    }
}