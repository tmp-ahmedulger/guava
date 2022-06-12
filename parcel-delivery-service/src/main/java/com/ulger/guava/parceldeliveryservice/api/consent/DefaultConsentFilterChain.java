package com.ulger.guava.parceldeliveryservice.api.consent;

import java.util.LinkedList;
import java.util.List;

public class DefaultConsentFilterChain<T extends ConsentFilterParams> implements ConsentFilterChain<T> {

    private final List<ConsentFilter<T>> filters;

    public DefaultConsentFilterChain() {
        this.filters = new LinkedList<>();
    }

    @Override
    public List<ConsentFilter<T>> getFilters() {
        return filters;
    }

    public void addFilter(ConsentFilter<T> checker) {
        filters.add(checker);
    }
}