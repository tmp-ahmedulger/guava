package com.ulger.guava.parceldeliveryservice.api.parcel;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

    CREATED(1),
    LOADED(2),
    UNLOADED(3);

    private final int code;

    Status(int code) {
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
    public static Optional<Status> findByCode(int code) {
        return findByCodeInternal(code);
    }

    private static Optional<Status> findByCodeInternal(int code) {
        return Arrays
                .stream(values())
                .filter(status -> status.code == code)
                .findFirst();
    }

    public boolean isOneOfLoaded() {
        return this == LOADED;
    }

    public boolean isUnloaded() {
        return this == UNLOADED;
    }
}