package com.example.vault.testyapp.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vault on 16/03/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories {
    public String title;
    public String description;
    public int post_count;

}
