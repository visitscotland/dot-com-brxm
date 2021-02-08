package com.visitscotland.brxm.components.content.factory.utils;

import com.visitscotland.brxm.beans.Quote;
import com.visitscotland.brxm.beans.capabilities.Linkable;
import com.visitscotland.brxm.beans.mapping.FlatQuote;
import com.visitscotland.brxm.beans.mapping.Module;
import com.visitscotland.brxm.components.content.factory.ImageFactory;
import com.visitscotland.brxm.components.content.factory.LinkModulesFactory;
import com.visitscotland.brxm.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

public class QuoteFactory {

    private static final Logger logger = LoggerFactory.getLogger(QuoteFactory.class);

    private ImageFactory imageFactory;

    private LinkModulesFactory linkFactory;

    public QuoteFactory(){
        this(new ImageFactory(), new LinkModulesFactory());
    }

    private QuoteFactory(ImageFactory imageFactory, LinkModulesFactory linkFactory){
        this.imageFactory = imageFactory;
        this.linkFactory = linkFactory;
    }

    public FlatQuote getQuote(Quote doc, Module module, Locale locale){
        FlatQuote quote = new FlatQuote();
        quote.setQuote(doc.getQuote());
        quote.setAuthorName(doc.getAuthor());
        quote.setAuthorTitle(doc.getRole());

        if (doc.getImage() != null) {
            quote.setImage(imageFactory.createImage(doc.getImage(), module, locale));
        }

        if (doc.getProduct() instanceof Linkable) {
            quote.setLink(linkFactory.createEnhancedLink((Linkable) doc.getProduct(), locale, false));
        } else if (doc.getProduct() != null){
            CommonUtils.contentIssue("The Product for this iCentre (%s) is not a valid link.", doc.getPath());
            logger.warn("The Product for this iCentre ({})is not a valid link.", doc.getPath());
        }
        return quote;
    }

}
