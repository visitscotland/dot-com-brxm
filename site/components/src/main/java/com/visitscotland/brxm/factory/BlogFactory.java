package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Blog;
import com.visitscotland.brxm.hippobeans.Profile;
import com.visitscotland.brxm.model.FlatBlog;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;


@Component
public class BlogFactory {

    private final ResourceBundleService bundle;

    private static final String BLOG_LABELS = "essentials.global";
    public BlogFactory( ResourceBundleService bundle){
        this.bundle = bundle;
    }

    public FlatBlog getBlog(Blog doc, Locale locale, Collection<String> errorMessages){
        FlatBlog blog = new FlatBlog();
        if (doc.getAuthor() instanceof Profile) {
            blog.setAuthorName(doc.getAuthor().getName());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyy", locale);
        blog.setPublishDate(sdf.format(doc.getPublishDate().getTime()));
        if (doc.getReadingTime() > 0) {
            //TODO consider moving the labels to modules in the future depending on the next phases
            String readTime = bundle.getResourceBundle(BLOG_LABELS,"read-time.plural", locale);
            if (doc.getReadingTime() < 2) {
                readTime = bundle.getResourceBundle(BLOG_LABELS,"read-time.singular", locale);
            }
            blog.setReadingTime(String.format(readTime, doc.getReadingTime().toString()));
        } else {
            errorMessages.add("The Read Time for the blog should be greater than 0 minutes.");
        }

        return blog;
    }

}
