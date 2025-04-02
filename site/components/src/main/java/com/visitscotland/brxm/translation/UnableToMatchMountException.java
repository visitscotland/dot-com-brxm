package com.visitscotland.brxm.translation;

import org.hippoecm.hst.core.request.ResolvedVirtualHost;

final class UnableToMatchMountException extends TranslationException {
    private static final String EXCEPTION_MESSAGE =
        "failed to resolve request path '%s' to a mount that is on the virtual host %s";

    public UnableToMatchMountException(final String requestPath,
                                       final ResolvedVirtualHost resolvedVirtualHost) {
        super(String.format(EXCEPTION_MESSAGE, requestPath, resolvedVirtualHost.getVirtualHost().getName()));
    }
}
