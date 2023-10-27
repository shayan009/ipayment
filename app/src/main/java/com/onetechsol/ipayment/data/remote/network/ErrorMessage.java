package com.onetechsol.ipayment.data.remote.network;

public class ErrorMessage {

    private final int status;
    private final String detail;

    public ErrorMessage(int status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    public int status() {
        return status;
    }

    public String detail() {
        return detail;
    }
}
