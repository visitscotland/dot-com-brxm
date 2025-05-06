package com.visitscotland.brxm.resource;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ResourceBundle;
import java.util.Optional;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.Map;

@Component
class ResourceBundleValueExtractor {
    /**
     * Extracts the value associated with a specific key from the given resource bundle.
     *
     * <p>This method requires that neither {@code resourceBundle} nor {@code key} is null;
     * otherwise, it throws a {@link NullPointerException}. If the key is not found in the resource bundle,
     * it returns an empty {@code Optional}. Otherwise, it returns an {@code Optional} containing the associated value.
     *
     * @param resourceBundle the resource bundle from which to extract the value; must not be null
     * @param key the key whose associated value is to be retrieved; must not be null
     * @return an {@code Optional} containing the value associated with {@code key} if present;
     *         otherwise, an empty {@code Optional}
     * @throws NullPointerException if {@code resourceBundle} or {@code key} is null
     */
    Optional<String> extractValueFromResourceBundle(final @Nonnull ResourceBundle resourceBundle,
                                                    final @Nonnull String key) {
        Objects.requireNonNull(resourceBundle, "resourceBundle cannot be null");
        Objects.requireNonNull(key, "key cannot be null");

        if (!resourceBundle.containsKey(key)) {
            return Optional.empty();
        }

        final String resourceBundleValue = resourceBundle.getString(key);
        return Optional.of(resourceBundleValue);
    }

    /**
     * Extracts all non-blank values from the given resource bundle as an unmodifiable list.
     *
     * <p>This method requires that {@code resourceBundle} is not null; otherwise, it throws a {@link NullPointerException}.
     * It streams over all keys in the resource bundle, retrieves their associated string values,
     * filters out any blank values (empty or whitespace only), and collects the remaining values into an unmodifiable list.
     *
     * @param resourceBundle the resource bundle from which to extract values; must not be null
     * @return an unmodifiable {@code List<String>} containing all non-blank values from the resource bundle
     * @throws NullPointerException if {@code resourceBundle} is null
     */
    List<String> extractValuesFromResourceBundleAsList(final @Nonnull ResourceBundle resourceBundle) {
        Objects.requireNonNull(resourceBundle, "resourceBundle cannot be null");

        return resourceBundle
            .keySet()
            .stream()
            .map(resourceBundle::getString)
            .filter(Predicate.not(String::isBlank))
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Extracts all key-value pairs from the given resource bundle as an unmodifiable map.
     *
     * <p>This method requires that {@code resourceBundle} is not null; otherwise, it throws a {@link NullPointerException}.
     * It iterates over all keys in the resource bundle, retrieves their associated string values,
     * and includes only those entries where the value is not blank (i.e., not empty or whitespace).
     *
     * @param resourceBundle the resource bundle from which to extract key-value pairs; must not be null
     * @return an unmodifiable {@code Map<String, String>} containing all key-value pairs from the resource bundle
     *         with non-blank values
     * @throws NullPointerException if {@code resourceBundle} is null
     */
    Map<String, String> extractValuesFromResourceBundleAsMap(final @Nonnull ResourceBundle resourceBundle) {
        Objects.requireNonNull(resourceBundle, "resourceBundle cannot be null");
        final Map<String, String> bundleMap = new HashMap<>();

        for (final String key : resourceBundle.keySet()) {
            final String value = resourceBundle.getString(key);

            if(!value.isBlank()) {
                bundleMap.put(key, value);
            }
        }

        return Map.copyOf(bundleMap);
    }
}