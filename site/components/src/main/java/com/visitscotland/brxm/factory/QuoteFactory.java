package com.visitscotland.brxm.factory;

import com.visitscotland.brxm.hippobeans.Quote;
import com.visitscotland.brxm.hippobeans.capabilities.Linkable;
import com.visitscotland.brxm.model.FlatQuote;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.services.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class QuoteFactory {

    private static final Logger contentLogger = LoggerFactory.getLogger("content");

    private ImageFactory imageFactory;

    private LinkService linkService;

    public QuoteFactory(ImageFactory imageFactory, LinkService linkService){
        this.imageFactory = imageFactory;
        this.linkService = linkService;
    }

    public FlatQuote getQuote(Quote doc, Module<?> module, Locale locale){
        FlatQuote quote = new FlatQuote();
        quote.setQuote(doc.getQuote());
        quote.setAuthorName(doc.getAuthor());
        quote.setAuthorTitle(doc.getRole());

        if (doc.getImage() != null) {
            quote.setImage(imageFactory.createImage(doc.getImage(), module, locale));
        }

        if (doc.getProduct() instanceof Linkable) {
            EnhancedLink link = linkService.createEnhancedLink((Linkable) doc.getProduct(), module, locale, false);
            if (link != null && link.getLink() != null) {
                link.setLabel(linkService.createFindOutMoreLink(module, locale, doc.getProduct()).getLabel());

                quote.setLink(link);
            } else{
                module.addErrorMessage("The product provided for the quote is not valid, please review the document at: " + doc.getPath());
                contentLogger.warn("The Product for this iCentre ({})is not a valid link.", doc.getPath());
            }
        } else if (doc.getProduct() != null){
            module.addErrorMessage("The product provided for the quote is not valid, please review the document at: " + doc.getPath());
            contentLogger.warn("The Product for this iCentre ({})is not a valid link.", doc.getPath());
        }

        return quote;
    }

}
