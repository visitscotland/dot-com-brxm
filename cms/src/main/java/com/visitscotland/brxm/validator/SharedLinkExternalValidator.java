package com.visitscotland.brxm.validator;

import com.visitscotland.brxm.hippobeans.Article;
import com.visitscotland.brxm.hippobeans.ArticleSection;
import com.visitscotland.brxm.hippobeans.SharedLink;
import org.onehippo.cms.services.validation.api.ValidationContext;
import org.onehippo.cms.services.validation.api.ValidationContextException;
import org.onehippo.cms.services.validation.api.Validator;
import org.onehippo.cms.services.validation.api.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * jcr:Name = visitscotland:shared-link-bsh-external
 *
 * @author jose.calcines
 */
public class SharedLinkExternalValidator implements Validator<Node> {

    private static final Logger logger = LoggerFactory.getLogger(LinkValidator.class);



    public Optional<Violation> validate(final ValidationContext context, final Node node) {
        try {
                for (NodeIterator it = node.getNodes(SharedLink.LINK_TYPES); it.hasNext(); ) {
                    Node link = it.nextNode();
                    if (link.isNodeType("visitscotland:ExternalLink")) {
                        if (node.hasProperty("visitscotland:websiteType") && node.getProperty("visitscotland:websiteType").getString().isEmpty()){
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
