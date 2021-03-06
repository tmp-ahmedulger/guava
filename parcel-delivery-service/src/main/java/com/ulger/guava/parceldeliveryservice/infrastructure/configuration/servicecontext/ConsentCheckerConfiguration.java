package com.ulger.guava.parceldeliveryservice.infrastructure.configuration.servicecontext;

import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.consent.DefaultConsentFilterChain;
import com.ulger.guava.parceldeliveryservice.api.consent.ProviderConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter.AddressUpdateConsentFilterParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter.AddressUpdatePermissionConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter.AddressUpdateStateConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter.DeliveryAddressConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter.StatusConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter.StatusUpdateConsentFilterParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter.StatusUpdatePermissionConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter.StatusUpdateStateConsentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsentCheckerConfiguration {

    @Bean
    @Qualifier("addressUpdateConsentFilterProvider")
    public ConsentFilter<AddressUpdateConsentFilterParams> addressUpdateConsentFilterProvider(
            @Autowired AddressUpdatePermissionConsentFilter addressUpdatePermissionConsentFilter,
            @Autowired DeliveryAddressConsentFilter deliveryAddressConsentFilter,
            @Autowired AddressUpdateStateConsentFilter addressUpdateStateConsentFilter) {

        DefaultConsentFilterChain<AddressUpdateConsentFilterParams> chain = new DefaultConsentFilterChain<>();
        chain.addFilter(addressUpdatePermissionConsentFilter);
        chain.addFilter(deliveryAddressConsentFilter);
        chain.addFilter(addressUpdateStateConsentFilter);

        return new ProviderConsentFilter<>(chain);
    }

    @Bean
    @Qualifier("statusUpdateConsentFilterProvider")
    public ConsentFilter<StatusUpdateConsentFilterParams> statusUpdateConsentFilterProvider(
            @Autowired StatusConsentFilter statusConsentFilter,
            @Autowired StatusUpdatePermissionConsentFilter statusUpdatePermissionConsentFilter,
            @Autowired StatusUpdateStateConsentFilter statusUpdateStateConsentFilter) {

        DefaultConsentFilterChain<StatusUpdateConsentFilterParams> chain = new DefaultConsentFilterChain<>();
        chain.addFilter(statusConsentFilter);
        chain.addFilter(statusUpdatePermissionConsentFilter);
        chain.addFilter(statusUpdateStateConsentFilter);

        return new ProviderConsentFilter<>(chain);
    }
}