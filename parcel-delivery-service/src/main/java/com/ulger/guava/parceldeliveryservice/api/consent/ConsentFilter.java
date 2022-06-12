package com.ulger.guava.parceldeliveryservice.api.consent;

/**
 * Some operations are not permitted to do. In example,
 * parcel address can be updated by only ADMIN or STANDARD user who is package owner.
 * This interface is responsible with checking if operation is permitted to do.
 *
 * @param <T> is generic type of class that keeps parameters to be used while
 *           checking allowance
 */
public interface ConsentFilter<T extends ConsentFilterParams> {

    /**
     * @throws ConsentFilterException when operation is not permitted
     * @param params keeps the parameters those are required for checking
     */
    void check(T params);
}