package com.visitscotland.brxm.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UrlParameterAppenderTest {
    @Test
    public void When_UrlWithoutQuery_Expect_QuestionMark() {
        final String url = "http://example.com";
        final String expected = "?";

        Assertions.assertEquals(expected, UrlParameterAppender.addParameter(url));
    }

    @Test
    public void When_UrlWithQuery_Expect_Ampersand() {
        final String url = "http://example.com?param=value";
        final String expected = "&";

        Assertions.assertEquals(expected, UrlParameterAppender.addParameter(url));
    }

    @Test
    public void When_UrlWithMultipleQueryParams_Expect_Ampersand() {
        final String url = "http://example.com?param1=value1&param2=value2";
        final String expected = "&";

        Assertions.assertEquals(expected, UrlParameterAppender.addParameter(url));
    }

    @Test
    public void When_EmptyUrl_Expect_QuestionMark() {
        final String url = "";
        final String expected = "?";

        Assertions.assertEquals(expected, UrlParameterAppender.addParameter(url));
    }

    @Test
    public void When_UrlWithFragment_Expect_QuestionMark() {
        final String url = "http://example.com#section";
        final String expected = "?";

        Assertions.assertEquals(expected, UrlParameterAppender.addParameter(url));
    }
}