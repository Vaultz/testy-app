package com.example.vault.testyapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;

import com.example.vault.testyapp.API.Attachments;
import com.example.vault.testyapp.API.Categories;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vault on 14/03/2017.
 */

// Parcelable permettra à l'objet d'être passé en paramètre d'une activité à l'autre
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article implements Parcelable {
    String title;
    String content;
    String date;
    String[] categories;
    String[] attachments;

    boolean featured;

    public Article() {}

    // @JsonProperty indique à Android studio de chercher cette variable
    @JsonProperty("title")
    public void setTitle(String titl) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.title = Html.fromHtml(titl, Html.FROM_HTML_MODE_LEGACY).toString();
        }
        else {
            this.title= Html.fromHtml(titl).toString();
        }
    }

    @JsonProperty("content")
    public void setContent(String cont) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.content = Html.fromHtml(cont, Html.FROM_HTML_MODE_LEGACY).toString();
        }
        else {
            this.content = Html.fromHtml(cont).toString();
        }
    }

    @JsonProperty("categories")
    public void setCategories(Categories[] cat) {
        this.categories = new String[cat.length];

        for (int i = 0; i < cat.length; i++) {
            this.categories[i] = cat[i].title;
        }
    }

    @JsonProperty("attachments")
    public void setAttachments(Attachments[] att) {
        this.attachments = new String[att.length];

        for (int i = 0; i < att.length ; i++) {
            this.attachments[i] = att[i].url;
        }

    }


    // Code auto-généré : ctrl+Enter -> Parcelable


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.date);
        dest.writeStringArray(this.categories);
        dest.writeStringArray(this.attachments);
        dest.writeByte(this.featured ? (byte) 1 : (byte) 0);
    }

    protected Article(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.date = in.readString();
        this.categories = in.createStringArray();
        this.attachments = in.createStringArray();
        this.featured = in.readByte() != 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };


    public String getContent() {
        return content;
    }
}
