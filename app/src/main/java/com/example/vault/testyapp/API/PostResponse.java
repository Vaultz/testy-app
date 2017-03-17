package com.example.vault.testyapp.API;

import com.example.vault.testyapp.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vault on 16/03/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResponse {

    @JsonProperty("posts")
    public Article[] articles;
}
