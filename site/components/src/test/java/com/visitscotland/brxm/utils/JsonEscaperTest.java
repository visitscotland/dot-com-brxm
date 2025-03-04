package com.visitscotland.brxm.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JsonEscaperTest {
    @Test
    void When_Escape_With_JsonString_Expect_EscapedJson() {
        final String input = "{\"name\": \"John\"}";
        final boolean isJsonObject = true;
        final String expected = "{'name': 'John'}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_NonJsonString_Expect_EscapedString() {
        final String input = "{\"name\": \"John\"}";
        final boolean isJsonObject = false;
        final String expected = "{&quot;name&quot;: &quot;John&quot;}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_EmptyString_Expect_EmptyString() {
        final String input = "";
        final boolean isJsonObject = true;
        final String expected = "";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_SingleQuote_Expect_EscapedSingleQuote() {
        final String input = "{\"name\": \"O'Reilly\"}";
        final boolean isJsonObject = true;
        final String expected = "{'name': 'O\\'Reilly'}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_DoubleQuoteInJson_Expect_SingleQuote() {
        final String input = "{\"name\": \"John\"}";
        final boolean isJsonObject = true;
        final String expected = "{'name': 'John'}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_DoubleQuoteInNonJson_Expect_HtmlEntity() {
        final String input = "{\"name\": \"John\"}";
        final boolean isJsonObject = false;
        final String expected = "{&quot;name&quot;: &quot;John&quot;}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_MixedQuotesInJson_Expect_EscapedMixedQuotes() {
        final String input = "{\"quote\": \"He said, 'Hello'\"}";
        final boolean isJsonObject = true;
        final String expected = "{'quote': 'He said, \\'Hello\\''}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_MixedQuotesInNonJson_Expect_EscapedMixedQuotes() {
        final String input = "{\"quote\": \"He said, 'Hello'\"}";
        final boolean isJsonObject = false;
        final String expected = "{&quot;quote&quot;: &quot;He said, \\'Hello\\'&quot;}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_NestedJson_Expect_EscapedNestedJson() {
        final String input = "{\"person\": {\"name\": \"John\", \"age\": 30}}";
        final boolean isJsonObject = true;
        final String expected = "{'person': {'name': 'John', 'age': 30}}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }

    @Test
    void When_Escape_With_NestedNonJson_Expect_EscapedNestedNonJson() {
        final String input = "{\"person\": {\"name\": \"John\", \"age\": 30}}";
        final boolean isJsonObject = false;
        final String expected = "{&quot;person&quot;: {&quot;name&quot;: &quot;John&quot;, &quot;age&quot;: 30}}";

        Assertions.assertEquals(expected, JsonEscaper.escape(input, isJsonObject));
    }
}