package com.example.meirlen.orc.api;

import org.json.JSONObject;

/**
 * Created by aaskarov on 13.11.2017.
 */

public class APIError {

    public String system, code, message, developerMessage, moreInfo;
    public int status, series;

    public static APIError parse(String responseString) {
        try {
            APIError error = new APIError();

            JSONObject json = new JSONObject(responseString);
            error.system = json.getString("system");
            error.status = json.getInt("status");
            error.series = json.getInt("series");
            error.code = json.getString("code");
            error.message = json.getString("message");
            error.developerMessage = json.optString("developerMessage");
            error.moreInfo = json.optString("moreInfo");
            return error;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "APIError{" +
                "system='" + system + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", status=" + status +
                ", series=" + series +
                '}';
    }
}
