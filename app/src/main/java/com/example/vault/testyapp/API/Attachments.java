package com.example.vault.testyapp.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vault on 17/03/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachments {
    public String url;
}
