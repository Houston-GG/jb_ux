package com.example.jb_ux.configuration;

public enum AvailableMethod {

    REST_REQUEST("REST_REQUEST"),
    EMAIL("EMAIL"),
    UNKNOWN_METHOD("UNKNOWN_METHOD");

    private String method;

    AvailableMethod(String method){
        this.method = method;
    }

    public String getMethod(){ return method;}
}
