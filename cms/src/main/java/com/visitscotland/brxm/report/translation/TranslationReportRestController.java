package com.visitscotland.brxm.report.translation;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.visitscotland.brxm.report.ReportException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestScope
@Tag(name = "Translation reporting", description = "APIs for translation reporting and translation metadata")
@SecurityRequirement(name = "basicAuth")
public class TranslationReportRestController {


    private final TranslationReportService translationReportService;

    @Autowired
    public TranslationReportRestController(TranslationReportService translationReportService) {
        this.translationReportService = translationReportService;
    }

    @GetMapping("/translation/untranslated")
    @Operation(summary = "List untranslated documents", description = "Returns untranslated documents, optionally filtered by locale.")
    @ApiResponse(responseCode = "200", description = "Untranslated document list returned")
    public RestListContainer<DocumentTranslationReportModel> untranslatedFiles(
            @Parameter(description = "Locale code to filter by") @RequestParam(required = false) String locale) {
        if (locale != null && !translationReportService.isLocaleSupported(locale)) {
            return new RestListContainer<>(Collections.emptyList());
        }
        return new RestListContainer<>(translationReportService.getUntranslatedDocuments(locale));
    }

    @PostMapping("/translation/{handleId}/priority")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Set translation priority", description = "Sets translation priority for the supplied document handle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Priority updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public void setTranslationPriority(@Parameter(description = "JCR handle UUID for the document") @PathVariable String handleId,
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON payload containing priority", required = true) @RequestBody Map<String, Object> requestBody) {
        Object priorityString = requestBody.get("priority");
        if (priorityString != null) {
            try {
                translationReportService.setTranslationPriority(handleId, TranslationPriority.valueOf(priorityString.toString()));
            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid priority");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No priority provided");
        }
    }

    @PostMapping("/translation/{handleId}/deadline")
    @Operation(summary = "Set translation deadline", description = "Sets translation deadline for the supplied document handle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deadline updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public void setTranslationDeadline(@Parameter(description = "JCR handle UUID for the document") @PathVariable String handleId,
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON payload containing deadline", required = true) @RequestBody Map<String, Object> requestBody) {
        Object deadlineString = requestBody.get("deadline");
        if (!(deadlineString instanceof String)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No deadline provided");
        }
        try {
            Calendar deadline = Calendar.getInstance();
            deadline.setTime(new StdDateFormat().parse((String)deadlineString));
            translationReportService.setTranslationDeadline(handleId, deadline);
        } catch (ParseException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid deadline");
        }
    }

    @GetMapping("/translation/priority")
    @Operation(summary = "List translation priorities", description = "Returns all supported translation priority options.")
    @ApiResponse(responseCode = "200", description = "Priority options returned")
    public List<List<Object>> getTranslationPriorityOptions() {
        return Arrays.stream(TranslationPriority.values()).map(priority -> {
            return Arrays.asList(priority.toString(),(Object)priority.name,  priority.sortOrder);
        }).collect(Collectors.toList());
    }

    @GetMapping("/translation/status")
    @Operation(summary = "List translation statuses", description = "Returns all supported translation status options.")
    @ApiResponse(responseCode = "200", description = "Status options returned")
    public Set<String> getTranslationStatusOptions() {
        return Arrays.stream(TranslationStatus.values()).map(TranslationStatus::toString).collect(Collectors.toSet());
    }

    @GetMapping("/translation/pages")
    @Operation(summary = "List page types", description = "Returns all page types supported by translation reporting.")
    @ApiResponse(responseCode = "200", description = "Page types returned")
    public Set<String> getPageTypes() {
        return translationReportService.getPageTypes();
    }

    @GetMapping("/translation/modules")
    @Operation(summary = "List module types", description = "Returns all module types supported by translation reporting.")
    @ApiResponse(responseCode = "200", description = "Module types returned")
    public Set<String> getModuleTypes() {
        return translationReportService.getModuleTypes();
    }

    @ExceptionHandler(ReportException.class)
    public void translationReportExceptionHandler(ReportException ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }


}
