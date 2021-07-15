package com.pixel.meirlen.orc.api;

/**
 * Created by aaskarov on 13.11.2017.
 */

public class NetworkResponse {

    public static final int CODE_OK = 200;
    public static final int CREATED = 201;
    public static final int OBJECT_CODE_OK = 0;
    public static final int NO_ERROR = 0;
    public static final int ERROR_CACHE_NOT_AVAILABLE = -1;
    public static final int ERROR_NO_INTERNET = -2;
    public static final int ERROR_HOST_NOT_FOUND = -3;
    public static final int ERROR_TIMEOUT = -4;
    public static final int ERROR_UNDEFINED_ERROR = -5;
    public static final int ERROR_NETWORK_IO = -6;
    public int error;
    public int code;
    public String responseString;
    public Exception exception;
}
