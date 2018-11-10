package com.example.meirlen.orc.helper;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionManagerTest {



    private SessionManager sessionManager;
    private String LAT="45.55";



    @Test
    public void setLat() {
        sessionManager.setLat("12.52");
    }

    @Test
    public void getLat() {
        sessionManager.getLat();
    }
}