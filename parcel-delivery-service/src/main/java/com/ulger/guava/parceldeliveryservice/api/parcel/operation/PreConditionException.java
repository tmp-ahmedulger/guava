package com.ulger.guava.parceldeliveryservice.api.parcel.operation;

public class PreConditionException extends RuntimeException {

    private String key;
    private Object[] args;

    public String getKey() {
        return key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    public Object[] getArgs() {
        return args;
    }

    public PreConditionException(String key, Object[] args) {
        this.key = key;
        this.args = args;
    }

    public PreConditionException(String message, String key, Object[] args) {
        super(message);
        this.key = key;
        this.args = args;
    }

    public PreConditionException(String message, Throwable cause, String key, Object[] args) {
        super(message, cause);
        this.key = key;
        this.args = args;
    }

    public PreConditionException(Throwable cause, String key, Object[] args) {
        super(cause);
        this.key = key;
        this.args = args;
    }

    public PreConditionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String key, Object[] args) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.key = key;
        this.args = args;
    }

    public static PreConditionException generateByKey(String key) {
        PreConditionException preConditionException = new PreConditionException();
        preConditionException.setKey(key);
        return preConditionException;
    }

    public PreConditionException() {
    }

    public PreConditionException(String message) {
        super(message);
    }

    public PreConditionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PreConditionException(Throwable cause) {
        super(cause);
    }

    public PreConditionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}