package com.visitscotland.brxm.mapper.module;

import com.visitscotland.brxm.model.megalinks.Entry;
import com.visitscotland.brxm.services.ResourceBundleService;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransportMapper {

    private static final String TRANSPORTS = "transports";

    private final Logger contentLogger;
    private final ResourceBundleService bundle;

    public TransportMapper(Logger contentLogger, ResourceBundleService bundle) {
        this.contentLogger = contentLogger;
        this.bundle = bundle;
    }

    public List<Entry> getTransports(final String[] transports, final Locale locale) {
        if (transports != null) {
            List<Entry> transportsToAdd = new ArrayList<>();
            for (final String transport : transports) {
                getTransport(transport, locale).ifPresent(transportsToAdd::add);
            }
            return transportsToAdd;
        }
        return Collections.emptyList();
    }

    private Optional<Entry> getTransport(final String transport, final Locale locale) {
        if (transport != null && bundle.existsResourceBundleKey(TRANSPORTS, transport, locale)) {
            return Optional.of(new Entry(transport, bundle.getResourceBundle(TRANSPORTS, transport, locale)));
        } else {
            contentLogger.warn("No key/value pair for transport type {}", transport);
        }

        return Optional.empty();
    }
}
