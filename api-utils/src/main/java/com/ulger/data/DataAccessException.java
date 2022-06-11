package com.ulger.data;

public class DataAccessException extends RuntimeException {

    private Integer reasonCode;

    public Integer getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(Integer reasonCode) {
        this.reasonCode = reasonCode;
    }

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

    public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataAccessException(Integer reasonCode) {
        this.reasonCode = reasonCode;
    }

    public DataAccessException(String message, Integer reasonCode) {
        super(message);
        this.reasonCode = reasonCode;
    }

    public DataAccessException(String message, Throwable cause, Integer reasonCode) {
        super(message, cause);
        this.reasonCode = reasonCode;
    }

    public DataAccessException(Throwable cause, Integer reasonCode) {
        super(cause);
        this.reasonCode = reasonCode;
    }

    public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer reasonCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.reasonCode = reasonCode;
    }
}