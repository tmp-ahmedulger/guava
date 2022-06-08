package com.ulger.guava.parceldeliveryservice.api.parcel;

import java.util.Arrays;
import java.util.Optional;

public enum LoadingStatus {

    CREATED(1),
    LOADED(2),
    UNLOADED(3);

    private final int code;

    LoadingStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * Returns status matching with code.
     *
     * @param code specific code used for matching
     * @return matching status. If not found returns Optional.empty
     */
    public static Optional<LoadingStatus> findByCode(int code) {
        return findByCodeInternal(code);
    }

    private static Optional<LoadingStatus> findByCodeInternal(int code) {
        return Arrays
                .stream(values())
                .filter(loadingStatus -> loadingStatus.code == code)
                .findFirst();
    }

    public boolean isOneOfLoaded() {
        return this == LOADED;
    }

    public boolean isUnloaded() {
        return this == UNLOADED;
    }
}