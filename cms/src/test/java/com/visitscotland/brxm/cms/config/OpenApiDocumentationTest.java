package com.visitscotland.brxm.cms.config;

import com.visitscotland.brxm.report.translation.TranslationReportRestController;
import com.visitscotland.brxm.translation.TranslationRestService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenApiDocumentationTest {

    @Test
    public void openApiDefinition_shouldBePresent() {
        OpenAPIDefinition openApiDefinition = OpenApiConfiguration.class.getAnnotation(OpenAPIDefinition.class);

        assertThat(openApiDefinition).isNotNull();
        assertThat(openApiDefinition.info().title()).isEqualTo("VisitScotland CMS Web APIs");
        assertThat(openApiDefinition.info().version()).isEqualTo("v1");
    }

    @Test
    public void translationRestServiceEndpoints_shouldBeDocumented() throws Exception {
        assertDocumented(TranslationRestService.class, "getNodeDifference", String.class);
        assertDocumented(TranslationRestService.class, "setTranslationFlag", String.class, String.class);
        assertDocumented(TranslationRestService.class, "deleteTranslationFlag", String.class);
    }

    @Test
    public void translationReportRestControllerEndpoints_shouldBeDocumented() throws Exception {
        assertDocumented(TranslationReportRestController.class, "untranslatedFiles", String.class);
        assertDocumented(TranslationReportRestController.class, "setTranslationPriority", String.class, Map.class);
        assertDocumented(TranslationReportRestController.class, "setTranslationDeadline", String.class, Map.class);
        assertDocumented(TranslationReportRestController.class, "getTranslationPriorityOptions");
        assertDocumented(TranslationReportRestController.class, "getTranslationStatusOptions");
        assertDocumented(TranslationReportRestController.class, "getPageTypes");
        assertDocumented(TranslationReportRestController.class, "getModuleTypes");
    }

    private void assertDocumented(Class<?> type, String methodName, Class<?>... parameterTypes) throws Exception {
        Method method = type.getDeclaredMethod(methodName, parameterTypes);
        assertThat(method.getAnnotation(Operation.class)).isNotNull();
        ApiResponses responses = method.getAnnotation(ApiResponses.class);
        ApiResponse response = method.getAnnotation(ApiResponse.class);
        assertThat(responses != null || response != null).isTrue();
        if (responses != null) {
            assertThat(responses.value()).isNotEmpty();
        }
    }
}
