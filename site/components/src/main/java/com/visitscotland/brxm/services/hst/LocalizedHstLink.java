package com.visitscotland.brxm.services.hst;

import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.platform.linking.HstLinkImpl;

/**
 * Ensures links always link to the current mount.
 * If the document does not exist on the current mount, HstLinkCreatorImpl will fall back to the english mount.
 * However we want to link to the current mount, and let the translation fallback handle resolution of the english document.
 * <p>
 * {@code HstLinkImpl} handles a lot of the logic related to publication of document and External preview and it wouldn't
 * make sense to repeat all that logic. On the other hand it is a bad practice to use an external implementation of an
 * interface (code smell) that's why we have created this class to contain the issue.
 */
public class LocalizedHstLink extends HstLinkImpl {

    /**
     * Creates a localized HST link that ensures links always point to the current mount.
     *
     * @param path the path to the document
     * @param mount the current mount to link to
     */
    public LocalizedHstLink(String path, Mount mount) {
        super(path, mount);
    }
}
