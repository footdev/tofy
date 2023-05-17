package com.ssafy.tofy.util;

public enum Status {
    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
