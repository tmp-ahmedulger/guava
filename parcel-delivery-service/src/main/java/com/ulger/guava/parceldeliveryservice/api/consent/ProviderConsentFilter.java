package com.ulger.guava.parceldeliveryservice.api.consent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProviderConsentFilter<T extends ConsentFilterParams> implements ConsentFilter<T> {

    private final ConsentFilterChain<T> chain;

    public ProviderConsentFilter(ConsentFilterChain<T> chain) {
        this.chain = chain;
    }

    @Override
    public void check(T params) {

        log.info("Allowance check operation started");

        for (ConsentFilter<T> checker : chain.getFilters()) {
            checker.check(params);
        }
    }
}