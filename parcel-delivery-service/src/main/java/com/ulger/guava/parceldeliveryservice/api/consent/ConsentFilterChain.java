package com.ulger.guava.parceldeliveryservice.api.consent;

import java.util.List;

public interface ConsentFilterChain<T extends ConsentFilterParams> {

    List<ConsentFilter<T>> getFilters();
}
