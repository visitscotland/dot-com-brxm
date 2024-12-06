package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.ExternalLink;
import com.visitscotland.brxm.hippobeans.SharedLink;
import com.visitscotland.brxm.hippobeans.SharedLinkBSH;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.Optional;

/**
 * jcr:Name = visitscotland:shared-link-bsh-external
 *
 * @author jose.calcines
 */
public class SharedLinkExternalValidator implements Validator<Node> {

    public Optional<Violation> validate(final ValidationContext context, final Node node) {
        try {
            for (NodeIterator it = node.getNodes(SharedLink.LINK_TYPES); it.hasNext(); ) {
                Node link = it.nextNode();
                if (link.isNodeType(ExternalLink.PRIMARY_TYPE)) {
                    if (node.hasProperty(SharedLinkBSH.WEBSITE_TYPE) && node.getProperty(SharedLinkBSH.WEBSITE_TYPE).getString().isEmpty()) {
                        return Optional.of(context.createViolation());
                    }
                }
            }
            return Optional.empty();
        } catch (RepositoryException e) {
            return Optional.of(context.createViolation());
        }
    }
}
