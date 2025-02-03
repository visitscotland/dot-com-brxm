package com.visitscotland.brxm.jcr;

import com.visitscotland.utils.Contract;

import javax.jcr.RepositoryException;
import javax.jcr.Node;

import java.util.Optional;

public final class NodeUtility {
    private NodeUtility() { }

    public static boolean isJcrPropertyBlank(final Node node, final String jcrPropertyName) throws RepositoryException {
        if (Contract.anyNull(node, jcrPropertyName)) return true;
        final Optional<String> property = Optional.ofNullable(node
            .getProperty(jcrPropertyName)
            .getString());

        return property.map(String::isBlank).orElse(true);
    }
}