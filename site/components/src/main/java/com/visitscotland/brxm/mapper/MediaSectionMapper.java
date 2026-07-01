package com.visitscotland.brxm.mapper;

import com.visitscotland.brxm.hippobeans.Image;
import com.visitscotland.brxm.hippobeans.MediaCollection;
import com.visitscotland.brxm.hippobeans.VideoLink;
import com.visitscotland.brxm.model.FlatImage;
import com.visitscotland.brxm.model.MediaSection;
import com.visitscotland.brxm.model.Module;
import com.visitscotland.brxm.model.megalinks.EnhancedLink;
import com.visitscotland.brxm.services.LinkService;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class MediaSectionMapper {

    private static final Logger log = LoggerFactory.getLogger(MediaSectionMapper.class);

    private final ImageMapper imageMapper;
    private final LinkService linkService;

    public MediaSectionMapper(ImageMapper imageMapper, LinkService linkService) {
        this.imageMapper = imageMapper;
        this.linkService = linkService;
    }

    public MediaSection map(MediaCollection compound, Module<?> module, Locale locale) {
        MediaSection media = new MediaSection();
        List<Object> items = new ArrayList<>();
        if (compound == null) {
            return null;
        }
        for (HippoBean document: compound.getMedia()){
            if (document instanceof Image){
                items.add(imageMapper.getImage(document, module, locale));
            } else if (document instanceof VideoLink) {
                items.add(getVideo((VideoLink) document, module, locale));
            } else if (document != null) {
                log.warn("Unsupported media type {} in MediaSection compound", document.getClass().getSimpleName());
                module.addErrorMessage("Unsupported media type: " + document.getDisplayName());
            }
        }

        media.setType(getType(items));
        media.setItems(items);

        return media;
    }

    private EnhancedLink getVideo(VideoLink videoLink, Module<?> module, Locale locale) {
        return linkService.createVideo(videoLink.getVideoLink(), module, locale);
    }

    private String getType(List<Object> items) {
        if (items.isEmpty()){
            log.warn("No media section found in MediaSection compound");
        } else if (items.size() == 1){
            if (items.get(0) instanceof EnhancedLink) {
                return "video";
            } else if (items.get(0) instanceof FlatImage) {
                return "image";
            }
        } else {
            return "carousel";
        }
        return "unknown";
    }
}
