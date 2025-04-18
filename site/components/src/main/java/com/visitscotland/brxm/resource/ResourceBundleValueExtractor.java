package com.visitscotland.brxm.resource;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Predicate;

@Component
class ResourceBundleValueExtractor {
    /**
     * Extracts a value from the given {@link ResourceBundle} for the specified key.
     *
     * <p>If the key is not present in the resource bundle, this method returns {@link Optional#empty()}.
     * Otherwise, it returns an {@link Optional} containing the corresponding value.</p>
     *
     * @param resourceBundle the {@link ResourceBundle} to extract the value from; must not be {@code null}
     * @param key the key to look up in the resource bundle; must not be {@code null}
     * @return an {@link Optional} containing the value associated with the key, or {@link Optional#empty()} if the key is not found
     */
    Optional<String> extractValueFromResourceBundle(final @Nonnull ResourceBundle resourceBundle,
                                                    final @Nonnull String key) {
        if (!resourceBundle.containsKey(key)) {
            return Optional.empty();
        }

        final String resourceBundleValue = resourceBundle.getString(key);
        return Optional.of(resourceBundleValue);
    }

    /**
     * Extracts all values from the given {@link ResourceBundle} as an unmodifiable list.
     *
     * <p>This method retrieves all keys from the resource bundle, maps each key to its corresponding
     * value, and collects these values into an unmodifiable {@link List}.</p>
     *
     * @param resourceBundle the {@link ResourceBundle} to extract values from; must not be {@code null} -
     *                       it will result in a {@link NullPointerException}.
     * @return an unmodifiable {@link List} containing all values from the resource bundle
     */
    List<String> extractValuesFromResourceBundleAsList(final @Nonnull ResourceBundle resourceBundle) {
        Objects.requireNonNull(resourceBundle);

        return resourceBundle
            .keySet()
            .stream()
            .map(resourceBundle::getString)
            .filter(Predicate.not(String::isBlank))
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Creates a map containing all key-value pairs from the provided {@link ResourceBundle}.
     * Each key in the map corresponds to a key in the resource bundle, and the value is the associated string.
     *
     * @param resourceBundle the {@link ResourceBundle} from which to extract labels; must not be null
     * @return a {@link Map} containing all keys and their corresponding string values from the resource bundle
     */
    Map<String, String> extractValuesFromResourceBundleAsMap(final @Nonnull ResourceBundle resourceBundle) {
        final Map<String, String> bundleMap = new HashMap<>();

        for (final String key : resourceBundle.keySet()) {
            bundleMap.put(key, resourceBundle.getString(key));
        }

        return bundleMap;
    }
}