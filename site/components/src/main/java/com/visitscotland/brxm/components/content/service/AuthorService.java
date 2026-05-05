package com.visitscotland.brxm.components.content.service;

import com.visitscotland.brxm.factory.BlogFactory;
import com.visitscotland.brxm.hippobeans.Page;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.pagebuilder.PageCompositionException;
import com.visitscotland.brxm.pagebuilder.PageCompositionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    final BlogFactory blogFactory;

    public AuthorService(BlogFactory blogFactory) {
        this.blogFactory = blogFactory;
    }

    /**
     * Retrieves the author section associated with the current page, if one exists.
     *
     * <p>This method orchestrates the extraction and transformation of a blog document
     * into a flattened model suitable for presentation. It handles error collection during
     * the transformation process and registers any issues with the page template.</p>
     *
     * @param helper the PageCompositionHelper providing access to the current page context
     *               and request locale; must not be null
     *
     * @return an Optional containing the transformed FlatBlog if the page contains a blog
     *         and transformation succeeds; an empty Optional if no blog exists or if
     *         transformation fails
     *
     * @throws IllegalArgumentException if helper is null (via PageCompositionHelper.getPage())
     *
     * @see BlogFactory#getBlog(com.visitscotland.brxm.hippobeans.Blog, java.util.Locale, Collection)
     */
    public Optional<FlatBlog> getBlog(PageCompositionHelper helper) {
        try {
            Page page = helper.getPage();
            if (page.getBlog() != null) {
                Collection<String> errorMessages = new ArrayList<>();

                FlatBlog blog = blogFactory.getBlog(page.getBlog(), helper.getLocale(), errorMessages);

                for (String errorMessage : errorMessages) {
                    helper.getPageTemplate().addErrorMessage(errorMessage);
                }

                return Optional.of(blog);
            }
        } catch (PageCompositionException e) {
            helper.getPageTemplate().addErrorMessage("The blog section threw an error and cannot be included in this page");
            logger.warn(e.getMessage(), e);
        }
        return Optional.empty();
    }
}
