package com.example.vault.testyapp;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by vault on 14/03/2017.
 */

public class TestJS {
    @JavascriptInterface
    public String testFunction() {
        Log.d("Loggy", "Testylog");
        return "Test";
    }
}
