package com.ulger.guava.parceldeliveryservice.api.parcel;

import java.util.Arrays;
import java.util.Optional;

public enum State {

    ACTIVE(1),
    CANCELLED(2);

    private final int code;

    State(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * Returns state matching with code.
     *
     * @param code specific code used for matching
     * @return matching status. If not found returns Optional.empty
     */
    public static Optional<State> findByCode(int code) {
        return findByCodeInternal(code);
    }

    private static Optional<State> findByCodeInternal(int code) {
        return Arrays
                .stream(values())
                .filter(status -> status.code == code)
                .findFirst();
    }

    public boolean isCancelled() {
        return this == CANCELLED;
    }
}