package com.visitscotland.brxm.config;

import com.visitscotland.brxm.model.FlatLink;
import com.visitscotland.brxm.model.LinkType;
import com.visitscotland.brxm.services.LinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HTMLtoVueTransformerTest {

    @Mock
    LinkService linkService;

    @InjectMocks
    HTMLtoVueTransformer transformer;

    @ParameterizedTest
    @DisplayName("VS-2383 - Internal links return type default")
    @CsvSource({"INTERNAL,default", "EXTERNAL,external", "DOWNLOAD,download"})
    void links_external(LinkType type, String output) {
        final String HTML = "<p>Take a look at the " +
                "<a href=\"https://community.visitscotland.com/\" title=\"iKnow Community\" target=\"_blank\">iKnow Scotland Community</a>" +
                " to share your tips and pick up a couple more for your next break or day out in Scotland.</p>";

        when(linkService.createExternalLink("https://community.visitscotland.com/", null))
                .thenReturn(new FlatLink(null, "https://community.visitscotland.com/", type));

        String result = transformer.processLinks(HTML, null);

        Assertions.assertTrue(result.contains("<vs-link "));
        Assertions.assertTrue(result.contains(" type=\"" + output + "\""));
    }

    @Test
    @DisplayName("Identifies multiples a tags")
    void links_multiple() {
        final String HTML = "<p>Random Links useful for this test" +
                "<a href=\"https://www.gov.scot/\">Link 1</a>, " +
                "<a href=\"https://www.gov.scot/coronavirus-covid-19/\">Link 2</a> & " +
                "<a href=\"https://protect.scot/\">Link 3</a> </p>";

        when(linkService.createExternalLink(Mockito.any(), any()))
                .thenReturn(new FlatLink(null, "", LinkType.EXTERNAL));

        String result = transformer.processLinks(HTML, null);
        Matcher matcher = Pattern.compile("<vs-link ").matcher(result);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        assertEquals(3, count);
    }

    // New tests: VS - consecutive internal and external links

    @Test
    @DisplayName("Internal and external links are transformed independently")
    void links_internalAndExternal() {
        final String HTML =
                "<p>" +
                        "<a href=\"/internal\">Internal link</a>, " +
                        "<a href=\"https://external.com\">External link</a>" +
                        "</p>";

        when(linkService.createExternalLink("/internal", null))
                .thenReturn(new FlatLink(null, "/internal", LinkType.INTERNAL));

        when(linkService.createExternalLink("https://external.com", null))
                .thenReturn(new FlatLink(null, "https://external.com", LinkType.EXTERNAL));

        String result = transformer.processLinks(HTML, null);

        assertTrue(result.contains(
                "<vs-link type=\"default\" href=\"/internal\">Internal link</vs-link>"
        ));

        assertTrue(result.contains(
                "<vs-link type=\"external\" href=\"https://external.com\">External link</vs-link>"
        ));

        verify(linkService).createExternalLink("/internal", null);
        verify(linkService).createExternalLink("https://external.com", null);
    }

    @Test
    @DisplayName("External and internal links are transformed independently")
    void links_externalAndInternal() {
        final String HTML =
                "<p>" +
                        "<a href=\"https://external.com\">External link</a>, " +
                        "<a href=\"/internal\">Internal link</a>" +
                        "</p>";

        when(linkService.createExternalLink("https://external.com", null))
                .thenReturn(new FlatLink(null, "https://external.com", LinkType.EXTERNAL));

        when(linkService.createExternalLink("/internal", null))
                .thenReturn(new FlatLink(null, "/internal", LinkType.INTERNAL));

        String result = transformer.processLinks(HTML, null);

        assertTrue(result.contains(
                "<vs-link type=\"external\" href=\"https://external.com\">External link</vs-link>"
        ));

        assertTrue(result.contains(
                "<vs-link type=\"default\" href=\"/internal\">Internal link</vs-link>"
        ));

        verify(linkService).createExternalLink("https://external.com", null);
        verify(linkService).createExternalLink("/internal", null);
    }

    @Test
    @DisplayName("Multiple alternating internal and external links retain their types")
    void links_multipleAlternatingTypes() {
        final String HTML =
                "<p>" +
                        "<a href=\"/internal-1\">Internal 1</a>, " +
                        "<a href=\"https://external.com\">External 1</a>, " +
                        "<a href=\"/internal-2\">Internal 2</a>, " +
                        "<a href=\"https://another-external.com\">External 2</a>" +
                        "</p>";

        when(linkService.createExternalLink("/internal-1", null))
                .thenReturn(new FlatLink(null, "/internal-1", LinkType.INTERNAL));

        when(linkService.createExternalLink("https://external.com", null))
                .thenReturn(new FlatLink(null, "https://external.com", LinkType.EXTERNAL));

        when(linkService.createExternalLink("/internal-2", null))
                .thenReturn(new FlatLink(null, "/internal-2", LinkType.INTERNAL));

        when(linkService.createExternalLink("https://another-external.com", null))
                .thenReturn(new FlatLink(null, "https://another-external.com", LinkType.EXTERNAL));

        String result = transformer.processLinks(HTML, null);

        assertTrue(result.contains(
                "<vs-link type=\"default\" href=\"/internal-1\">Internal 1</vs-link>"
        ));

        assertTrue(result.contains(
                "<vs-link type=\"external\" href=\"https://external.com\">External 1</vs-link>"
        ));

        assertTrue(result.contains(
                "<vs-link type=\"default\" href=\"/internal-2\">Internal 2</vs-link>"
        ));

        assertTrue(result.contains(
                "<vs-link type=\"external\" href=\"https://another-external.com\">External 2</vs-link>"
        ));

        Matcher matcher = Pattern.compile("<vs-link ").matcher(result);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        assertEquals(4, count);
    }

    @Test
    @DisplayName("The a tag expression does not get confused by tags that start with a (i.e article)")
    void links_no_article_tag() {
        final String HTML = "<article>The a tag expression does not get confused by tags that start with a</article>";
        String result = transformer.processLinks(HTML, null);

        Assertions.assertFalse(result.contains("data-type="));
    }

    @Test
    @DisplayName("Headings convert heading to vue components")
    void heading() {
        String HTML = "<h2>Heading 2</h2>";

        String result = transformer.processHeadings(HTML, false);

        assertTrue(result.startsWith("<vs-heading level=\"2\""));
        assertTrue(result.endsWith("</vs-heading>"));
    }

    @Test
    @DisplayName("Create anchor links for h2 headings")
    void h2_multiple() {
        String HTML = "<h2>Heading 1</h2><p>Blah</p><h2>Heading 2</h2><p>Blah</p><h2>Heading 3</h2><p>Blah</p>";

        String result = transformer.processHeadings(HTML, false);
        Matcher matcher = Pattern.compile("id=").matcher(result);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        assertEquals(3, count);
    }

    @Test
    @DisplayName("The conversion to kebab case reduce the noise as much as possible")
    void h2_kebabCase() {
        String HTML = "<h2> Heading for ---- OMG!  </h2>";

        assertTrue(transformer.processHeadings(HTML, false).contains("id=\"heading-for-omg\""));
    }

    @Test
    @DisplayName("Transform ordered lists into vue components")
    void lists_ol() {
        String HTML = "<ol><li>Item 1</li><li>Item 2</li></ol>";

        assertTrue(transformer.processLists(HTML).endsWith("</vs-list>"));
        assertTrue(transformer.processLists(HTML).startsWith("<vs-list ordered>"));
    }

    @Test
    @DisplayName("Transform unordered lists into vue components")
    void lists_ul() {
        String HTML = "<ul><li>Item 1</li><li>Item 2</li></ul>";

        assertTrue(transformer.processLists(HTML).endsWith("</vs-list>"));
        assertTrue(transformer.processLists(HTML).startsWith("<vs-list>"));
    }

    @Test
    @DisplayName("VS-2488 - Consecutive lists will be turned into vs-link components")
    void lists_severalConsecutiveLists() {
        String output = transformer.processLists(
                "<ul><li>Item 1</li><li>Item 2</li></ul>" +
                        "<ul><li>Item 3</li><li>Item 4</li></ul>"
        );

        assertEquals(
                "<vs-list><li>Item 1</li><li>Item 2</li></vs-list>" +
                        "<vs-list><li>Item 3</li><li>Item 4</li></vs-list>",
                output
        );
    }

    @Test
    @DisplayName("VS-2488 - Nested list will only alter the most external item")
    void lists_nestedLists() {
        String output = transformer.processLists(
                "<ul><li>Item 1</li><li>Item 2</li><li><ul><li>Item 3</li><li>Item 4</li></ul></li></ul>"
        );

        assertEquals(
                "<vs-list><li>Item 1</li><li>Item 2</li><li><ul><li>Item 3</li><li>Item 4</li></ul></li></vs-list>",
                output
        );
    }

    @Test
    @DisplayName("VS-2488 - Complex scenario that combines consecutive and nested lists")
    void lists_complex() {
        String output = transformer.processLists(
                "<ul><ol><ul></ul></ol><ol></ol></ul> <ol><ol></ol></ol><ul><ul></ul></ul>"
        );

        assertEquals(
                "<vs-list><ol><ul></ul></ol><ol></ol></vs-list> " +
                        "<vs-list ordered><ol></ol></vs-list><vs-list><ul></ul></vs-list>",
                output
        );
    }

    @Test
    @DisplayName("Download document links includes the icon and the size")
    void links_external() {
        final String HTML = "<p>Take a look at the " +
                "<a href=\"https://www.visitscotland.com/pdf/\" title=\"iKnow Community\" target=\"_blank\">iKnow Scotland Community</a>" +
                " to share your tips and pick up a couple more for your next break or day out in Scotland.</p>";

        when(linkService.createExternalLink("https://www.visitscotland.com/pdf/", null))
                .thenReturn(new FlatLink(null, "https://www.visitscotland.com/pdf/", LinkType.DOWNLOAD));

        when(linkService.getDownloadText("https://www.visitscotland.com/pdf/"))
                .thenReturn("(PDF 3GB)");

        String result = transformer.processLinks(HTML, null);

        Assertions.assertTrue(result.contains("<vs-link "));
        Assertions.assertTrue(result.contains(" type=\"download\""));
        Assertions.assertTrue(result.contains("(PDF 3GB)"));
    }

    @Test
    @DisplayName("VS-2733 - Links with attributes are interpreted")
    void links_withAttributes() {
        final String HTML = "<p>Take a look at the " +
                "<a rel=\"nofollow\" href=\"https://www.visitscotland.com/\" title=\"iKnow Community\" target=\"_blank\">iKnow Scotland Community</a></p>";

        when(linkService.createExternalLink("https://www.visitscotland.com/", null))
                .thenReturn(new FlatLink(null, "https://www.visitscotland.com/", LinkType.INTERNAL));

        String result = transformer.processLinks(HTML, null);

        Assertions.assertTrue(result.contains("<vs-link "));
    }

    @Test
    @DisplayName("VS-2756 - Global internal links add the language")
    void localizedUrl() {
        final String HTML = "<a href=\"https://www.visitscotland.com/\">Link 1</a>";

        when(linkService.createExternalLink("https://www.visitscotland.com/", null))
                .thenReturn(new FlatLink(null, "https://www.visitscotland.com/es/", LinkType.EXTERNAL));

        Assertions.assertTrue(
                transformer.processLinks(HTML, null).contains("https://www.visitscotland.com/es/")
        );
    }

    @Test
    @DisplayName("VS-3133 - info alert spans")
    void infoAlerts() {
        String html = "<span class=\"info-text\">This guide is only for UK visitors</span>";

        Assertions.assertTrue(
                transformer.process(html, null).contains(
                        "<vs-alert>This guide is only for UK visitors</vs-alert>"
                )
        );
    }

    @Test
    @DisplayName("VS-3133 - info alert multiple spans")
    void infoAlerts_multipleSpans() {
        String html = "<span class=\"info-text\">This guide is only for UK visitors</span>" +
                "<span class=\"info-text\">Second alert</span>";

        Assertions.assertTrue(
                transformer.process(html, null).contains(
                        "<vs-alert>This guide is only for UK visitors</vs-alert>" +
                                "<vs-alert>Second alert</vs-alert>"
                )
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "<span class=\"info-tex\">This guide is only for UK visitors</span>",
            "<span>This guide is only for UK visitors</span>",
            "<notaspan class=\"info-tex\">This guide is only for UK visitors</notaspan>"    })
    @DisplayName("VS-3133 - info alert malformed spans not transformed")
    void infoAlert_malformedSpans(String html) {
        Assertions.assertEquals(html, transformer.process(html, null));
    }
}
